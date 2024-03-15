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
import com.zcy.rescue.passenger.dao.UserInfoRelationMapper;
import com.zcy.rescue.passenger.entity.UserInfoRelation;
import com.zcy.rescue.passenger.enums.error.UserInfoRelationErrorCodeEnum;
import com.zcy.rescue.passenger.service.UserInfoRelationService;
import com.zcy.rescue.passenger.vo.UserInfoRelationVO;
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
 * 用户信息关系表的Service实现
 * 
 * @author zcy
 * @date 2024-2-5
 */
@Service
public class UserInfoRelationServiceImpl implements UserInfoRelationService {

	private static final Logger logger = LoggerFactory.getLogger(UserInfoRelationServiceImpl.class);

    /**
     * 注入的用户信息关系表的Dao接口实现
     */
	@Autowired
	private UserInfoRelationMapper userInfoRelationMapper;

    /**
     * 根据主键删除记录(逻辑删除)
	 * @param userInfoRelation 用户信息关系表
     * @return
     */
	@Override
	@Transactional
	public int deleteByPrimaryKey( UserInfoRelation userInfoRelation) {
	    logger.debug("deleteByPrimaryKey userInfoRelation={}", JsonUtil.toJson(userInfoRelation));
		//校验参数
		checkExist(userInfoRelation);
		UserInfoRelation userInfoRelationNew = new UserInfoRelation();
		userInfoRelationNew .setId(userInfoRelation.getId());
		//userInfoRelationNew .setDeleteFlag(BooleanEnum.YES.getValue());
		long millis = DateUtil.getCurrentMillis();
		updateInfo(userInfoRelationNew,millis);
		int result = userInfoRelationMapper.updateByPrimaryKeySelective(userInfoRelationNew);
	    //int result = userInfoRelationMapper.deleteByPrimaryKey(userInfoRelation);
	    logger.debug("deleteByPrimaryKey end.deleted {} record(s).", result);
	    return result;
	}
    /**
     * 根据主键批量删除记录(逻辑删除)
	 * @param userInfoRelationVO 用户信息关系表
     * @return
     */
	@Override
	@Transactional
	public int deleteToUpdate( UserInfoRelationVO userInfoRelationVO) {
	    logger.debug("deleteBatch userInfoRelation={}", JsonUtil.toJson(userInfoRelationVO));
		//校验参数 
		 updateParameter(userInfoRelationVO);
		int result = userInfoRelationMapper.deleteToUpdate(userInfoRelationVO); 
	    logger.debug("deleteBatch end.deleteBatch {} record(s).", result);
	    return result;
	}
    /**
     * 根据主键批量更新状态
	 * @param userInfoRelationVO 用户信息关系表
     * @return
     */
	@Override
	@Transactional
	public int updateBatchStatus( UserInfoRelationVO userInfoRelationVO) {
	    logger.debug("updateBatchStatus userInfoRelation={}", JsonUtil.toJson(userInfoRelationVO));
		//校验参数 
		 updateParameter(userInfoRelationVO);
		int result = userInfoRelationMapper.updateBatchStatus(userInfoRelationVO); 
	    logger.debug("updateBatchStatus end.updateBatchStatus {} record(s).", result);
	    return result;
	}
	
    /**
     * 实体参数校验及赋值
     * @param userInfoRelationVO
     */
    private void updateParameter(UserInfoRelationVO userInfoRelationVO) {
        if (CollectionUtils.isEmpty(userInfoRelationVO.getIdList())) {
            throw new SystemException(UserInfoRelationErrorCodeEnum.USERINFORELATION_DATA_IDLIST_NOT_EMPTY.getValue()
                    , UserInfoRelationErrorCodeEnum.USERINFORELATION_DATA_IDLIST_NOT_EMPTY.getDescriptions());
        }
        long millis = DateUtil.getCurrentMillis();
        userInfoRelationVO.setUpdateTime(millis);
        //userInfoRelationVO.setUpdateId(BizUserUtil.getUserID())
        //        .setUpdateName(BizUserUtil.getUserName());
    }
	
	/**
	 * 生成分布式ID并赋值
	 * @param entity 实体对象
	 */
	public void createId(UserInfoRelation entity,long millis) {
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
	public void updateInfo(UserInfoRelation entity,long millis) {
		//修改人
		//entity.setUpdateTime(millis);
		//entity.setUpdateId(BizUserUtil.getUserID())
		//		.setUpdateName(BizUserUtil.getUserName());
	}

    /**
     * 插入实体信息，只有当字段有值时，才会构建sql
     * @param userInfoRelation 用户信息关系表
     * @return
     */
	@Override
	@Transactional
    public UserInfoRelation insertSelective(UserInfoRelation userInfoRelation) {
    	logger.debug("insertSelective userInfoRelation={}", JsonUtil.toJson(userInfoRelation));
		long millis = DateUtil.getCurrentMillis();
		//校验参数
        // TODO 排重校验
        
        
		//生成分布式ID
		createId(userInfoRelation,millis);
	    int result = userInfoRelationMapper.insertSelective(userInfoRelation);
		UserInfoRelation newUserInfoRelation = null;
        if(result != 0){
            newUserInfoRelation = userInfoRelationMapper.getByPrimaryKey(userInfoRelation);
        }
		logger.debug("insertSelective end.inserted success, entity: {}.", JsonUtil.toJson(newUserInfoRelation));
	    return newUserInfoRelation;
    }
	
	/**
	 * 批量插入实体信息(如果失败全部失败)
	 * @param list wms客户地址表List
	 * @return List<Long> 主键list集合
	 */
	@Override
	@Transactional
	public List<Long> insertBatch( List<UserInfoRelation> list) {
		logger.debug("insertBatch list={}",  JsonUtil.toJson(list));
		long millis = DateUtil.getCurrentMillis();
		List<Long> idList = new ArrayList<Long>();
		if(list == null){
			return idList;
		}
		Map<String, List<UserInfoRelation>> listMap = BizDataUtil.splitBatches(list, null);
		for (Map.Entry<String, List<UserInfoRelation>> stringListEntry : listMap.entrySet()) {
			List<UserInfoRelation> batchList = stringListEntry.getValue();
			logger.debug("批量写入:{}", stringListEntry.getKey());
			for (UserInfoRelation entity : batchList ) {
				//校验参数
	        	
				//生成分布式ID
				createId(entity,millis);
				//idList.add(entity.getId());
			}
	
			int result = userInfoRelationMapper.insertBatch(batchList);
			if(result == 0){
				return new ArrayList<Long>();
			}
			logger.debug("insertBatch success, ids: {}.", JsonUtil.toJson(idList));
		}
		return idList;
	}

    /**
     * 根据主键查询实体信息
     * @param userInfoRelation 用户信息关系表
     * @return
     */
	@Override
    public UserInfoRelation getByPrimaryKey(UserInfoRelation userInfoRelation) {
        logger.debug("getByPrimaryKey userInfoRelation={}", JsonUtil.toJson(userInfoRelation));
		//校验参数
        UserInfoRelation result = userInfoRelationMapper.getByPrimaryKey(userInfoRelation);
        return result;
    }

    /**
     * 根据对象中的主键更新其他信息，只有当字段有值时，才会构建sql
     * @param userInfoRelation 用户信息关系表
     * @return
     */
	@Override
	@Transactional
    public UserInfoRelation updateByPrimaryKeySelective(UserInfoRelation userInfoRelation) {
    	logger.debug("updateByPrimaryKeySelective userInfoRelation={}", JsonUtil.toJson(userInfoRelation));
		//校验参数
        checkExist(userInfoRelation);
		long millis = DateUtil.getCurrentMillis();
		updateInfo(userInfoRelation,millis);  
	    int result = userInfoRelationMapper.updateByPrimaryKeySelective(userInfoRelation);
		UserInfoRelation newUserInfoRelation = null;
        if(result != 0){
            newUserInfoRelation = getByPrimaryKey(userInfoRelation);
        }
		logger.debug("updateByPrimaryKeySelective end.updated success, entity:{} .",JsonUtil.toJson( newUserInfoRelation));
	    return newUserInfoRelation;
    }
    
    /**
     * 根据查询条件获取列表，不分页，就算查询条件中有分页信息，返回结果也不会分页
     * @param userInfoRelationVO 用户信息关系表查询bean
     * @return
     */
	@Override
    public List<UserInfoRelation> getList(UserInfoRelationVO userInfoRelationVO) {
    	logger.debug("getList userInfoRelationVO={}", JsonUtil.toJson(userInfoRelationVO));
		//参数校验 
	    List<UserInfoRelation> resultList = userInfoRelationMapper.getList(userInfoRelationVO);
	    return resultList;
    }
    
    /**
     * 根据查询条件获取列表，分页，如果查询条件中没有分页信息，使用默认分页信息
     * 分页信息 @see com.yestae.framework.common.domain.PageVO
     * @param userInfoRelationVO 用户信息关系表查询bean
     * @return
     */
	@Override
    public List<UserInfoRelation> getPage(UserInfoRelationVO userInfoRelationVO) {
    	logger.debug("getPage userInfoRelationVO={}", JsonUtil.toJson(userInfoRelationVO));
		//参数校验 
	    List<UserInfoRelation> resultList = userInfoRelationMapper.getPage(userInfoRelationVO);
	    return resultList;
    }
    
    /**
     * 根据查询条件获取行数
     * @param userInfoRelationVO 用户信息关系表查询bean
     * @return
     */
	@Override
    public int getCount(UserInfoRelationVO userInfoRelationVO) {
    	logger.debug("getCount userInfoRelationVO={}", JsonUtil.toJson(userInfoRelationVO));
		//参数校验 
	    int count = userInfoRelationMapper.getCount(userInfoRelationVO);
	    return count;
    }
	
 
	
    /**
     * 检查数据库中是否存在数据
	 * @param userInfoRelation 用户信息关系表
     * @return
     */
    public UserInfoRelation checkExist(UserInfoRelation userInfoRelation){
        UserInfoRelation oldUserInfoRelation = getByPrimaryKey(userInfoRelation);
        if(oldUserInfoRelation == null){
          // 异常处理
            throw new SystemException(UserInfoRelationErrorCodeEnum.USERINFORELATION_DATA_UNEXIST.getValue()
                    , UserInfoRelationErrorCodeEnum.USERINFORELATION_DATA_UNEXIST.getDescriptions());
        }
        return oldUserInfoRelation;
    }
}
