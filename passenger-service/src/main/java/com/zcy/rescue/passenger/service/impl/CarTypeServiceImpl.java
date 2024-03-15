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
import com.zcy.rescue.passenger.dao.CarTypeMapper;
import com.zcy.rescue.passenger.entity.CarType;
import com.zcy.rescue.passenger.enums.error.CarTypeErrorCodeEnum;
import com.zcy.rescue.passenger.service.CarTypeService;
import com.zcy.rescue.passenger.vo.CarTypeVO;
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
public class CarTypeServiceImpl implements CarTypeService {

	private static final Logger logger = LoggerFactory.getLogger(CarTypeServiceImpl.class);

    /**
     * 注入的的Dao接口实现
     */
	@Autowired
	private CarTypeMapper carTypeMapper;

    /**
     * 根据主键删除记录(逻辑删除)
	 * @param carType 
     * @return
     */
	@Override
	@Transactional
	public int deleteByPrimaryKey( CarType carType) {
	    logger.debug("deleteByPrimaryKey carType={}", JsonUtil.toJson(carType));
		//校验参数
		checkExist(carType);
		CarType carTypeNew = new CarType();
		carTypeNew .setId(carType.getId());
		//carTypeNew .setDeleteFlag(BooleanEnum.YES.getValue());
		long millis = DateUtil.getCurrentMillis();
		updateInfo(carTypeNew,millis);
		int result = carTypeMapper.updateByPrimaryKeySelective(carTypeNew);
	    //int result = carTypeMapper.deleteByPrimaryKey(carType);
	    logger.debug("deleteByPrimaryKey end.deleted {} record(s).", result);
	    return result;
	}
    /**
     * 根据主键批量删除记录(逻辑删除)
	 * @param carTypeVO 
     * @return
     */
	@Override
	@Transactional
	public int deleteToUpdate( CarTypeVO carTypeVO) {
	    logger.debug("deleteBatch carType={}", JsonUtil.toJson(carTypeVO));
		//校验参数 
		 updateParameter(carTypeVO);
		int result = carTypeMapper.deleteToUpdate(carTypeVO); 
	    logger.debug("deleteBatch end.deleteBatch {} record(s).", result);
	    return result;
	}
    /**
     * 根据主键批量更新状态
	 * @param carTypeVO 
     * @return
     */
	@Override
	@Transactional
	public int updateBatchStatus( CarTypeVO carTypeVO) {
	    logger.debug("updateBatchStatus carType={}", JsonUtil.toJson(carTypeVO));
		//校验参数 
		 updateParameter(carTypeVO);
		int result = carTypeMapper.updateBatchStatus(carTypeVO); 
	    logger.debug("updateBatchStatus end.updateBatchStatus {} record(s).", result);
	    return result;
	}
	
    /**
     * 实体参数校验及赋值
     * @param carTypeVO
     */
    private void updateParameter(CarTypeVO carTypeVO) {
        if (CollectionUtils.isEmpty(carTypeVO.getIdList())) {
            throw new SystemException(CarTypeErrorCodeEnum.CARTYPE_DATA_IDLIST_NOT_EMPTY.getValue()
                    , CarTypeErrorCodeEnum.CARTYPE_DATA_IDLIST_NOT_EMPTY.getDescriptions());
        }
        long millis = DateUtil.getCurrentMillis();
        //carTypeVO.setUpdateTime(millis);
        //carTypeVO.setUpdateId(BizUserUtil.getUserID())
        //        .setUpdateName(BizUserUtil.getUserName());
    }
	
	/**
	 * 生成分布式ID并赋值
	 * @param entity 实体对象
	 */
	public void createId(CarType entity,long millis) {
		//创建人
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
	public void updateInfo(CarType entity,long millis) {
		//修改人
		//entity.setUpdateTime(millis);
		//entity.setUpdateId(BizUserUtil.getUserID())
		//		.setUpdateName(BizUserUtil.getUserName());
	}

    /**
     * 插入实体信息，只有当字段有值时，才会构建sql
     * @param carType 
     * @return
     */
	@Override
	@Transactional
    public CarType insertSelective(CarType carType) {
    	logger.debug("insertSelective carType={}", JsonUtil.toJson(carType));
		long millis = DateUtil.getCurrentMillis();
		//校验参数
        // TODO 排重校验
        
        
		//生成分布式ID
		createId(carType,millis);
	    int result = carTypeMapper.insertSelective(carType);
		CarType newCarType = null;
        if(result != 0){
            newCarType = carTypeMapper.getByPrimaryKey(carType);
        }
		logger.debug("insertSelective end.inserted success, entity: {}.", JsonUtil.toJson(newCarType));
	    return newCarType;
    }
	
	/**
	 * 批量插入实体信息(如果失败全部失败)
	 * @param list wms客户地址表List
	 * @return List<Long> 主键list集合
	 */
	@Override
	@Transactional
	public List<Long> insertBatch( List<CarType> list) {
		logger.debug("insertBatch list={}",  JsonUtil.toJson(list));
		long millis = DateUtil.getCurrentMillis();
		List<Long> idList = new ArrayList<Long>();
		if(list == null){
			return idList;
		}
		Map<String, List<CarType>> listMap = BizDataUtil.splitBatches(list, null);
		for (Map.Entry<String, List<CarType>> stringListEntry : listMap.entrySet()) {
			List<CarType> batchList = stringListEntry.getValue();
			logger.debug("批量写入:{}", stringListEntry.getKey());
			for (CarType entity : batchList ) {
				//校验参数
	        	
				//生成分布式ID
				createId(entity,millis);
				idList.add(entity.getId());
			}
	
			int result = carTypeMapper.insertBatch(batchList);
			if(result == 0){
				return new ArrayList<Long>();
			}
			logger.debug("insertBatch success, ids: {}.", JsonUtil.toJson(idList));
		}
		return idList;
	}

    /**
     * 根据主键查询实体信息
     * @param carType 
     * @return
     */
	@Override
    public CarType getByPrimaryKey(CarType carType) {
        logger.debug("getByPrimaryKey carType={}", JsonUtil.toJson(carType));
		//校验参数
        CarType result = carTypeMapper.getByPrimaryKey(carType);
        return result;
    }

    /**
     * 根据对象中的主键更新其他信息，只有当字段有值时，才会构建sql
     * @param carType 
     * @return
     */
	@Override
	@Transactional
    public CarType updateByPrimaryKeySelective(CarType carType) {
    	logger.debug("updateByPrimaryKeySelective carType={}", JsonUtil.toJson(carType));
		//校验参数
        checkExist(carType);
		long millis = DateUtil.getCurrentMillis();
		updateInfo(carType,millis);  
	    int result = carTypeMapper.updateByPrimaryKeySelective(carType);
		CarType newCarType = null;
        if(result != 0){
            newCarType = getByPrimaryKey(carType);
        }
		logger.debug("updateByPrimaryKeySelective end.updated success, entity:{} .",JsonUtil.toJson( newCarType));
	    return newCarType;
    }
    
    /**
     * 根据查询条件获取列表，不分页，就算查询条件中有分页信息，返回结果也不会分页
     * @param carTypeVO 查询bean
     * @return
     */
	@Override
    public List<CarType> getList(CarTypeVO carTypeVO) {
    	logger.debug("getList carTypeVO={}", JsonUtil.toJson(carTypeVO));
		//参数校验 
	    List<CarType> resultList = carTypeMapper.getList(carTypeVO);
	    return resultList;
    }
    
    /**
     * 根据查询条件获取列表，分页，如果查询条件中没有分页信息，使用默认分页信息
     * 分页信息 @see com.yestae.framework.common.domain.PageVO
     * @param carTypeVO 查询bean
     * @return
     */
	@Override
    public List<CarType> getPage(CarTypeVO carTypeVO) {
    	logger.debug("getPage carTypeVO={}", JsonUtil.toJson(carTypeVO));
		//参数校验 
	    List<CarType> resultList = carTypeMapper.getPage(carTypeVO);
	    return resultList;
    }
    
    /**
     * 根据查询条件获取行数
     * @param carTypeVO 查询bean
     * @return
     */
	@Override
    public int getCount(CarTypeVO carTypeVO) {
    	logger.debug("getCount carTypeVO={}", JsonUtil.toJson(carTypeVO));
		//参数校验 
	    int count = carTypeMapper.getCount(carTypeVO);
	    return count;
    }
	
 
	
    /**
     * 检查数据库中是否存在数据
	 * @param carType 
     * @return
     */
    public CarType checkExist(CarType carType){
        CarType oldCarType = getByPrimaryKey(carType);
        if(oldCarType == null){
          // 异常处理
            throw new SystemException(CarTypeErrorCodeEnum.CARTYPE_DATA_UNEXIST.getValue()
                    , CarTypeErrorCodeEnum.CARTYPE_DATA_UNEXIST.getDescriptions());
        }
        return oldCarType;
    }
}
