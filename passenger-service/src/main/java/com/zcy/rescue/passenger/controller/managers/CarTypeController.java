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
import com.zcy.rescue.passenger.dto.CarTypeDto;
import com.zcy.rescue.passenger.entity.CarType;
import com.zcy.rescue.passenger.enums.error.CarTypeErrorCodeEnum;
import com.zcy.rescue.passenger.service.CarTypeService;
import com.zcy.rescue.passenger.vo.CarTypeVO;
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
 * 管理端/车辆类型的Rest实现
 *
 * @author zcy
 * @date 2024-1-28
 */
 @Api(tags = "车辆类型的Rest实现")
@RestController
@RequestMapping("/carType")
public class CarTypeController {
	
	private static final Logger logger = LoggerFactory.getLogger(CarTypeController.class);

    /**
     * 注入的的Service接口实现
     */
	@Autowired
	private CarTypeService carTypeService;
	
    @PostMapping(value="/save", produces="application/json")
	
    public DataResult<CarTypeDto> save(@RequestBody CarTypeVO carTypeVO) {
        logger.debug("的save carType={}", JsonUtil.toJson(carTypeVO));
        CarType carType = new CarType();
		BeanUtil.copyProperties(carTypeVO, carType);
        carType = carTypeService.insertSelective(carType);
        logger.debug("的save result={}",JsonUtil.toJson(carType));
		if(carType != null){
			CarTypeDto carTypeDto = new CarTypeDto();
			BeanUtil.copyProperties(carType, carTypeDto);
			return DataResult.success(carTypeDto);
		}
		return DataResult.success(null);
		
    }
    
	
    @PostMapping(value="/update", produces="application/json")
    public DataResult<CarTypeDto> update(@RequestBody CarTypeVO carTypeVO) {
        logger.debug("的update carType={}", JsonUtil.toJson(carTypeVO));
        CarType carType = new CarType();
		BeanUtil.copyProperties(carTypeVO, carType);
        carType = carTypeService.updateByPrimaryKeySelective(carType);
        logger.debug("的update result={}",JsonUtil.toJson(carType));
		if(carType != null){
			CarTypeDto carTypeDto = new CarTypeDto();
			BeanUtil.copyProperties(carType, carTypeDto);
			return DataResult.success(carTypeDto);
		}
		return DataResult.success(null);
    }
	/**
     * 根据主键删除记录(逻辑删除)
	 * @param carTypeVO 
     * @return
     */
    @ApiOperation("根据主键删除记录(逻辑删除)")
    @PostMapping(value="/delete", produces="application/json")
    public DataResult<CarTypeVO> delete(@RequestBody CarTypeVO carTypeVO) {
        logger.debug("的delete carType={}", JsonUtil.toJson(carTypeVO));
        CarType carType = new CarType();
        BeanUtil.copyProperties(carTypeVO, carType);
        int result = carTypeService.deleteByPrimaryKey(carType);
        logger.debug("的delete result={}",result); 
		if (result != 1) {
            throw new SystemException(CarTypeErrorCodeEnum.CARTYPE_DATA_DELETE_FAIL.getValue()
                    , CarTypeErrorCodeEnum.CARTYPE_DATA_DELETE_FAIL.getDescriptions());
		}  
		return DataResult.success(carTypeVO);
    }
	/**
     * 根据主键批量删除记录(逻辑删除)
	 * @param carTypeVO 
     * @return
     */
	@ApiOperation("根据主键批量删除记录(逻辑删除),idList必须传值")
    @PostMapping(value="/deleteBatch", produces="application/json")
    public DataResult<CarTypeVO> deleteBatch(@RequestBody CarTypeVO carTypeVO) {
        logger.debug("的deleteBatch carType={}", JsonUtil.toJson(carTypeVO)); 
        int result = carTypeService.deleteToUpdate(carTypeVO);
        logger.debug("的deleteBatch result={}",result); 
		if (result < 1) {
            throw new SystemException(CarTypeErrorCodeEnum.CARTYPE_DATA_DELETE_FAIL.getValue()
                    , CarTypeErrorCodeEnum.CARTYPE_DATA_DELETE_FAIL.getDescriptions());
		}  
		return DataResult.success(carTypeVO);
    }
    /**
     * 根据主键批量更新状态
	 * @param carTypeVO 
     * @return
     */
	@ApiOperation("根据主键批量更新状态,idList必须传值")
    @PostMapping(value="/updateBatchStatus", produces="application/json")
    public DataResult<CarTypeVO> updateBatchStatus(@RequestBody CarTypeVO carTypeVO) {
        logger.debug("的updateBatchStatus carType={}", JsonUtil.toJson(carTypeVO)); 
        int result = carTypeService.updateBatchStatus(carTypeVO);
        logger.debug("的updateBatchStatus result={}",result); 
		if (result < 1) { 
            throw new SystemException(CarTypeErrorCodeEnum.CARTYPE_DATA_UPDATE_FAIL.getValue()
                    , CarTypeErrorCodeEnum.CARTYPE_DATA_UPDATE_FAIL.getDescriptions());
		}  
		return DataResult.success(carTypeVO);
    }
    
    /**
     * 批量添加数据
     * @param carTypeList 字典管理
     * @return
     */
    @ApiOperation("批量添加数据")
    @PostMapping(value="/insertBatch", produces="application/json")
    public DataResult<List<Long>> insertBatch(@RequestBody List<CarType> carTypeList) {
        logger.debug("insertBatch carType={}", JsonUtil.toJson(carTypeList));
        List<Long> ids = carTypeService.insertBatch(carTypeList);
        logger.debug("insertBatch result={}",JsonUtil.toJson(ids));
        if (CollectionUtils.isEmpty(ids)) {
            throw new SystemException(CarTypeErrorCodeEnum.CARTYPE_DATA_INSERT_FAIL.getValue()
                    , CarTypeErrorCodeEnum.CARTYPE_DATA_INSERT_FAIL.getDescriptions());
        }
        return DataResult.success(ids);
    }
	
    @PostMapping(value="/get", produces="application/json")
    public DataResult<CarTypeDto> get (@RequestBody CarTypeVO carTypeVO) {
		logger.debug("的get carTypeVO={}",JsonUtil.toJson(carTypeVO));
        CarType carType = new CarType();
        BeanUtil.copyProperties(carTypeVO, carType);
		carType = carTypeService.getByPrimaryKey(carType);
		logger.debug("的get result={}", carType);
		CarTypeDto carTypeDto = new CarTypeDto();
		BeanUtil.copyProperties(carType, carTypeDto);
		return DataResult.success(carTypeDto);
    }
    
	
    @PostMapping(value="/getList", produces="application/json")
    public DataResult<List<CarTypeDto>> getList(@RequestBody CarTypeVO carTypeVO) {
        logger.debug("的getList carTypeVO={}",JsonUtil.toJson( carTypeVO));
        List<CarType> carTypeList = carTypeService.getList(carTypeVO);
		List<CarTypeDto> carTypeDtoList = BeanUtil.copyList(carTypeList, CarTypeDto.class); 
		return DataResult.success(carTypeDtoList);
    }
    
    
    @PostMapping(value="/getPage", produces="application/json")
    public PageResult<CarTypeDto> getPage(@RequestBody CarTypeVO  carTypeVO) {
        logger.debug("的getListWithPage carTypeVO={}", JsonUtil.toJson( carTypeVO));
		
		int count = carTypeService.getCount(carTypeVO);
        if (count < 1) {
            return PageResult.empty();
        }
        List<CarType> carTypeList = carTypeService.getPage(carTypeVO); 
		List<CarTypeDto> carTypeDtoList =BeanUtil.copyList(carTypeList, CarTypeDto.class); 
        PageResult<CarTypeDto> carTypeDtoPageResult = PageResult.page(carTypeVO,count,carTypeDtoList);
		return carTypeDtoPageResult;
    }
    
    
	
    @PostMapping(value="/count", produces="application/json")
    public DataResult<Integer> getCount(@RequestBody CarTypeVO carTypeVO) {
        logger.debug("的getCount carTypeVO={}", JsonUtil.toJson(carTypeVO));
        int count = carTypeService.getCount(carTypeVO);
        logger.debug("的getCount count={}", count);
        return DataResult.success(count);
    }
    

 
    
}
