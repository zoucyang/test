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
import com.zcy.rescue.passenger.dao.OrderMapper;
import com.zcy.rescue.passenger.entity.Order;
import com.zcy.rescue.passenger.enums.OrderErrorCodeEnum;
import com.zcy.rescue.passenger.service.OrderService;
import com.zcy.rescue.passenger.vo.OrderVO;
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
 * 订单表的Service实现
 * 
 * @author zcy
 * @date 2024-1-28
 */
@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    /**
     * 注入的订单表的Dao接口实现
     */
	@Autowired
	private OrderMapper orderMapper;

    /**
     * 根据主键删除记录(逻辑删除)
	 * @param order 订单表
     * @return
     */
	@Override
	@Transactional
	public int deleteByPrimaryKey( Order order) {
	    logger.debug("deleteByPrimaryKey order={}", JsonUtil.toJson(order));
		//校验参数
		checkExist(order);
		Order orderNew = new Order();
		orderNew .setId(order.getId());
		//orderNew .setDeleteFlag(BooleanEnum.YES.getValue());
		long millis = DateUtil.getCurrentMillis();
		updateInfo(orderNew,millis);
		int result = orderMapper.updateByPrimaryKeySelective(orderNew);
	    //int result = orderMapper.deleteByPrimaryKey(order);
	    logger.debug("deleteByPrimaryKey end.deleted {} record(s).", result);
	    return result;
	}
    /**
     * 根据主键批量删除记录(逻辑删除)
	 * @param orderVO 订单表
     * @return
     */
	@Override
	@Transactional
	public int deleteToUpdate( OrderVO orderVO) {
	    logger.debug("deleteBatch order={}", JsonUtil.toJson(orderVO));
		//校验参数 
		 updateParameter(orderVO);
		int result = orderMapper.deleteToUpdate(orderVO); 
	    logger.debug("deleteBatch end.deleteBatch {} record(s).", result);
	    return result;
	}
    /**
     * 根据主键批量更新状态
	 * @param orderVO 订单表
     * @return
     */
	@Override
	@Transactional
	public int updateBatchStatus( OrderVO orderVO) {
	    logger.debug("updateBatchStatus order={}", JsonUtil.toJson(orderVO));
		//校验参数 
		 updateParameter(orderVO);
		int result = orderMapper.updateBatchStatus(orderVO); 
	    logger.debug("updateBatchStatus end.updateBatchStatus {} record(s).", result);
	    return result;
	}
	
    /**
     * 实体参数校验及赋值
     * @param orderVO
     */
    private void updateParameter(OrderVO orderVO) {
        if (CollectionUtils.isEmpty(orderVO.getIdList())) {
            throw new SystemException(OrderErrorCodeEnum.ORDER_DATA_IDLIST_NOT_EMPTY.getValue()
                    , OrderErrorCodeEnum.ORDER_DATA_IDLIST_NOT_EMPTY.getDescriptions());
        }
        long millis = DateUtil.getCurrentMillis();
        //orderVO.setUpdateTime(millis);
        //orderVO.setUpdateId(BizUserUtil.getUserID())
        //        .setUpdateName(BizUserUtil.getUserName());
    }
	
	/**
	 * 生成分布式ID并赋值
	 * @param entity 实体对象
	 */
	public void createId(Order entity,long millis) {
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
	public void updateInfo(Order entity,long millis) {
		//修改人
		//entity.setUpdateTime(millis);
		//entity.setUpdateId(BizUserUtil.getUserID())
		//		.setUpdateName(BizUserUtil.getUserName());
	}

    /**
     * 插入实体信息，只有当字段有值时，才会构建sql
     * @param order 订单表
     * @return
     */
	@Override
	@Transactional
    public Order insertSelective(Order order) {
    	logger.debug("insertSelective order={}", JsonUtil.toJson(order));
		long millis = DateUtil.getCurrentMillis();
		//校验参数
        // TODO 排重校验
        
        
		//生成分布式ID
		createId(order,millis);
	    int result = orderMapper.insertSelective(order);
		Order newOrder = null;
        if(result != 0){
            newOrder = orderMapper.getByPrimaryKey(order);
        }
		logger.debug("insertSelective end.inserted success, entity: {}.", JsonUtil.toJson(newOrder));
	    return newOrder;
    }
	
	/**
	 * 批量插入实体信息(如果失败全部失败)
	 * @param list wms客户地址表List
	 * @return List<Long> 主键list集合
	 */
	@Override
	@Transactional
	public List<Long> insertBatch( List<Order> list) {
		logger.debug("insertBatch list={}",  JsonUtil.toJson(list));
		long millis = DateUtil.getCurrentMillis();
		List<Long> idList = new ArrayList<Long>();
		if(list == null){
			return idList;
		}
		Map<String, List<Order>> listMap = BizDataUtil.splitBatches(list, null);
		for (Map.Entry<String, List<Order>> stringListEntry : listMap.entrySet()) {
			List<Order> batchList = stringListEntry.getValue();
			logger.debug("批量写入:{}", stringListEntry.getKey());
			for (Order entity : batchList ) {
				//校验参数
	        	
				//生成分布式ID
				createId(entity,millis);
				idList.add(entity.getId());
			}
	
			int result = orderMapper.insertBatch(batchList);
			if(result == 0){
				return new ArrayList<Long>();
			}
			logger.debug("insertBatch success, ids: {}.", JsonUtil.toJson(idList));
		}
		return idList;
	}

    /**
     * 根据主键查询实体信息
     * @param order 订单表
     * @return
     */
	@Override
    public Order getByPrimaryKey(Order order) {
        logger.debug("getByPrimaryKey order={}", JsonUtil.toJson(order));
		//校验参数
        Order result = orderMapper.getByPrimaryKey(order);
        return result;
    }

    /**
     * 根据对象中的主键更新其他信息，只有当字段有值时，才会构建sql
     * @param order 订单表
     * @return
     */
	@Override
	@Transactional
    public Order updateByPrimaryKeySelective(Order order) {
    	logger.debug("updateByPrimaryKeySelective order={}", JsonUtil.toJson(order));
		//校验参数
        checkExist(order);
		long millis = DateUtil.getCurrentMillis();
		updateInfo(order,millis);  
	    int result = orderMapper.updateByPrimaryKeySelective(order);
		Order newOrder = null;
        if(result != 0){
            newOrder = getByPrimaryKey(order);
        }
		logger.debug("updateByPrimaryKeySelective end.updated success, entity:{} .",JsonUtil.toJson( newOrder));
	    return newOrder;
    }
    
    /**
     * 根据查询条件获取列表，不分页，就算查询条件中有分页信息，返回结果也不会分页
     * @param orderVO 订单表查询bean
     * @return
     */
	@Override
    public List<Order> getList(OrderVO orderVO) {
    	logger.debug("getList orderVO={}", JsonUtil.toJson(orderVO));
		//参数校验 
	    List<Order> resultList = orderMapper.getList(orderVO);
	    return resultList;
    }
    
    /**
     * 根据查询条件获取列表，分页，如果查询条件中没有分页信息，使用默认分页信息
     * 分页信息 @see com.yestae.framework.common.domain.PageVO
     * @param orderVO 订单表查询bean
     * @return
     */
	@Override
    public List<Order> getPage(OrderVO orderVO) {
    	logger.debug("getPage orderVO={}", JsonUtil.toJson(orderVO));
		//参数校验 
	    List<Order> resultList = orderMapper.getPage(orderVO);
	    return resultList;
    }
    
    /**
     * 根据查询条件获取行数
     * @param orderVO 订单表查询bean
     * @return
     */
	@Override
    public int getCount(OrderVO orderVO) {
    	logger.debug("getCount orderVO={}", JsonUtil.toJson(orderVO));
		//参数校验 
	    int count = orderMapper.getCount(orderVO);
	    return count;
    }
	
 
	
    /**
     * 检查数据库中是否存在数据
	 * @param order 订单表
     * @return
     */
    public Order checkExist(Order order){
        Order oldOrder = getByPrimaryKey(order);
        if(oldOrder == null){
          // 异常处理
            throw new SystemException(OrderErrorCodeEnum.ORDER_DATA_UNEXIST.getValue()
                    , OrderErrorCodeEnum.ORDER_DATA_UNEXIST.getDescriptions());
        }
        return oldOrder;
    }
}
