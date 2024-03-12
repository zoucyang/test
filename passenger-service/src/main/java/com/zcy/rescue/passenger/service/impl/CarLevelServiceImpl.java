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
import com.zcy.rescue.passenger.dao.CarLevelMapper;
import com.zcy.rescue.passenger.entity.CarLevel;
import com.zcy.rescue.passenger.enums.CarLevelErrorCodeEnum;
import com.zcy.rescue.passenger.service.CarLevelService;
import com.zcy.rescue.passenger.vo.CarLevelVO;
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
 * 车辆级别列表的Service实现
 * 
 * @author zcy
 * @date 2024-1-28
 */
@Service
public class CarLevelServiceImpl implements CarLevelService {

	private static final Logger logger = LoggerFactory.getLogger(CarLevelServiceImpl.class);

    /**
     * 注入的车辆级别列表的Dao接口实现
     */
	@Autowired
	private CarLevelMapper carLevelMapper;

    /**
     * 根据主键删除记录(逻辑删除)
	 * @param carLevel 车辆级别列表
     * @return
     */
	@Override
	@Transactional
	public int deleteByPrimaryKey( CarLevel carLevel) {
	    logger.debug("deleteByPrimaryKey carLevel={}", JsonUtil.toJson(carLevel));
		//校验参数
		checkExist(carLevel);
		CarLevel carLevelNew = new CarLevel();
		carLevelNew .setId(carLevel.getId());
		//carLevelNew .setDeleteFlag(BooleanEnum.YES.getValue());
		long millis = DateUtil.getCurrentMillis();
		updateInfo(carLevelNew,millis);
		int result = carLevelMapper.updateByPrimaryKeySelective(carLevelNew);
	    //int result = carLevelMapper.deleteByPrimaryKey(carLevel);
	    logger.debug("deleteByPrimaryKey end.deleted {} record(s).", result);
	    return result;
	}
    /**
     * 根据主键批量删除记录(逻辑删除)
	 * @param carLevelVO 车辆级别列表
     * @return
     */
	@Override
	@Transactional
	public int deleteToUpdate( CarLevelVO carLevelVO) {
	    logger.debug("deleteBatch carLevel={}", JsonUtil.toJson(carLevelVO));
		//校验参数 
		 updateParameter(carLevelVO);
		int result = carLevelMapper.deleteToUpdate(carLevelVO); 
	    logger.debug("deleteBatch end.deleteBatch {} record(s).", result);
	    return result;
	}
    /**
     * 根据主键批量更新状态
	 * @param carLevelVO 车辆级别列表
     * @return
     */
	@Override
	@Transactional
	public int updateBatchStatus( CarLevelVO carLevelVO) {
	    logger.debug("updateBatchStatus carLevel={}", JsonUtil.toJson(carLevelVO));
		//校验参数 
		 updateParameter(carLevelVO);
		int result = carLevelMapper.updateBatchStatus(carLevelVO); 
	    logger.debug("updateBatchStatus end.updateBatchStatus {} record(s).", result);
	    return result;
	}
	
    /**
     * 实体参数校验及赋值
     * @param carLevelVO
     */
    private void updateParameter(CarLevelVO carLevelVO) {
        if (CollectionUtils.isEmpty(carLevelVO.getIdList())) {
            throw new SystemException(CarLevelErrorCodeEnum.CARLEVEL_DATA_IDLIST_NOT_EMPTY.getValue()
                    , CarLevelErrorCodeEnum.CARLEVEL_DATA_IDLIST_NOT_EMPTY.getDescriptions());
        }
        long millis = DateUtil.getCurrentMillis();
        //carLevelVO.setUpdateTime(millis);
        //carLevelVO.setUpdateId(BizUserUtil.getUserID())
        //        .setUpdateName(BizUserUtil.getUserName());
    }
	
	/**
	 * 生成分布式ID并赋值
	 * @param entity 实体对象
	 */
	public void createId(CarLevel entity,long millis) {
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
	public void updateInfo(CarLevel entity,long millis) {
		//修改人
		//entity.setUpdateTime(millis);
		//entity.setUpdateId(BizUserUtil.getUserID())
		//		.setUpdateName(BizUserUtil.getUserName());
	}

    /**
     * 插入实体信息，只有当字段有值时，才会构建sql
     * @param carLevel 车辆级别列表
     * @return
     */
	@Override
	@Transactional
    public CarLevel insertSelective(CarLevel carLevel) {
    	logger.debug("insertSelective carLevel={}", JsonUtil.toJson(carLevel));
		long millis = DateUtil.getCurrentMillis();
		//校验参数
        // TODO 排重校验
        
        
		//生成分布式ID
		createId(carLevel,millis);
	    int result = carLevelMapper.insertSelective(carLevel);
		CarLevel newCarLevel = null;
        if(result != 0){
            newCarLevel = carLevelMapper.getByPrimaryKey(carLevel);
        }
		logger.debug("insertSelective end.inserted success, entity: {}.", JsonUtil.toJson(newCarLevel));
	    return newCarLevel;
    }
	
	/**
	 * 批量插入实体信息(如果失败全部失败)
	 * @param list wms客户地址表List
	 * @return List<Long> 主键list集合
	 */
	@Override
	@Transactional
	public List<Long> insertBatch( List<CarLevel> list) {
		logger.debug("insertBatch list={}",  JsonUtil.toJson(list));
		long millis = DateUtil.getCurrentMillis();
		List<Long> idList = new ArrayList<Long>();
		if(list == null){
			return idList;
		}
		Map<String, List<CarLevel>> listMap = BizDataUtil.splitBatches(list, null);
		for (Map.Entry<String, List<CarLevel>> stringListEntry : listMap.entrySet()) {
			List<CarLevel> batchList = stringListEntry.getValue();
			logger.debug("批量写入:{}", stringListEntry.getKey());
			for (CarLevel entity : batchList ) {
				//校验参数
	        	
				//生成分布式ID
				createId(entity,millis);
				idList.add(entity.getId());
			}
	
			int result = carLevelMapper.insertBatch(batchList);
			if(result == 0){
				return new ArrayList<Long>();
			}
			logger.debug("insertBatch success, ids: {}.", JsonUtil.toJson(idList));
		}
		return idList;
	}

    /**
     * 根据主键查询实体信息
     * @param carLevel 车辆级别列表
     * @return
     */
	@Override
    public CarLevel getByPrimaryKey(CarLevel carLevel) {
        logger.debug("getByPrimaryKey carLevel={}", JsonUtil.toJson(carLevel));
		//校验参数
        CarLevel result = carLevelMapper.getByPrimaryKey(carLevel);
        return result;
    }

    /**
     * 根据对象中的主键更新其他信息，只有当字段有值时，才会构建sql
     * @param carLevel 车辆级别列表
     * @return
     */
	@Override
	@Transactional
    public CarLevel updateByPrimaryKeySelective(CarLevel carLevel) {
    	logger.debug("updateByPrimaryKeySelective carLevel={}", JsonUtil.toJson(carLevel));
		//校验参数
        checkExist(carLevel);
		long millis = DateUtil.getCurrentMillis();
		updateInfo(carLevel,millis);  
	    int result = carLevelMapper.updateByPrimaryKeySelective(carLevel);
		CarLevel newCarLevel = null;
        if(result != 0){
            newCarLevel = getByPrimaryKey(carLevel);
        }
		logger.debug("updateByPrimaryKeySelective end.updated success, entity:{} .",JsonUtil.toJson( newCarLevel));
	    return newCarLevel;
    }
    
    /**
     * 根据查询条件获取列表，不分页，就算查询条件中有分页信息，返回结果也不会分页
     * @param carLevelVO 车辆级别列表查询bean
     * @return
     */
	@Override
    public List<CarLevel> getList(CarLevelVO carLevelVO) {
    	logger.debug("getList carLevelVO={}", JsonUtil.toJson(carLevelVO));
		//参数校验 
	    List<CarLevel> resultList = carLevelMapper.getList(carLevelVO);
	    return resultList;
    }
    
    /**
     * 根据查询条件获取列表，分页，如果查询条件中没有分页信息，使用默认分页信息
     * 分页信息 @see com.yestae.framework.common.domain.PageVO
     * @param carLevelVO 车辆级别列表查询bean
     * @return
     */
	@Override
    public List<CarLevel> getPage(CarLevelVO carLevelVO) {
    	logger.debug("getPage carLevelVO={}", JsonUtil.toJson(carLevelVO));
		//参数校验 
	    List<CarLevel> resultList = carLevelMapper.getPage(carLevelVO);
	    return resultList;
    }
    
    /**
     * 根据查询条件获取行数
     * @param carLevelVO 车辆级别列表查询bean
     * @return
     */
	@Override
    public int getCount(CarLevelVO carLevelVO) {
    	logger.debug("getCount carLevelVO={}", JsonUtil.toJson(carLevelVO));
		//参数校验 
	    int count = carLevelMapper.getCount(carLevelVO);
	    return count;
    }
	
 
	
    /**
     * 检查数据库中是否存在数据
	 * @param carLevel 车辆级别列表
     * @return
     */
    public CarLevel checkExist(CarLevel carLevel){
        CarLevel oldCarLevel = getByPrimaryKey(carLevel);
        if(oldCarLevel == null){
          // 异常处理
            throw new SystemException(CarLevelErrorCodeEnum.CARLEVEL_DATA_UNEXIST.getValue()
                    , CarLevelErrorCodeEnum.CARLEVEL_DATA_UNEXIST.getDescriptions());
        }
        return oldCarLevel;
    }
}
