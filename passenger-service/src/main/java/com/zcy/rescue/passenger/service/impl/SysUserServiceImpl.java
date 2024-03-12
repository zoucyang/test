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
import com.zcy.rescue.passenger.dao.SysUserMapper;
import com.zcy.rescue.passenger.entity.SysUser;
import com.zcy.rescue.passenger.enums.SysUserErrorCodeEnum;
import com.zcy.rescue.passenger.service.SysUserService;
import com.zcy.rescue.passenger.vo.SysUserVO;
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
public class SysUserServiceImpl implements SysUserService {

	private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    /**
     * 注入的的Dao接口实现
     */
	@Autowired
	private SysUserMapper sysUserMapper;

    /**
     * 根据主键删除记录(逻辑删除)
	 * @param sysUser 
     * @return
     */
	@Override
	@Transactional
	public int deleteByPrimaryKey( SysUser sysUser) {
	    logger.debug("deleteByPrimaryKey sysUser={}", JsonUtil.toJson(sysUser));
		//校验参数
		checkExist(sysUser);
		SysUser sysUserNew = new SysUser();
		sysUserNew .setId(sysUser.getId());
		//sysUserNew .setDeleteFlag(BooleanEnum.YES.getValue());
		long millis = DateUtil.getCurrentMillis();
		updateInfo(sysUserNew,millis);
		int result = sysUserMapper.updateByPrimaryKeySelective(sysUserNew);
	    //int result = sysUserMapper.deleteByPrimaryKey(sysUser);
	    logger.debug("deleteByPrimaryKey end.deleted {} record(s).", result);
	    return result;
	}
    /**
     * 根据主键批量删除记录(逻辑删除)
	 * @param sysUserVO 
     * @return
     */
	@Override
	@Transactional
	public int deleteToUpdate( SysUserVO sysUserVO) {
	    logger.debug("deleteBatch sysUser={}", JsonUtil.toJson(sysUserVO));
		//校验参数 
		 updateParameter(sysUserVO);
		int result = sysUserMapper.deleteToUpdate(sysUserVO); 
	    logger.debug("deleteBatch end.deleteBatch {} record(s).", result);
	    return result;
	}
    /**
     * 根据主键批量更新状态
	 * @param sysUserVO 
     * @return
     */
	@Override
	@Transactional
	public int updateBatchStatus( SysUserVO sysUserVO) {
	    logger.debug("updateBatchStatus sysUser={}", JsonUtil.toJson(sysUserVO));
		//校验参数 
		 updateParameter(sysUserVO);
		int result = sysUserMapper.updateBatchStatus(sysUserVO); 
	    logger.debug("updateBatchStatus end.updateBatchStatus {} record(s).", result);
	    return result;
	}
	
    /**
     * 实体参数校验及赋值
     * @param sysUserVO
     */
    private void updateParameter(SysUserVO sysUserVO) {
        if (CollectionUtils.isEmpty(sysUserVO.getIdList())) {
            throw new SystemException(SysUserErrorCodeEnum.SYSUSER_DATA_IDLIST_NOT_EMPTY.getValue()
                    , SysUserErrorCodeEnum.SYSUSER_DATA_IDLIST_NOT_EMPTY.getDescriptions());
        }
        long millis = DateUtil.getCurrentMillis();
        //sysUserVO.setUpdateTime(millis);
        //sysUserVO.setUpdateId(BizUserUtil.getUserID())
        //        .setUpdateName(BizUserUtil.getUserName());
    }
	
	/**
	 * 生成分布式ID并赋值
	 * @param entity 实体对象
	 */
	public void createId(SysUser entity,long millis) {
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
	public void updateInfo(SysUser entity,long millis) {
		//修改人
		//entity.setUpdateTime(millis);
		//entity.setUpdateId(BizUserUtil.getUserID())
		//		.setUpdateName(BizUserUtil.getUserName());
	}

    /**
     * 插入实体信息，只有当字段有值时，才会构建sql
     * @param sysUser 
     * @return
     */
	@Override
	@Transactional
    public SysUser insertSelective(SysUser sysUser) {
    	logger.debug("insertSelective sysUser={}", JsonUtil.toJson(sysUser));
		long millis = DateUtil.getCurrentMillis();
		//校验参数
        // TODO 排重校验
        
        
		//生成分布式ID
		createId(sysUser,millis);
	    int result = sysUserMapper.insertSelective(sysUser);
		SysUser newSysUser = null;
        if(result != 0){
            newSysUser = sysUserMapper.getByPrimaryKey(sysUser);
        }
		logger.debug("insertSelective end.inserted success, entity: {}.", JsonUtil.toJson(newSysUser));
	    return newSysUser;
    }
	
	/**
	 * 批量插入实体信息(如果失败全部失败)
	 * @param list wms客户地址表List
	 * @return List<Long> 主键list集合
	 */
	@Override
	@Transactional
	public List<Long> insertBatch( List<SysUser> list) {
		logger.debug("insertBatch list={}",  JsonUtil.toJson(list));
		long millis = DateUtil.getCurrentMillis();
		List<Long> idList = new ArrayList<Long>();
		if(list == null){
			return idList;
		}
		Map<String, List<SysUser>> listMap = BizDataUtil.splitBatches(list, null);
		for (Map.Entry<String, List<SysUser>> stringListEntry : listMap.entrySet()) {
			List<SysUser> batchList = stringListEntry.getValue();
			logger.debug("批量写入:{}", stringListEntry.getKey());
			for (SysUser entity : batchList ) {
				//校验参数
	        	
				//生成分布式ID
				createId(entity,millis);
				idList.add(entity.getId());
			}
	
			int result = sysUserMapper.insertBatch(batchList);
			if(result == 0){
				return new ArrayList<Long>();
			}
			logger.debug("insertBatch success, ids: {}.", JsonUtil.toJson(idList));
		}
		return idList;
	}

    /**
     * 根据主键查询实体信息
     * @param sysUser 
     * @return
     */
	@Override
    public SysUser getByPrimaryKey(SysUser sysUser) {
        logger.debug("getByPrimaryKey sysUser={}", JsonUtil.toJson(sysUser));
		//校验参数
        SysUser result = sysUserMapper.getByPrimaryKey(sysUser);
        return result;
    }

    /**
     * 根据对象中的主键更新其他信息，只有当字段有值时，才会构建sql
     * @param sysUser 
     * @return
     */
	@Override
	@Transactional
    public SysUser updateByPrimaryKeySelective(SysUser sysUser) {
    	logger.debug("updateByPrimaryKeySelective sysUser={}", JsonUtil.toJson(sysUser));
		//校验参数
        checkExist(sysUser);
		long millis = DateUtil.getCurrentMillis();
		updateInfo(sysUser,millis);  
	    int result = sysUserMapper.updateByPrimaryKeySelective(sysUser);
		SysUser newSysUser = null;
        if(result != 0){
            newSysUser = getByPrimaryKey(sysUser);
        }
		logger.debug("updateByPrimaryKeySelective end.updated success, entity:{} .",JsonUtil.toJson( newSysUser));
	    return newSysUser;
    }
    
    /**
     * 根据查询条件获取列表，不分页，就算查询条件中有分页信息，返回结果也不会分页
     * @param sysUserVO 查询bean
     * @return
     */
	@Override
    public List<SysUser> getList(SysUserVO sysUserVO) {
    	logger.debug("getList sysUserVO={}", JsonUtil.toJson(sysUserVO));
		//参数校验 
	    List<SysUser> resultList = sysUserMapper.getList(sysUserVO);
	    return resultList;
    }
    
    /**
     * 根据查询条件获取列表，分页，如果查询条件中没有分页信息，使用默认分页信息
     * 分页信息 @see com.yestae.framework.common.domain.PageVO
     * @param sysUserVO 查询bean
     * @return
     */
	@Override
    public List<SysUser> getPage(SysUserVO sysUserVO) {
    	logger.debug("getPage sysUserVO={}", JsonUtil.toJson(sysUserVO));
		//参数校验 
	    List<SysUser> resultList = sysUserMapper.getPage(sysUserVO);
	    return resultList;
    }
    
    /**
     * 根据查询条件获取行数
     * @param sysUserVO 查询bean
     * @return
     */
	@Override
    public int getCount(SysUserVO sysUserVO) {
    	logger.debug("getCount sysUserVO={}", JsonUtil.toJson(sysUserVO));
		//参数校验 
	    int count = sysUserMapper.getCount(sysUserVO);
	    return count;
    }
	
 
	
    /**
     * 检查数据库中是否存在数据
	 * @param sysUser 
     * @return
     */
    public SysUser checkExist(SysUser sysUser){
        SysUser oldSysUser = getByPrimaryKey(sysUser);
        if(oldSysUser == null){
          // 异常处理
            throw new SystemException(SysUserErrorCodeEnum.SYSUSER_DATA_UNEXIST.getValue()
                    , SysUserErrorCodeEnum.SYSUSER_DATA_UNEXIST.getDescriptions());
        }
        return oldSysUser;
    }
}
