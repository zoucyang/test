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
import com.zcy.rescue.passenger.dao.ChartInfoMapper;
import com.zcy.rescue.passenger.entity.ChartInfo;
import com.zcy.rescue.passenger.enums.error.ChartInfoErrorCodeEnum;
import com.zcy.rescue.passenger.service.ChartInfoService;
import com.zcy.rescue.passenger.vo.ChartInfoVO;
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
 * 微信信息表的Service实现
 * 
 * @author zcy
 * @date 2024-2-5
 */
@Service
public class ChartInfoServiceImpl implements ChartInfoService {

	private static final Logger logger = LoggerFactory.getLogger(ChartInfoServiceImpl.class);

    /**
     * 注入的微信信息表的Dao接口实现
     */
	@Autowired
	private ChartInfoMapper chartInfoMapper;

    /**
     * 根据主键删除记录(逻辑删除)
	 * @param chartInfo 微信信息表
     * @return
     */
	@Override
	@Transactional
	public int deleteByPrimaryKey( ChartInfo chartInfo) {
	    logger.debug("deleteByPrimaryKey chartInfo={}", JsonUtil.toJson(chartInfo));
		//校验参数
		checkExist(chartInfo);
		ChartInfo chartInfoNew = new ChartInfo();
		chartInfoNew .setId(chartInfo.getId());
		//chartInfoNew .setDeleteFlag(BooleanEnum.YES.getValue());
		long millis = DateUtil.getCurrentMillis();
		updateInfo(chartInfoNew,millis);
		int result = chartInfoMapper.updateByPrimaryKeySelective(chartInfoNew);
	    //int result = chartInfoMapper.deleteByPrimaryKey(chartInfo);
	    logger.debug("deleteByPrimaryKey end.deleted {} record(s).", result);
	    return result;
	}
    /**
     * 根据主键批量删除记录(逻辑删除)
	 * @param chartInfoVO 微信信息表
     * @return
     */
	@Override
	@Transactional
	public int deleteToUpdate( ChartInfoVO chartInfoVO) {
	    logger.debug("deleteBatch chartInfo={}", JsonUtil.toJson(chartInfoVO));
		//校验参数 
		 updateParameter(chartInfoVO);
		int result = chartInfoMapper.deleteToUpdate(chartInfoVO); 
	    logger.debug("deleteBatch end.deleteBatch {} record(s).", result);
	    return result;
	}
    /**
     * 根据主键批量更新状态
	 * @param chartInfoVO 微信信息表
     * @return
     */
	@Override
	@Transactional
	public int updateBatchStatus( ChartInfoVO chartInfoVO) {
	    logger.debug("updateBatchStatus chartInfo={}", JsonUtil.toJson(chartInfoVO));
		//校验参数 
		 updateParameter(chartInfoVO);
		int result = chartInfoMapper.updateBatchStatus(chartInfoVO); 
	    logger.debug("updateBatchStatus end.updateBatchStatus {} record(s).", result);
	    return result;
	}
	
    /**
     * 实体参数校验及赋值
     * @param chartInfoVO
     */
    private void updateParameter(ChartInfoVO chartInfoVO) {
        if (CollectionUtils.isEmpty(chartInfoVO.getIdList())) {
            throw new SystemException(ChartInfoErrorCodeEnum.CHARTINFO_DATA_IDLIST_NOT_EMPTY.getValue()
                    , ChartInfoErrorCodeEnum.CHARTINFO_DATA_IDLIST_NOT_EMPTY.getDescriptions());
        }
        long millis = DateUtil.getCurrentMillis();
        chartInfoVO.setUpdateTime(millis);
        //chartInfoVO.setUpdateId(BizUserUtil.getUserID())
        //        .setUpdateName(BizUserUtil.getUserName());
    }
	
	/**
	 * 生成分布式ID并赋值
	 * @param entity 实体对象
	 */
	public void createId(ChartInfo entity,long millis) {
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
	public void updateInfo(ChartInfo entity,long millis) {
		//修改人
		//entity.setUpdateTime(millis);
		//entity.setUpdateId(BizUserUtil.getUserID())
		//		.setUpdateName(BizUserUtil.getUserName());
	}

    /**
     * 插入实体信息，只有当字段有值时，才会构建sql
     * @param chartInfo 微信信息表
     * @return
     */
	@Override
	@Transactional
    public ChartInfo insertSelective(ChartInfo chartInfo) {
    	logger.debug("insertSelective chartInfo={}", JsonUtil.toJson(chartInfo));
		long millis = DateUtil.getCurrentMillis();
		//校验参数
        // TODO 排重校验
        
        
		//生成分布式ID
		createId(chartInfo,millis);
	    int result = chartInfoMapper.insertSelective(chartInfo);
		ChartInfo newChartInfo = null;
        if(result != 0){
            newChartInfo = chartInfoMapper.getByPrimaryKey(chartInfo);
        }
		logger.debug("insertSelective end.inserted success, entity: {}.", JsonUtil.toJson(newChartInfo));
	    return newChartInfo;
    }
	
	/**
	 * 批量插入实体信息(如果失败全部失败)
	 * @param list wms客户地址表List
	 * @return List<Long> 主键list集合
	 */
	@Override
	@Transactional
	public List<Long> insertBatch( List<ChartInfo> list) {
		logger.debug("insertBatch list={}",  JsonUtil.toJson(list));
		long millis = DateUtil.getCurrentMillis();
		List<Long> idList = new ArrayList<Long>();
		if(list == null){
			return idList;
		}
		Map<String, List<ChartInfo>> listMap = BizDataUtil.splitBatches(list, null);
		for (Map.Entry<String, List<ChartInfo>> stringListEntry : listMap.entrySet()) {
			List<ChartInfo> batchList = stringListEntry.getValue();
			logger.debug("批量写入:{}", stringListEntry.getKey());
			for (ChartInfo entity : batchList ) {
				//校验参数
	        	
				//生成分布式ID
				createId(entity,millis);
				//idList.add(entity.getId());
			}
	
			int result = chartInfoMapper.insertBatch(batchList);
			if(result == 0){
				return new ArrayList<Long>();
			}
			logger.debug("insertBatch success, ids: {}.", JsonUtil.toJson(idList));
		}
		return idList;
	}

    /**
     * 根据主键查询实体信息
     * @param chartInfo 微信信息表
     * @return
     */
	@Override
    public ChartInfo getByPrimaryKey(ChartInfo chartInfo) {
        logger.debug("getByPrimaryKey chartInfo={}", JsonUtil.toJson(chartInfo));
		//校验参数
        ChartInfo result = chartInfoMapper.getByPrimaryKey(chartInfo);
        return result;
    }

    /**
     * 根据对象中的主键更新其他信息，只有当字段有值时，才会构建sql
     * @param chartInfo 微信信息表
     * @return
     */
	@Override
	@Transactional
    public ChartInfo updateByPrimaryKeySelective(ChartInfo chartInfo) {
    	logger.debug("updateByPrimaryKeySelective chartInfo={}", JsonUtil.toJson(chartInfo));
		//校验参数
        checkExist(chartInfo);
		long millis = DateUtil.getCurrentMillis();
		updateInfo(chartInfo,millis);  
	    int result = chartInfoMapper.updateByPrimaryKeySelective(chartInfo);
		ChartInfo newChartInfo = null;
        if(result != 0){
            newChartInfo = getByPrimaryKey(chartInfo);
        }
		logger.debug("updateByPrimaryKeySelective end.updated success, entity:{} .",JsonUtil.toJson( newChartInfo));
	    return newChartInfo;
    }
    
    /**
     * 根据查询条件获取列表，不分页，就算查询条件中有分页信息，返回结果也不会分页
     * @param chartInfoVO 微信信息表查询bean
     * @return
     */
	@Override
    public List<ChartInfo> getList(ChartInfoVO chartInfoVO) {
    	logger.debug("getList chartInfoVO={}", JsonUtil.toJson(chartInfoVO));
		//参数校验 
	    List<ChartInfo> resultList = chartInfoMapper.getList(chartInfoVO);
	    return resultList;
    }
    
    /**
     * 根据查询条件获取列表，分页，如果查询条件中没有分页信息，使用默认分页信息
     * 分页信息 @see com.yestae.framework.common.domain.PageVO
     * @param chartInfoVO 微信信息表查询bean
     * @return
     */
	@Override
    public List<ChartInfo> getPage(ChartInfoVO chartInfoVO) {
    	logger.debug("getPage chartInfoVO={}", JsonUtil.toJson(chartInfoVO));
		//参数校验 
	    List<ChartInfo> resultList = chartInfoMapper.getPage(chartInfoVO);
	    return resultList;
    }
    
    /**
     * 根据查询条件获取行数
     * @param chartInfoVO 微信信息表查询bean
     * @return
     */
	@Override
    public int getCount(ChartInfoVO chartInfoVO) {
    	logger.debug("getCount chartInfoVO={}", JsonUtil.toJson(chartInfoVO));
		//参数校验 
	    int count = chartInfoMapper.getCount(chartInfoVO);
	    return count;
    }
	
 
	
    /**
     * 检查数据库中是否存在数据
	 * @param chartInfo 微信信息表
     * @return
     */
    public ChartInfo checkExist(ChartInfo chartInfo){
        ChartInfo oldChartInfo = getByPrimaryKey(chartInfo);
        if(oldChartInfo == null){
          // 异常处理
            throw new SystemException(ChartInfoErrorCodeEnum.CHARTINFO_DATA_UNEXIST.getValue()
                    , ChartInfoErrorCodeEnum.CHARTINFO_DATA_UNEXIST.getDescriptions());
        }
        return oldChartInfo;
    }
}
