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
import com.zcy.rescue.passenger.dao.ServiceMapper;
import com.zcy.rescue.passenger.entity.ServiceProject;
import com.zcy.rescue.passenger.enums.ServiceErrorCodeEnum;
import com.zcy.rescue.passenger.service.ServiceService;
import com.zcy.rescue.passenger.vo.ServiceVO;
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
public class ServiceServiceImpl implements ServiceService {

	private static final Logger logger = LoggerFactory.getLogger(ServiceServiceImpl.class);

    /**
     * 注入的的Dao接口实现
     */
	@Autowired
	private ServiceMapper serviceMapper;

    /**
     * 根据主键删除记录(逻辑删除)
	 * @param service 
     * @return
     */
	@Override
	@Transactional
	public int deleteByPrimaryKey( ServiceProject service) {
	    logger.debug("deleteByPrimaryKey service={}", JsonUtil.toJson(service));
		//校验参数
		checkExist(service);
		ServiceProject serviceNew = new ServiceProject();
		serviceNew .setId(service.getId());
		//serviceNew .setDeleteFlag(BooleanEnum.YES.getValue());
		long millis = DateUtil.getCurrentMillis();
		updateInfo(serviceNew,millis);
		int result = serviceMapper.updateByPrimaryKeySelective(serviceNew);
	    //int result = serviceMapper.deleteByPrimaryKey(service);
	    logger.debug("deleteByPrimaryKey end.deleted {} record(s).", result);
	    return result;
	}
    /**
     * 根据主键批量删除记录(逻辑删除)
	 * @param serviceVO 
     * @return
     */
	@Override
	@Transactional
	public int deleteToUpdate( ServiceVO serviceVO) {
	    logger.debug("deleteBatch service={}", JsonUtil.toJson(serviceVO));
		//校验参数 
		 updateParameter(serviceVO);
		int result = serviceMapper.deleteToUpdate(serviceVO); 
	    logger.debug("deleteBatch end.deleteBatch {} record(s).", result);
	    return result;
	}
    /**
     * 根据主键批量更新状态
	 * @param serviceVO 
     * @return
     */
	@Override
	@Transactional
	public int updateBatchStatus( ServiceVO serviceVO) {
	    logger.debug("updateBatchStatus service={}", JsonUtil.toJson(serviceVO));
		//校验参数 
		 updateParameter(serviceVO);
		int result = serviceMapper.updateBatchStatus(serviceVO); 
	    logger.debug("updateBatchStatus end.updateBatchStatus {} record(s).", result);
	    return result;
	}
	
    /**
     * 实体参数校验及赋值
     * @param serviceVO
     */
    private void updateParameter(ServiceVO serviceVO) {
        if (CollectionUtils.isEmpty(serviceVO.getIdList())) {
            throw new SystemException(ServiceErrorCodeEnum.SERVICE_DATA_IDLIST_NOT_EMPTY.getValue()
                    , ServiceErrorCodeEnum.SERVICE_DATA_IDLIST_NOT_EMPTY.getDescriptions());
        }
        long millis = DateUtil.getCurrentMillis();
        //serviceVO.setUpdateTime(millis);
        //serviceVO.setUpdateId(BizUserUtil.getUserID())
        //        .setUpdateName(BizUserUtil.getUserName());
    }
	
	/**
	 * 生成分布式ID并赋值
	 * @param entity 实体对象
	 */
	public void createId(ServiceProject entity, long millis) {
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
	public void updateInfo(ServiceProject entity, long millis) {
		//修改人
		//entity.setUpdateTime(millis);
		//entity.setUpdateId(BizUserUtil.getUserID())
		//		.setUpdateName(BizUserUtil.getUserName());
	}

    /**
     * 插入实体信息，只有当字段有值时，才会构建sql
     * @param service 
     * @return
     */
	@Override
	@Transactional
    public ServiceProject insertSelective(ServiceProject service) {
    	logger.debug("insertSelective service={}", JsonUtil.toJson(service));
		long millis = DateUtil.getCurrentMillis();
		//校验参数
        // TODO 排重校验
        
        
		//生成分布式ID
		createId(service,millis);
	    int result = serviceMapper.insertSelective(service);
		ServiceProject newService = null;
        if(result != 0){
            newService = serviceMapper.getByPrimaryKey(service);
        }
		logger.debug("insertSelective end.inserted success, entity: {}.", JsonUtil.toJson(newService));
	    return newService;
    }
	
	/**
	 * 批量插入实体信息(如果失败全部失败)
	 * @param list wms客户地址表List
	 * @return List<Long> 主键list集合
	 */
	@Override
	@Transactional
	public List<Long> insertBatch( List<ServiceProject> list) {
		logger.debug("insertBatch list={}",  JsonUtil.toJson(list));
		long millis = DateUtil.getCurrentMillis();
		List<Long> idList = new ArrayList<Long>();
		if(list == null){
			return idList;
		}
		Map<String, List<ServiceProject>> listMap = BizDataUtil.splitBatches(list, null);
		for (Map.Entry<String, List<ServiceProject>> stringListEntry : listMap.entrySet()) {
			List<ServiceProject> batchList = stringListEntry.getValue();
			logger.debug("批量写入:{}", stringListEntry.getKey());
			for (ServiceProject entity : batchList ) {
				//校验参数
	        	
				//生成分布式ID
				createId(entity,millis);
				idList.add(entity.getId());
			}
	
			int result = serviceMapper.insertBatch(batchList);
			if(result == 0){
				return new ArrayList<Long>();
			}
			logger.debug("insertBatch success, ids: {}.", JsonUtil.toJson(idList));
		}
		return idList;
	}

    /**
     * 根据主键查询实体信息
     * @param service 
     * @return
     */
	@Override
    public ServiceProject getByPrimaryKey(ServiceProject service) {
        logger.debug("getByPrimaryKey service={}", JsonUtil.toJson(service));
		//校验参数
        ServiceProject result = serviceMapper.getByPrimaryKey(service);
        return result;
    }

    /**
     * 根据对象中的主键更新其他信息，只有当字段有值时，才会构建sql
     * @param service 
     * @return
     */
	@Override
	@Transactional
    public ServiceProject updateByPrimaryKeySelective(ServiceProject service) {
    	logger.debug("updateByPrimaryKeySelective service={}", JsonUtil.toJson(service));
		//校验参数
        checkExist(service);
		long millis = DateUtil.getCurrentMillis();
		updateInfo(service,millis);  
	    int result = serviceMapper.updateByPrimaryKeySelective(service);
		ServiceProject newService = null;
        if(result != 0){
            newService = getByPrimaryKey(service);
        }
		logger.debug("updateByPrimaryKeySelective end.updated success, entity:{} .",JsonUtil.toJson( newService));
	    return newService;
    }
    
    /**
     * 根据查询条件获取列表，不分页，就算查询条件中有分页信息，返回结果也不会分页
     * @param serviceVO 查询bean
     * @return
     */
	@Override
    public List<ServiceProject> getList(ServiceVO serviceVO) {
    	logger.debug("getList serviceVO={}", JsonUtil.toJson(serviceVO));
		//参数校验 
	    List<ServiceProject> resultList = serviceMapper.getList(serviceVO);
	    return resultList;
    }
    
    /**
     * 根据查询条件获取列表，分页，如果查询条件中没有分页信息，使用默认分页信息
     * 分页信息 @see com.yestae.framework.common.domain.PageVO
     * @param serviceVO 查询bean
     * @return
     */
	@Override
    public List<ServiceProject> getPage(ServiceVO serviceVO) {
    	logger.debug("getPage serviceVO={}", JsonUtil.toJson(serviceVO));
		//参数校验 
	    List<ServiceProject> resultList = serviceMapper.getPage(serviceVO);
	    return resultList;
    }
    
    /**
     * 根据查询条件获取行数
     * @param serviceVO 查询bean
     * @return
     */
	@Override
    public int getCount(ServiceVO serviceVO) {
    	logger.debug("getCount serviceVO={}", JsonUtil.toJson(serviceVO));
		//参数校验 
	    int count = serviceMapper.getCount(serviceVO);
	    return count;
    }
	
 
	
    /**
     * 检查数据库中是否存在数据
	 * @param service 
     * @return
     */
    public ServiceProject checkExist(ServiceProject service){
        ServiceProject oldService = getByPrimaryKey(service);
        if(oldService == null){
          // 异常处理
            throw new SystemException(ServiceErrorCodeEnum.SERVICE_DATA_UNEXIST.getValue()
                    , ServiceErrorCodeEnum.SERVICE_DATA_UNEXIST.getDescriptions());
        }
        return oldService;
    }
}
