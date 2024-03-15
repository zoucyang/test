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
import com.zcy.rescue.passenger.dao.CarInfoMapper;
import com.zcy.rescue.passenger.entity.CarInfo;
import com.zcy.rescue.passenger.enums.error.CarInfoErrorCodeEnum;
import com.zcy.rescue.passenger.service.CarInfoService;
import com.zcy.rescue.passenger.vo.CarInfoVO;
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
public class CarInfoServiceImpl implements CarInfoService {

	private static final Logger logger = LoggerFactory.getLogger(CarInfoServiceImpl.class);

    /**
     * 注入的的Dao接口实现
     */
	@Autowired
	private CarInfoMapper carInfoMapper;

    /**
     * 根据主键删除记录(逻辑删除)
	 * @param carInfo 
     * @return
     */
	@Override
	@Transactional
	public int deleteByPrimaryKey( CarInfo carInfo) {
	    logger.debug("deleteByPrimaryKey carInfo={}", JsonUtil.toJson(carInfo));
		//校验参数
		checkExist(carInfo);
		CarInfo carInfoNew = new CarInfo();
		carInfoNew .setId(carInfo.getId());
		//carInfoNew .setDeleteFlag(BooleanEnum.YES.getValue());
		long millis = DateUtil.getCurrentMillis();
		updateInfo(carInfoNew,millis);
		int result = carInfoMapper.updateByPrimaryKeySelective(carInfoNew);
	    //int result = carInfoMapper.deleteByPrimaryKey(carInfo);
	    logger.debug("deleteByPrimaryKey end.deleted {} record(s).", result);
	    return result;
	}
    /**
     * 根据主键批量删除记录(逻辑删除)
	 * @param carInfoVO 
     * @return
     */
	@Override
	@Transactional
	public int deleteToUpdate( CarInfoVO carInfoVO) {
	    logger.debug("deleteBatch carInfo={}", JsonUtil.toJson(carInfoVO));
		//校验参数 
		 updateParameter(carInfoVO);
		int result = carInfoMapper.deleteToUpdate(carInfoVO); 
	    logger.debug("deleteBatch end.deleteBatch {} record(s).", result);
	    return result;
	}
    /**
     * 根据主键批量更新状态
	 * @param carInfoVO 
     * @return
     */
	@Override
	@Transactional
	public int updateBatchStatus( CarInfoVO carInfoVO) {
	    logger.debug("updateBatchStatus carInfo={}", JsonUtil.toJson(carInfoVO));
		//校验参数 
		 updateParameter(carInfoVO);
		int result = carInfoMapper.updateBatchStatus(carInfoVO); 
	    logger.debug("updateBatchStatus end.updateBatchStatus {} record(s).", result);
	    return result;
	}
	
    /**
     * 实体参数校验及赋值
     * @param carInfoVO
     */
    private void updateParameter(CarInfoVO carInfoVO) {
        if (CollectionUtils.isEmpty(carInfoVO.getIdList())) {
            throw new SystemException(CarInfoErrorCodeEnum.CARINFO_DATA_IDLIST_NOT_EMPTY.getValue()
                    , CarInfoErrorCodeEnum.CARINFO_DATA_IDLIST_NOT_EMPTY.getDescriptions());
        }
        long millis = DateUtil.getCurrentMillis();
        //carInfoVO.setUpdateTime(millis);
        //carInfoVO.setUpdateId(BizUserUtil.getUserID())
        //        .setUpdateName(BizUserUtil.getUserName());
    }
	
	/**
	 * 生成分布式ID并赋值
	 * @param entity 实体对象
	 */
	public void createId(CarInfo entity,long millis) {
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
	public void updateInfo(CarInfo entity,long millis) {
		//修改人
		//entity.setUpdateTime(millis);
		//entity.setUpdateId(BizUserUtil.getUserID())
		//		.setUpdateName(BizUserUtil.getUserName());
	}

    /**
     * 插入实体信息，只有当字段有值时，才会构建sql
     * @param carInfo 
     * @return
     */
	@Override
	@Transactional
    public CarInfo insertSelective(CarInfo carInfo) {
    	logger.debug("insertSelective carInfo={}", JsonUtil.toJson(carInfo));
		long millis = DateUtil.getCurrentMillis();
		//校验参数
        // TODO 排重校验
        
        
		//生成分布式ID
		createId(carInfo,millis);
	    int result = carInfoMapper.insertSelective(carInfo);
		CarInfo newCarInfo = null;
        if(result != 0){
            newCarInfo = carInfoMapper.getByPrimaryKey(carInfo);
        }
		logger.debug("insertSelective end.inserted success, entity: {}.", JsonUtil.toJson(newCarInfo));
	    return newCarInfo;
    }
	
	/**
	 * 批量插入实体信息(如果失败全部失败)
	 * @param list wms客户地址表List
	 * @return List<Long> 主键list集合
	 */
	@Override
	@Transactional
	public List<Long> insertBatch( List<CarInfo> list) {
		logger.debug("insertBatch list={}",  JsonUtil.toJson(list));
		long millis = DateUtil.getCurrentMillis();
		List<Long> idList = new ArrayList<Long>();
		if(list == null){
			return idList;
		}
		Map<String, List<CarInfo>> listMap = BizDataUtil.splitBatches(list, null);
		for (Map.Entry<String, List<CarInfo>> stringListEntry : listMap.entrySet()) {
			List<CarInfo> batchList = stringListEntry.getValue();
			logger.debug("批量写入:{}", stringListEntry.getKey());
			for (CarInfo entity : batchList ) {
				//校验参数
	        	
				//生成分布式ID
				createId(entity,millis);
				idList.add(entity.getId());
			}
	
			int result = carInfoMapper.insertBatch(batchList);
			if(result == 0){
				return new ArrayList<Long>();
			}
			logger.debug("insertBatch success, ids: {}.", JsonUtil.toJson(idList));
		}
		return idList;
	}

    /**
     * 根据主键查询实体信息
     * @param carInfo 
     * @return
     */
	@Override
    public CarInfo getByPrimaryKey(CarInfo carInfo) {
        logger.debug("getByPrimaryKey carInfo={}", JsonUtil.toJson(carInfo));
		//校验参数
        CarInfo result = carInfoMapper.getByPrimaryKey(carInfo);
        return result;
    }

    /**
     * 根据对象中的主键更新其他信息，只有当字段有值时，才会构建sql
     * @param carInfo 
     * @return
     */
	@Override
	@Transactional
    public CarInfo updateByPrimaryKeySelective(CarInfo carInfo) {
    	logger.debug("updateByPrimaryKeySelective carInfo={}", JsonUtil.toJson(carInfo));
		//校验参数
        checkExist(carInfo);
		long millis = DateUtil.getCurrentMillis();
		updateInfo(carInfo,millis);  
	    int result = carInfoMapper.updateByPrimaryKeySelective(carInfo);
		CarInfo newCarInfo = null;
        if(result != 0){
            newCarInfo = getByPrimaryKey(carInfo);
        }
		logger.debug("updateByPrimaryKeySelective end.updated success, entity:{} .",JsonUtil.toJson( newCarInfo));
	    return newCarInfo;
    }
    
    /**
     * 根据查询条件获取列表，不分页，就算查询条件中有分页信息，返回结果也不会分页
     * @param carInfoVO 查询bean
     * @return
     */
	@Override
    public List<CarInfo> getList(CarInfoVO carInfoVO) {
    	logger.debug("getList carInfoVO={}", JsonUtil.toJson(carInfoVO));
		//参数校验 
	    List<CarInfo> resultList = carInfoMapper.getList(carInfoVO);
	    return resultList;
    }
    
    /**
     * 根据查询条件获取列表，分页，如果查询条件中没有分页信息，使用默认分页信息
     * 分页信息 @see com.yestae.framework.common.domain.PageVO
     * @param carInfoVO 查询bean
     * @return
     */
	@Override
    public List<CarInfo> getPage(CarInfoVO carInfoVO) {
    	logger.debug("getPage carInfoVO={}", JsonUtil.toJson(carInfoVO));
		//参数校验 
	    List<CarInfo> resultList = carInfoMapper.getPage(carInfoVO);
	    return resultList;
    }
    
    /**
     * 根据查询条件获取行数
     * @param carInfoVO 查询bean
     * @return
     */
	@Override
    public int getCount(CarInfoVO carInfoVO) {
    	logger.debug("getCount carInfoVO={}", JsonUtil.toJson(carInfoVO));
		//参数校验 
	    int count = carInfoMapper.getCount(carInfoVO);
	    return count;
    }
	
 
	
    /**
     * 检查数据库中是否存在数据
	 * @param carInfo 
     * @return
     */
    public CarInfo checkExist(CarInfo carInfo){
        CarInfo oldCarInfo = getByPrimaryKey(carInfo);
        if(oldCarInfo == null){
          // 异常处理
            throw new SystemException(CarInfoErrorCodeEnum.CARINFO_DATA_UNEXIST.getValue()
                    , CarInfoErrorCodeEnum.CARINFO_DATA_UNEXIST.getDescriptions());
        }
        return oldCarInfo;
    }
}
