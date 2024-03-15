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
import com.zcy.rescue.passenger.dao.PassengerInfoMapper;
import com.zcy.rescue.passenger.entity.PassengerInfo;
import com.zcy.rescue.passenger.enums.error.PassengerInfoErrorCodeEnum;
import com.zcy.rescue.passenger.service.PassengerInfoService;
import com.zcy.rescue.passenger.vo.PassengerInfoVO;
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
public class PassengerInfoServiceImpl implements PassengerInfoService {

	private static final Logger logger = LoggerFactory.getLogger(PassengerInfoServiceImpl.class);

    /**
     * 注入的的Dao接口实现
     */
	@Autowired
	private PassengerInfoMapper passengerInfoMapper;

    /**
     * 根据主键删除记录(逻辑删除)
	 * @param passengerInfo 
     * @return
     */
	@Override
	@Transactional
	public int deleteByPrimaryKey( PassengerInfo passengerInfo) {
	    logger.debug("deleteByPrimaryKey passengerInfo={}", JsonUtil.toJson(passengerInfo));
		//校验参数
		checkExist(passengerInfo);
		PassengerInfo passengerInfoNew = new PassengerInfo();
		passengerInfoNew .setId(passengerInfo.getId());
		//passengerInfoNew .setDeleteFlag(BooleanEnum.YES.getValue());
		long millis = DateUtil.getCurrentMillis();
		updateInfo(passengerInfoNew,millis);
		int result = passengerInfoMapper.updateByPrimaryKeySelective(passengerInfoNew);
	    //int result = passengerInfoMapper.deleteByPrimaryKey(passengerInfo);
	    logger.debug("deleteByPrimaryKey end.deleted {} record(s).", result);
	    return result;
	}
    /**
     * 根据主键批量删除记录(逻辑删除)
	 * @param passengerInfoVO 
     * @return
     */
	@Override
	@Transactional
	public int deleteToUpdate( PassengerInfoVO passengerInfoVO) {
	    logger.debug("deleteBatch passengerInfo={}", JsonUtil.toJson(passengerInfoVO));
		//校验参数 
		 updateParameter(passengerInfoVO);
		int result = passengerInfoMapper.deleteToUpdate(passengerInfoVO); 
	    logger.debug("deleteBatch end.deleteBatch {} record(s).", result);
	    return result;
	}
    /**
     * 根据主键批量更新状态
	 * @param passengerInfoVO 
     * @return
     */
	@Override
	@Transactional
	public int updateBatchStatus( PassengerInfoVO passengerInfoVO) {
	    logger.debug("updateBatchStatus passengerInfo={}", JsonUtil.toJson(passengerInfoVO));
		//校验参数 
		 updateParameter(passengerInfoVO);
		int result = passengerInfoMapper.updateBatchStatus(passengerInfoVO); 
	    logger.debug("updateBatchStatus end.updateBatchStatus {} record(s).", result);
	    return result;
	}
	
    /**
     * 实体参数校验及赋值
     * @param passengerInfoVO
     */
    private void updateParameter(PassengerInfoVO passengerInfoVO) {
        if (CollectionUtils.isEmpty(passengerInfoVO.getIdList())) {
            throw new SystemException(PassengerInfoErrorCodeEnum.PASSENGERINFO_DATA_IDLIST_NOT_EMPTY.getValue()
                    , PassengerInfoErrorCodeEnum.PASSENGERINFO_DATA_IDLIST_NOT_EMPTY.getDescriptions());
        }
        long millis = DateUtil.getCurrentMillis();
        //passengerInfoVO.setUpdateTime(millis);
        //passengerInfoVO.setUpdateId(BizUserUtil.getUserID())
        //        .setUpdateName(BizUserUtil.getUserName());
    }
	
	/**
	 * 生成分布式ID并赋值
	 * @param entity 实体对象
	 */
	public void createId(PassengerInfo entity,long millis) {
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
	public void updateInfo(PassengerInfo entity,long millis) {
		//修改人
		//entity.setUpdateTime(millis);
		//entity.setUpdateId(BizUserUtil.getUserID())
		//		.setUpdateName(BizUserUtil.getUserName());
	}

    /**
     * 插入实体信息，只有当字段有值时，才会构建sql
     * @param passengerInfo 
     * @return
     */
	@Override
	@Transactional
    public PassengerInfo insertSelective(PassengerInfo passengerInfo) {
    	logger.debug("insertSelective passengerInfo={}", JsonUtil.toJson(passengerInfo));
		long millis = DateUtil.getCurrentMillis();
		//校验参数
        // TODO 排重校验
        
        
		//生成分布式ID
		createId(passengerInfo,millis);
	    int result = passengerInfoMapper.insertSelective(passengerInfo);
		PassengerInfo newPassengerInfo = null;
        if(result != 0){
            newPassengerInfo = passengerInfoMapper.getByPrimaryKey(passengerInfo);
        }
		logger.debug("insertSelective end.inserted success, entity: {}.", JsonUtil.toJson(newPassengerInfo));
	    return newPassengerInfo;
    }
	
	/**
	 * 批量插入实体信息(如果失败全部失败)
	 * @param list wms客户地址表List
	 * @return List<Long> 主键list集合
	 */
	@Override
	@Transactional
	public List<Long> insertBatch( List<PassengerInfo> list) {
		logger.debug("insertBatch list={}",  JsonUtil.toJson(list));
		long millis = DateUtil.getCurrentMillis();
		List<Long> idList = new ArrayList<Long>();
		if(list == null){
			return idList;
		}
		Map<String, List<PassengerInfo>> listMap = BizDataUtil.splitBatches(list, null);
		for (Map.Entry<String, List<PassengerInfo>> stringListEntry : listMap.entrySet()) {
			List<PassengerInfo> batchList = stringListEntry.getValue();
			logger.debug("批量写入:{}", stringListEntry.getKey());
			for (PassengerInfo entity : batchList ) {
				//校验参数
	        	
				//生成分布式ID
				createId(entity,millis);
				idList.add(entity.getId());
			}
	
			int result = passengerInfoMapper.insertBatch(batchList);
			if(result == 0){
				return new ArrayList<Long>();
			}
			logger.debug("insertBatch success, ids: {}.", JsonUtil.toJson(idList));
		}
		return idList;
	}

    /**
     * 根据主键查询实体信息
     * @param passengerInfo 
     * @return
     */
	@Override
    public PassengerInfo getByPrimaryKey(PassengerInfo passengerInfo) {
        logger.debug("getByPrimaryKey passengerInfo={}", JsonUtil.toJson(passengerInfo));
		//校验参数
        PassengerInfo result = passengerInfoMapper.getByPrimaryKey(passengerInfo);
        return result;
    }

    /**
     * 根据对象中的主键更新其他信息，只有当字段有值时，才会构建sql
     * @param passengerInfo 
     * @return
     */
	@Override
	@Transactional
    public PassengerInfo updateByPrimaryKeySelective(PassengerInfo passengerInfo) {
    	logger.debug("updateByPrimaryKeySelective passengerInfo={}", JsonUtil.toJson(passengerInfo));
		//校验参数
        checkExist(passengerInfo);
		long millis = DateUtil.getCurrentMillis();
		updateInfo(passengerInfo,millis);  
	    int result = passengerInfoMapper.updateByPrimaryKeySelective(passengerInfo);
		PassengerInfo newPassengerInfo = null;
        if(result != 0){
            newPassengerInfo = getByPrimaryKey(passengerInfo);
        }
		logger.debug("updateByPrimaryKeySelective end.updated success, entity:{} .",JsonUtil.toJson( newPassengerInfo));
	    return newPassengerInfo;
    }
    
    /**
     * 根据查询条件获取列表，不分页，就算查询条件中有分页信息，返回结果也不会分页
     * @param passengerInfoVO 查询bean
     * @return
     */
	@Override
    public List<PassengerInfo> getList(PassengerInfoVO passengerInfoVO) {
    	logger.debug("getList passengerInfoVO={}", JsonUtil.toJson(passengerInfoVO));
		//参数校验 
	    List<PassengerInfo> resultList = passengerInfoMapper.getList(passengerInfoVO);
	    return resultList;
    }
    
    /**
     * 根据查询条件获取列表，分页，如果查询条件中没有分页信息，使用默认分页信息
     * 分页信息 @see com.yestae.framework.common.domain.PageVO
     * @param passengerInfoVO 查询bean
     * @return
     */
	@Override
    public List<PassengerInfo> getPage(PassengerInfoVO passengerInfoVO) {
    	logger.debug("getPage passengerInfoVO={}", JsonUtil.toJson(passengerInfoVO));
		//参数校验 
	    List<PassengerInfo> resultList = passengerInfoMapper.getPage(passengerInfoVO);
	    return resultList;
    }
    
    /**
     * 根据查询条件获取行数
     * @param passengerInfoVO 查询bean
     * @return
     */
	@Override
    public int getCount(PassengerInfoVO passengerInfoVO) {
    	logger.debug("getCount passengerInfoVO={}", JsonUtil.toJson(passengerInfoVO));
		//参数校验 
	    int count = passengerInfoMapper.getCount(passengerInfoVO);
	    return count;
    }
	
 
	
    /**
     * 检查数据库中是否存在数据
	 * @param passengerInfo 
     * @return
     */
    public PassengerInfo checkExist(PassengerInfo passengerInfo){
        PassengerInfo oldPassengerInfo = getByPrimaryKey(passengerInfo);
        if(oldPassengerInfo == null){
          // 异常处理
            throw new SystemException(PassengerInfoErrorCodeEnum.PASSENGERINFO_DATA_UNEXIST.getValue()
                    , PassengerInfoErrorCodeEnum.PASSENGERINFO_DATA_UNEXIST.getDescriptions());
        }
        return oldPassengerInfo;
    }
}
