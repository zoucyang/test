/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.service.impl;
 

import com.zcy.rescue.passenger.common.exceptions.SystemException;
import com.zcy.rescue.passenger.common.utils.BizDataUtil;
import com.zcy.rescue.passenger.common.utils.DateUtil;
import com.zcy.rescue.passenger.common.utils.JsonUtil;
import com.zcy.rescue.passenger.dao.CarBrandMapper;
import com.zcy.rescue.passenger.entity.CarBrand;
import com.zcy.rescue.passenger.enums.error.CarBrandErrorCodeEnum;
import com.zcy.rescue.passenger.service.CarBrandService;
import com.zcy.rescue.passenger.vo.CarBrandVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
 
/**
 * 的Service实现
 * 
 * @author zcy
 * @date 2024-1-28
 */
@Service
public class CarBrandServiceImpl implements CarBrandService {

	private static final Logger logger = LoggerFactory.getLogger(CarBrandServiceImpl.class);

    /**
     * 注入的的Dao接口实现
     */
	@Autowired
	private CarBrandMapper carBrandMapper;

    /**
     * 根据主键删除记录(逻辑删除)
	 * @param carBrand 
     * @return
     */
	@Override
	@Transactional
	public int deleteByPrimaryKey( CarBrand carBrand) {
	    logger.debug("deleteByPrimaryKey carBrand={}", JsonUtil.toJson(carBrand));
		//校验参数
		checkExist(carBrand);
		CarBrand carBrandNew = new CarBrand();
		carBrandNew .setId(carBrand.getId());
		//carBrandNew .setDeleteFlag(BooleanEnum.YES.getValue());
		long millis = DateUtil.getCurrentMillis();
		updateInfo(carBrandNew,millis);
		int result = carBrandMapper.updateByPrimaryKeySelective(carBrandNew);
	    //int result = carBrandMapper.deleteByPrimaryKey(carBrand);
	    logger.debug("deleteByPrimaryKey end.deleted {} record(s).", result);
	    return result;
	}
    /**
     * 根据主键批量删除记录(逻辑删除)
	 * @param carBrandVO 
     * @return
     */
	@Override
	@Transactional
	public int deleteToUpdate( CarBrandVO carBrandVO) {
	    logger.debug("deleteBatch carBrand={}", JsonUtil.toJson(carBrandVO));
		//校验参数 
		 updateParameter(carBrandVO);
		int result = carBrandMapper.deleteToUpdate(carBrandVO); 
	    logger.debug("deleteBatch end.deleteBatch {} record(s).", result);
	    return result;
	}
    /**
     * 根据主键批量更新状态
	 * @param carBrandVO 
     * @return
     */
	@Override
	@Transactional
	public int updateBatchStatus( CarBrandVO carBrandVO) {
	    logger.debug("updateBatchStatus carBrand={}", JsonUtil.toJson(carBrandVO));
		//校验参数 
		 updateParameter(carBrandVO);
		int result = carBrandMapper.updateBatchStatus(carBrandVO); 
	    logger.debug("updateBatchStatus end.updateBatchStatus {} record(s).", result);
	    return result;
	}
	
    /**
     * 实体参数校验及赋值
     * @param carBrandVO
     */
    private void updateParameter(CarBrandVO carBrandVO) {
        if (CollectionUtils.isEmpty(carBrandVO.getIdList())) {
            throw new SystemException(CarBrandErrorCodeEnum.CARBRAND_DATA_IDLIST_NOT_EMPTY.getValue()
                    , CarBrandErrorCodeEnum.CARBRAND_DATA_IDLIST_NOT_EMPTY.getDescriptions());
        }
        long millis = DateUtil.getCurrentMillis();
        //carBrandVO.setUpdateTime(millis);
        //carBrandVO.setUpdateId(BizUserUtil.getUserID())
        //        .setUpdateName(BizUserUtil.getUserName());
    }
	
	/**
	 * 生成分布式ID并赋值
	 * @param entity 实体对象
	 */
	public void createId(CarBrand entity,long millis) {
		////创建人
		//entity.setCreateTime(millis);
		//entity.setCreateId(BizUserUtil.getUserID())
		//		.setCreateName(BizUserUtil.getUserName())
		//		.setDeleteFlag(BooleanEnum.NO.getValue());
		////生成分布式id
		//entity.setId(entity.getId()==null? SnowflakeIdWorker.nextId():entity.getId());
		////修改人
		//entity.setUpdateTime(millis);
		//entity.setUpdateId(BizUserUtil.getUserID())
		//		.setUpdateName(BizUserUtil.getUserName());
	}

	/**
	 * 更新实体赋值通用属性
	 * @param entity 实体对象
	 */
	public void updateInfo(CarBrand entity,long millis) {
		//修改人
		//entity.setUpdateTime(millis);
		//entity.setUpdateId(BizUserUtil.getUserID())
		//		.setUpdateName(BizUserUtil.getUserName());
	}

    /**
     * 插入实体信息，只有当字段有值时，才会构建sql
     * @param carBrand 
     * @return
     */
	@Override
	@Transactional
    public CarBrand insertSelective(CarBrand carBrand) {
    	logger.debug("insertSelective carBrand={}", JsonUtil.toJson(carBrand));
		long millis = DateUtil.getCurrentMillis();
		//校验参数
        // TODO 排重校验
        
        
		//生成分布式ID
		createId(carBrand,millis);
	    int result = carBrandMapper.insertSelective(carBrand);
		CarBrand newCarBrand = null;
        if(result != 0){
            newCarBrand = carBrandMapper.getByPrimaryKey(carBrand);
        }
		logger.debug("insertSelective end.inserted success, entity: {}.", JsonUtil.toJson(newCarBrand));
	    return newCarBrand;
    }
	
	/**
	 * 批量插入实体信息(如果失败全部失败)
	 * @param list wms客户地址表List
	 * @return List<Long> 主键list集合
	 */
	@Override
	@Transactional
	public List<Long> insertBatch( List<CarBrand> list) {
		logger.debug("insertBatch list={}",  JsonUtil.toJson(list));
		long millis = DateUtil.getCurrentMillis();
		List<Long> idList = new ArrayList<Long>();
		if(list == null){
			return idList;
		}
		Map<String, List<CarBrand>> listMap = BizDataUtil.splitBatches(list, null);
		for (Map.Entry<String, List<CarBrand>> stringListEntry : listMap.entrySet()) {
			List<CarBrand> batchList = stringListEntry.getValue();
			logger.debug("批量写入:{}", stringListEntry.getKey());
			for (CarBrand entity : batchList ) {
				//校验参数
	        	
				//生成分布式ID
				createId(entity,millis);
				//idList.add(entity.getId());
			}
	
			int result = carBrandMapper.insertBatch(batchList);
			if(result == 0){
				return new ArrayList<Long>();
			}
			logger.debug("insertBatch success, ids: {}.", JsonUtil.toJson(idList));
		}
		return idList;
	}

    /**
     * 根据主键查询实体信息
     * @param carBrand 
     * @return
     */
	@Override
    public CarBrand getByPrimaryKey(CarBrand carBrand) {
        logger.debug("getByPrimaryKey carBrand={}", JsonUtil.toJson(carBrand));
		//校验参数
        CarBrand result = carBrandMapper.getByPrimaryKey(carBrand);
        return result;
    }

    /**
     * 根据对象中的主键更新其他信息，只有当字段有值时，才会构建sql
     * @param carBrand 
     * @return
     */
	@Override
	@Transactional
    public CarBrand updateByPrimaryKeySelective(CarBrand carBrand) {
    	logger.debug("updateByPrimaryKeySelective carBrand={}", JsonUtil.toJson(carBrand));
		//校验参数
        checkExist(carBrand);
		long millis = DateUtil.getCurrentMillis();
		updateInfo(carBrand,millis);  
	    int result = carBrandMapper.updateByPrimaryKeySelective(carBrand);
		CarBrand newCarBrand = null;
        if(result != 0){
            newCarBrand = getByPrimaryKey(carBrand);
        }
		logger.debug("updateByPrimaryKeySelective end.updated success, entity:{} .",JsonUtil.toJson( newCarBrand));
	    return newCarBrand;
    }
    
    /**
     * 根据查询条件获取列表，不分页，就算查询条件中有分页信息，返回结果也不会分页
     * @param carBrandVO 查询bean
     * @return
     */
	@Override
    public List<CarBrand> getList(CarBrandVO carBrandVO) {
    	logger.debug("getList carBrandVO={}", JsonUtil.toJson(carBrandVO));
		//参数校验 
	    List<CarBrand> resultList = carBrandMapper.getList(carBrandVO);
	    return resultList;
    }
    
    /**
     * 根据查询条件获取列表，分页，如果查询条件中没有分页信息，使用默认分页信息
     * 分页信息 @see com.yestae.framework.common.domain.PageVO
     * @param carBrandVO 查询bean
     * @return
     */
	@Override
    public List<CarBrand> getPage(CarBrandVO carBrandVO) {
    	logger.debug("getPage carBrandVO={}", JsonUtil.toJson(carBrandVO));
		//参数校验 
	    List<CarBrand> resultList = carBrandMapper.getPage(carBrandVO);
	    return resultList;
    }
    
    /**
     * 根据查询条件获取行数
     * @param carBrandVO 查询bean
     * @return
     */
	@Override
    public int getCount(CarBrandVO carBrandVO) {
    	logger.debug("getCount carBrandVO={}", JsonUtil.toJson(carBrandVO));
		//参数校验 
	    int count = carBrandMapper.getCount(carBrandVO);
	    return count;
    }
	
 
	
    /**
     * 检查数据库中是否存在数据
	 * @param carBrand 
     * @return
     */
    public CarBrand checkExist(CarBrand carBrand){
        CarBrand oldCarBrand = getByPrimaryKey(carBrand);
        if(oldCarBrand == null){
          // 异常处理
            throw new SystemException(CarBrandErrorCodeEnum.CARBRAND_DATA_UNEXIST.getValue()
                    , CarBrandErrorCodeEnum.CARBRAND_DATA_UNEXIST.getDescriptions());
        }
        return oldCarBrand;
    }
}
