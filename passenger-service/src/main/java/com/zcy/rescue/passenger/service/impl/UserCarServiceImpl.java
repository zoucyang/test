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
import com.zcy.rescue.passenger.dao.UserCarMapper;
import com.zcy.rescue.passenger.entity.UserCar;
import com.zcy.rescue.passenger.enums.error.UserCarErrorCodeEnum;
import com.zcy.rescue.passenger.service.UserCarService;
import com.zcy.rescue.passenger.vo.UserCarVO;
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
 * 用户车辆关系表的Service实现
 * 
 * @author zcy
 * @date 2024-1-28
 */
@Service
public class UserCarServiceImpl implements UserCarService {

	private static final Logger logger = LoggerFactory.getLogger(UserCarServiceImpl.class);

    /**
     * 注入的用户车辆关系表的Dao接口实现
     */
	@Autowired
	private UserCarMapper userCarMapper;

    /**
     * 根据主键删除记录(逻辑删除)
	 * @param userCar 用户车辆关系表
     * @return
     */
	@Override
	@Transactional
	public int deleteByPrimaryKey( UserCar userCar) {
	    logger.debug("deleteByPrimaryKey userCar={}", JsonUtil.toJson(userCar));
		//校验参数
		checkExist(userCar);
		UserCar userCarNew = new UserCar();
		userCarNew .setId(userCar.getId());
		//userCarNew .setDeleteFlag(BooleanEnum.YES.getValue());
		long millis = DateUtil.getCurrentMillis();
		updateInfo(userCarNew,millis);
		int result = userCarMapper.updateByPrimaryKeySelective(userCarNew);
	    //int result = userCarMapper.deleteByPrimaryKey(userCar);
	    logger.debug("deleteByPrimaryKey end.deleted {} record(s).", result);
	    return result;
	}
    /**
     * 根据主键批量删除记录(逻辑删除)
	 * @param userCarVO 用户车辆关系表
     * @return
     */
	@Override
	@Transactional
	public int deleteToUpdate( UserCarVO userCarVO) {
	    logger.debug("deleteBatch userCar={}", JsonUtil.toJson(userCarVO));
		//校验参数 
		 updateParameter(userCarVO);
		int result = userCarMapper.deleteToUpdate(userCarVO); 
	    logger.debug("deleteBatch end.deleteBatch {} record(s).", result);
	    return result;
	}
    /**
     * 根据主键批量更新状态
	 * @param userCarVO 用户车辆关系表
     * @return
     */
	@Override
	@Transactional
	public int updateBatchStatus( UserCarVO userCarVO) {
	    logger.debug("updateBatchStatus userCar={}", JsonUtil.toJson(userCarVO));
		//校验参数 
		 updateParameter(userCarVO);
		int result = userCarMapper.updateBatchStatus(userCarVO); 
	    logger.debug("updateBatchStatus end.updateBatchStatus {} record(s).", result);
	    return result;
	}
	
    /**
     * 实体参数校验及赋值
     * @param userCarVO
     */
    private void updateParameter(UserCarVO userCarVO) {
        if (CollectionUtils.isEmpty(userCarVO.getIdList())) {
            throw new SystemException(UserCarErrorCodeEnum.USERCAR_DATA_IDLIST_NOT_EMPTY.getValue()
                    , UserCarErrorCodeEnum.USERCAR_DATA_IDLIST_NOT_EMPTY.getDescriptions());
        }
        long millis = DateUtil.getCurrentMillis();
        //userCarVO.setUpdateTime(millis);
        //userCarVO.setUpdateId(BizUserUtil.getUserID())
        //        .setUpdateName(BizUserUtil.getUserName());
    }
	
	/**
	 * 生成分布式ID并赋值
	 * @param entity 实体对象
	 */
	public void createId(UserCar entity,long millis) {
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
	public void updateInfo(UserCar entity,long millis) {
		//修改人
		//entity.setUpdateTime(millis);
		//entity.setUpdateId(BizUserUtil.getUserID())
		//		.setUpdateName(BizUserUtil.getUserName());
	}

    /**
     * 插入实体信息，只有当字段有值时，才会构建sql
     * @param userCar 用户车辆关系表
     * @return
     */
	@Override
	@Transactional
    public UserCar insertSelective(UserCar userCar) {
    	logger.debug("insertSelective userCar={}", JsonUtil.toJson(userCar));
		long millis = DateUtil.getCurrentMillis();
		//校验参数
        // TODO 排重校验
        
        
		//生成分布式ID
		createId(userCar,millis);
	    int result = userCarMapper.insertSelective(userCar);
		UserCar newUserCar = null;
        if(result != 0){
            newUserCar = userCarMapper.getByPrimaryKey(userCar);
        }
		logger.debug("insertSelective end.inserted success, entity: {}.", JsonUtil.toJson(newUserCar));
	    return newUserCar;
    }
	
	/**
	 * 批量插入实体信息(如果失败全部失败)
	 * @param list wms客户地址表List
	 * @return List<Long> 主键list集合
	 */
	@Override
	@Transactional
	public List<Long> insertBatch( List<UserCar> list) {
		logger.debug("insertBatch list={}",  JsonUtil.toJson(list));
		long millis = DateUtil.getCurrentMillis();
		List<Long> idList = new ArrayList<Long>();
		if(list == null){
			return idList;
		}
		Map<String, List<UserCar>> listMap = BizDataUtil.splitBatches(list, null);
		for (Map.Entry<String, List<UserCar>> stringListEntry : listMap.entrySet()) {
			List<UserCar> batchList = stringListEntry.getValue();
			logger.debug("批量写入:{}", stringListEntry.getKey());
			for (UserCar entity : batchList ) {
				//校验参数
	        	
				//生成分布式ID
				createId(entity,millis);
				idList.add(entity.getId());
			}
	
			int result = userCarMapper.insertBatch(batchList);
			if(result == 0){
				return new ArrayList<Long>();
			}
			logger.debug("insertBatch success, ids: {}.", JsonUtil.toJson(idList));
		}
		return idList;
	}

    /**
     * 根据主键查询实体信息
     * @param userCar 用户车辆关系表
     * @return
     */
	@Override
    public UserCar getByPrimaryKey(UserCar userCar) {
        logger.debug("getByPrimaryKey userCar={}", JsonUtil.toJson(userCar));
		//校验参数
        UserCar result = userCarMapper.getByPrimaryKey(userCar);
        return result;
    }

    /**
     * 根据对象中的主键更新其他信息，只有当字段有值时，才会构建sql
     * @param userCar 用户车辆关系表
     * @return
     */
	@Override
	@Transactional
    public UserCar updateByPrimaryKeySelective(UserCar userCar) {
    	logger.debug("updateByPrimaryKeySelective userCar={}", JsonUtil.toJson(userCar));
		//校验参数
        checkExist(userCar);
		long millis = DateUtil.getCurrentMillis();
		updateInfo(userCar,millis);  
	    int result = userCarMapper.updateByPrimaryKeySelective(userCar);
		UserCar newUserCar = null;
        if(result != 0){
            newUserCar = getByPrimaryKey(userCar);
        }
		logger.debug("updateByPrimaryKeySelective end.updated success, entity:{} .",JsonUtil.toJson( newUserCar));
	    return newUserCar;
    }
    
    /**
     * 根据查询条件获取列表，不分页，就算查询条件中有分页信息，返回结果也不会分页
     * @param userCarVO 用户车辆关系表查询bean
     * @return
     */
	@Override
    public List<UserCar> getList(UserCarVO userCarVO) {
    	logger.debug("getList userCarVO={}", JsonUtil.toJson(userCarVO));
		//参数校验 
	    List<UserCar> resultList = userCarMapper.getList(userCarVO);
	    return resultList;
    }
    
    /**
     * 根据查询条件获取列表，分页，如果查询条件中没有分页信息，使用默认分页信息
     * 分页信息 @see com.yestae.framework.common.domain.PageVO
     * @param userCarVO 用户车辆关系表查询bean
     * @return
     */
	@Override
    public List<UserCar> getPage(UserCarVO userCarVO) {
    	logger.debug("getPage userCarVO={}", JsonUtil.toJson(userCarVO));
		//参数校验 
	    List<UserCar> resultList = userCarMapper.getPage(userCarVO);
	    return resultList;
    }
    
    /**
     * 根据查询条件获取行数
     * @param userCarVO 用户车辆关系表查询bean
     * @return
     */
	@Override
    public int getCount(UserCarVO userCarVO) {
    	logger.debug("getCount userCarVO={}", JsonUtil.toJson(userCarVO));
		//参数校验 
	    int count = userCarMapper.getCount(userCarVO);
	    return count;
    }
	
 
	
    /**
     * 检查数据库中是否存在数据
	 * @param userCar 用户车辆关系表
     * @return
     */
    public UserCar checkExist(UserCar userCar){
        UserCar oldUserCar = getByPrimaryKey(userCar);
        if(oldUserCar == null){
          // 异常处理
            throw new SystemException(UserCarErrorCodeEnum.USERCAR_DATA_UNEXIST.getValue()
                    , UserCarErrorCodeEnum.USERCAR_DATA_UNEXIST.getDescriptions());
        }
        return oldUserCar;
    }
}
