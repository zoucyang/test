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
import com.zcy.rescue.passenger.dao.DriverInfoMapper;
import com.zcy.rescue.passenger.entity.DriverInfo;
import com.zcy.rescue.passenger.enums.DriverInfoErrorCodeEnum;
import com.zcy.rescue.passenger.service.DriverInfoService;
import com.zcy.rescue.passenger.vo.DriverInfoVO;
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
 * 司机信息表的Service实现
 * 
 * @author zcy
 * @date 2024-1-28
 */
@Service
public class DriverInfoServiceImpl implements DriverInfoService {

	private static final Logger logger = LoggerFactory.getLogger(DriverInfoServiceImpl.class);

    /**
     * 注入的司机信息表的Dao接口实现
     */
	@Autowired
	private DriverInfoMapper driverInfoMapper;

    /**
     * 根据主键删除记录(逻辑删除)
	 * @param driverInfo 司机信息表
     * @return
     */
	@Override
	@Transactional
	public int deleteByPrimaryKey( DriverInfo driverInfo) {
	    logger.debug("deleteByPrimaryKey driverInfo={}", JsonUtil.toJson(driverInfo));
		//校验参数
		checkExist(driverInfo);
		DriverInfo driverInfoNew = new DriverInfo();
		driverInfoNew .setId(driverInfo.getId());
		//driverInfoNew .setDeleteFlag(BooleanEnum.YES.getValue());
		long millis = DateUtil.getCurrentMillis();
		updateInfo(driverInfoNew,millis);
		int result = driverInfoMapper.updateByPrimaryKeySelective(driverInfoNew);
	    //int result = driverInfoMapper.deleteByPrimaryKey(driverInfo);
	    logger.debug("deleteByPrimaryKey end.deleted {} record(s).", result);
	    return result;
	}
    /**
     * 根据主键批量删除记录(逻辑删除)
	 * @param driverInfoVO 司机信息表
     * @return
     */
	@Override
	@Transactional
	public int deleteToUpdate( DriverInfoVO driverInfoVO) {
	    logger.debug("deleteBatch driverInfo={}", JsonUtil.toJson(driverInfoVO));
		//校验参数 
		 updateParameter(driverInfoVO);
		int result = driverInfoMapper.deleteToUpdate(driverInfoVO); 
	    logger.debug("deleteBatch end.deleteBatch {} record(s).", result);
	    return result;
	}
    /**
     * 根据主键批量更新状态
	 * @param driverInfoVO 司机信息表
     * @return
     */
	@Override
	@Transactional
	public int updateBatchStatus( DriverInfoVO driverInfoVO) {
	    logger.debug("updateBatchStatus driverInfo={}", JsonUtil.toJson(driverInfoVO));
		//校验参数 
		 updateParameter(driverInfoVO);
		int result = driverInfoMapper.updateBatchStatus(driverInfoVO); 
	    logger.debug("updateBatchStatus end.updateBatchStatus {} record(s).", result);
	    return result;
	}
	
    /**
     * 实体参数校验及赋值
     * @param driverInfoVO
     */
    private void updateParameter(DriverInfoVO driverInfoVO) {
        if (CollectionUtils.isEmpty(driverInfoVO.getIdList())) {
            throw new SystemException(DriverInfoErrorCodeEnum.DRIVERINFO_DATA_IDLIST_NOT_EMPTY.getValue()
                    , DriverInfoErrorCodeEnum.DRIVERINFO_DATA_IDLIST_NOT_EMPTY.getDescriptions());
        }
        long millis = DateUtil.getCurrentMillis();
        //driverInfoVO.setUpdateTime(millis);
        //driverInfoVO.setUpdateId(BizUserUtil.getUserID())
        //        .setUpdateName(BizUserUtil.getUserName());
    }
	
	/**
	 * 生成分布式ID并赋值
	 * @param entity 实体对象
	 */
	public void createId(DriverInfo entity,long millis) {
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
	public void updateInfo(DriverInfo entity,long millis) {
		//修改人
		//entity.setUpdateTime(millis);
		//entity.setUpdateId(BizUserUtil.getUserID())
		//		.setUpdateName(BizUserUtil.getUserName());
	}

    /**
     * 插入实体信息，只有当字段有值时，才会构建sql
     * @param driverInfo 司机信息表
     * @return
     */
	@Override
	@Transactional
    public DriverInfo insertSelective(DriverInfo driverInfo) {
    	logger.debug("insertSelective driverInfo={}", JsonUtil.toJson(driverInfo));
		long millis = DateUtil.getCurrentMillis();
		//校验参数
        // TODO 排重校验
        
        
		//生成分布式ID
		createId(driverInfo,millis);
	    int result = driverInfoMapper.insertSelective(driverInfo);
		DriverInfo newDriverInfo = null;
        if(result != 0){
            newDriverInfo = driverInfoMapper.getByPrimaryKey(driverInfo);
        }
		logger.debug("insertSelective end.inserted success, entity: {}.", JsonUtil.toJson(newDriverInfo));
	    return newDriverInfo;
    }
	
	/**
	 * 批量插入实体信息(如果失败全部失败)
	 * @param list wms客户地址表List
	 * @return List<Long> 主键list集合
	 */
	@Override
	@Transactional
	public List<Long> insertBatch( List<DriverInfo> list) {
		logger.debug("insertBatch list={}",  JsonUtil.toJson(list));
		long millis = DateUtil.getCurrentMillis();
		List<Long> idList = new ArrayList<Long>();
		if(list == null){
			return idList;
		}
		Map<String, List<DriverInfo>> listMap = BizDataUtil.splitBatches(list, null);
		for (Map.Entry<String, List<DriverInfo>> stringListEntry : listMap.entrySet()) {
			List<DriverInfo> batchList = stringListEntry.getValue();
			logger.debug("批量写入:{}", stringListEntry.getKey());
			for (DriverInfo entity : batchList ) {
				//校验参数
	        	
				//生成分布式ID
				createId(entity,millis);
				idList.add(entity.getId());
			}
	
			int result = driverInfoMapper.insertBatch(batchList);
			if(result == 0){
				return new ArrayList<Long>();
			}
			logger.debug("insertBatch success, ids: {}.", JsonUtil.toJson(idList));
		}
		return idList;
	}

    /**
     * 根据主键查询实体信息
     * @param driverInfo 司机信息表
     * @return
     */
	@Override
    public DriverInfo getByPrimaryKey(DriverInfo driverInfo) {
        logger.debug("getByPrimaryKey driverInfo={}", JsonUtil.toJson(driverInfo));
		//校验参数
        DriverInfo result = driverInfoMapper.getByPrimaryKey(driverInfo);
        return result;
    }

    /**
     * 根据对象中的主键更新其他信息，只有当字段有值时，才会构建sql
     * @param driverInfo 司机信息表
     * @return
     */
	@Override
	@Transactional
    public DriverInfo updateByPrimaryKeySelective(DriverInfo driverInfo) {
    	logger.debug("updateByPrimaryKeySelective driverInfo={}", JsonUtil.toJson(driverInfo));
		//校验参数
        checkExist(driverInfo);
		long millis = DateUtil.getCurrentMillis();
		updateInfo(driverInfo,millis);  
	    int result = driverInfoMapper.updateByPrimaryKeySelective(driverInfo);
		DriverInfo newDriverInfo = null;
        if(result != 0){
            newDriverInfo = getByPrimaryKey(driverInfo);
        }
		logger.debug("updateByPrimaryKeySelective end.updated success, entity:{} .",JsonUtil.toJson( newDriverInfo));
	    return newDriverInfo;
    }
    
    /**
     * 根据查询条件获取列表，不分页，就算查询条件中有分页信息，返回结果也不会分页
     * @param driverInfoVO 司机信息表查询bean
     * @return
     */
	@Override
    public List<DriverInfo> getList(DriverInfoVO driverInfoVO) {
    	logger.debug("getList driverInfoVO={}", JsonUtil.toJson(driverInfoVO));
		//参数校验 
	    List<DriverInfo> resultList = driverInfoMapper.getList(driverInfoVO);
	    return resultList;
    }
    
    /**
     * 根据查询条件获取列表，分页，如果查询条件中没有分页信息，使用默认分页信息
     * 分页信息 @see com.yestae.framework.common.domain.PageVO
     * @param driverInfoVO 司机信息表查询bean
     * @return
     */
	@Override
    public List<DriverInfo> getPage(DriverInfoVO driverInfoVO) {
    	logger.debug("getPage driverInfoVO={}", JsonUtil.toJson(driverInfoVO));
		//参数校验 
	    List<DriverInfo> resultList = driverInfoMapper.getPage(driverInfoVO);
	    return resultList;
    }
    
    /**
     * 根据查询条件获取行数
     * @param driverInfoVO 司机信息表查询bean
     * @return
     */
	@Override
    public int getCount(DriverInfoVO driverInfoVO) {
    	logger.debug("getCount driverInfoVO={}", JsonUtil.toJson(driverInfoVO));
		//参数校验 
	    int count = driverInfoMapper.getCount(driverInfoVO);
	    return count;
    }
	
 
	
    /**
     * 检查数据库中是否存在数据
	 * @param driverInfo 司机信息表
     * @return
     */
    public DriverInfo checkExist(DriverInfo driverInfo){
        DriverInfo oldDriverInfo = getByPrimaryKey(driverInfo);
        if(oldDriverInfo == null){
          // 异常处理
            throw new SystemException(DriverInfoErrorCodeEnum.DRIVERINFO_DATA_UNEXIST.getValue()
                    , DriverInfoErrorCodeEnum.DRIVERINFO_DATA_UNEXIST.getDescriptions());
        }
        return oldDriverInfo;
    }
}
