/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.controller.managers;
 
import java.util.List; 
 
import com.zcy.rescue.passenger.common.domain.DataResult;
import com.zcy.rescue.passenger.common.domain.PageResult;
import com.zcy.rescue.passenger.common.exceptions.SystemException;
import com.zcy.rescue.passenger.common.utils.BeanUtil;
import com.zcy.rescue.passenger.common.utils.JsonUtil;
import com.zcy.rescue.passenger.dto.ServiceDto;
import com.zcy.rescue.passenger.entity.ServiceProject;
import com.zcy.rescue.passenger.enums.ServiceErrorCodeEnum;
import com.zcy.rescue.passenger.service.ServiceService;
import com.zcy.rescue.passenger.vo.ServiceVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 管理端/服务项目的Rest实现
 *
 * @author zcy
 * @date 2024-1-28
 */
 @Api(tags = "服务项目的Rest实现")
@RestController
@RequestMapping("/service")
public class ServiceController {
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceController.class);

    /**
     * 注入的的Service接口实现
     */
	@Autowired
	private ServiceService serviceService;
	
    @PostMapping(value="/save", produces="application/json")
	
    public DataResult<ServiceDto> save(@RequestBody ServiceVO serviceVO) {
        logger.debug("的save service={}", JsonUtil.toJson(serviceVO));
        ServiceProject service = new ServiceProject();
		BeanUtil.copyProperties(serviceVO, service);
        service = serviceService.insertSelective(service);
        logger.debug("的save result={}",JsonUtil.toJson(service));
		if(service != null){
			ServiceDto serviceDto = new ServiceDto();
			BeanUtil.copyProperties(service, serviceDto);
			return DataResult.success(serviceDto);
		}
		return DataResult.success(null);
		
    }
    
	
    @PostMapping(value="/update", produces="application/json")
    public DataResult<ServiceDto> update(@RequestBody ServiceVO serviceVO) {
        logger.debug("的update service={}", JsonUtil.toJson(serviceVO));
        ServiceProject service = new ServiceProject();
		BeanUtil.copyProperties(serviceVO, service);
        service = serviceService.updateByPrimaryKeySelective(service);
        logger.debug("的update result={}",JsonUtil.toJson(service));
		if(service != null){
			ServiceDto serviceDto = new ServiceDto();
			BeanUtil.copyProperties(service, serviceDto);
			return DataResult.success(serviceDto);
		}
		return DataResult.success(null);
    }
	/**
     * 根据主键删除记录(逻辑删除)
	 * @param serviceVO 
     * @return
     */
    @ApiOperation("根据主键删除记录(逻辑删除)")
    @PostMapping(value="/delete", produces="application/json")
    public DataResult<ServiceVO> delete(@RequestBody ServiceVO serviceVO) {
        logger.debug("的delete service={}", JsonUtil.toJson(serviceVO));
        ServiceProject service = new ServiceProject();
        BeanUtil.copyProperties(serviceVO, service);
        int result = serviceService.deleteByPrimaryKey(service);
        logger.debug("的delete result={}",result); 
		if (result != 1) {
            throw new SystemException(ServiceErrorCodeEnum.SERVICE_DATA_DELETE_FAIL.getValue()
                    , ServiceErrorCodeEnum.SERVICE_DATA_DELETE_FAIL.getDescriptions());
		}  
		return DataResult.success(serviceVO);
    }
	/**
     * 根据主键批量删除记录(逻辑删除)
	 * @param serviceVO 
     * @return
     */
	@ApiOperation("根据主键批量删除记录(逻辑删除),idList必须传值")
    @PostMapping(value="/deleteBatch", produces="application/json")
    public DataResult<ServiceVO> deleteBatch(@RequestBody ServiceVO serviceVO) {
        logger.debug("的deleteBatch service={}", JsonUtil.toJson(serviceVO)); 
        int result = serviceService.deleteToUpdate(serviceVO);
        logger.debug("的deleteBatch result={}",result); 
		if (result < 1) {
            throw new SystemException(ServiceErrorCodeEnum.SERVICE_DATA_DELETE_FAIL.getValue()
                    , ServiceErrorCodeEnum.SERVICE_DATA_DELETE_FAIL.getDescriptions());
		}  
		return DataResult.success(serviceVO);
    }
    /**
     * 根据主键批量更新状态
	 * @param serviceVO 
     * @return
     */
	@ApiOperation("根据主键批量更新状态,idList必须传值")
    @PostMapping(value="/updateBatchStatus", produces="application/json")
    public DataResult<ServiceVO> updateBatchStatus(@RequestBody ServiceVO serviceVO) {
        logger.debug("的updateBatchStatus service={}", JsonUtil.toJson(serviceVO)); 
        int result = serviceService.updateBatchStatus(serviceVO);
        logger.debug("的updateBatchStatus result={}",result); 
		if (result < 1) { 
            throw new SystemException(ServiceErrorCodeEnum.SERVICE_DATA_UPDATE_FAIL.getValue()
                    , ServiceErrorCodeEnum.SERVICE_DATA_UPDATE_FAIL.getDescriptions());
		}  
		return DataResult.success(serviceVO);
    }
    
    /**
     * 批量添加数据
     * @param serviceList 字典管理
     * @return
     */
    @ApiOperation("批量添加数据")
    @PostMapping(value="/insertBatch", produces="application/json")
    public DataResult<List<Long>> insertBatch(@RequestBody List<ServiceProject> serviceList) {
        logger.debug("insertBatch service={}", JsonUtil.toJson(serviceList));
        List<Long> ids = serviceService.insertBatch(serviceList);
        logger.debug("insertBatch result={}",JsonUtil.toJson(ids));
        if (CollectionUtils.isEmpty(ids)) {
            throw new SystemException(ServiceErrorCodeEnum.SERVICE_DATA_INSERT_FAIL.getValue()
                    , ServiceErrorCodeEnum.SERVICE_DATA_INSERT_FAIL.getDescriptions());
        }
        return DataResult.success(ids);
    }
	
    @PostMapping(value="/get", produces="application/json")
    public DataResult<ServiceDto> get (@RequestBody ServiceVO serviceVO) {
		logger.debug("的get serviceVO={}",JsonUtil.toJson(serviceVO));
        ServiceProject service = new ServiceProject();
        BeanUtil.copyProperties(serviceVO, service);
		service = serviceService.getByPrimaryKey(service);
		logger.debug("的get result={}", service);
		ServiceDto serviceDto = new ServiceDto();
		BeanUtil.copyProperties(service, serviceDto);
		return DataResult.success(serviceDto);
    }
    
	
    @PostMapping(value="/getList", produces="application/json")
    public DataResult<List<ServiceDto>> getList(@RequestBody ServiceVO serviceVO) {
        logger.debug("的getList serviceVO={}",JsonUtil.toJson( serviceVO));
        List<ServiceProject> serviceList = serviceService.getList(serviceVO);
		List<ServiceDto> serviceDtoList = BeanUtil.copyList(serviceList, ServiceDto.class); 
		return DataResult.success(serviceDtoList);
    }
    
    
    @PostMapping(value="/getPage", produces="application/json")
    public PageResult<ServiceDto> getPage(@RequestBody ServiceVO  serviceVO) {
        logger.debug("的getListWithPage serviceVO={}", JsonUtil.toJson( serviceVO));
		
		int count = serviceService.getCount(serviceVO);
        if (count < 1) {
            return PageResult.empty();
        }
        List<ServiceProject> serviceList = serviceService.getPage(serviceVO);
		List<ServiceDto> serviceDtoList =BeanUtil.copyList(serviceList, ServiceDto.class); 
        PageResult<ServiceDto> serviceDtoPageResult = PageResult.page(serviceVO,count,serviceDtoList);
		return serviceDtoPageResult;
    }
    
    
	
    @PostMapping(value="/count", produces="application/json")
    public DataResult<Integer> getCount(@RequestBody ServiceVO serviceVO) {
        logger.debug("的getCount serviceVO={}", JsonUtil.toJson(serviceVO));
        int count = serviceService.getCount(serviceVO);
        logger.debug("的getCount count={}", count);
        return DataResult.success(count);
    }
    

 
    
}
