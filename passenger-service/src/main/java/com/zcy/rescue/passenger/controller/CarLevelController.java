/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.controller;
 
import java.util.List; 
 
import com.zcy.rescue.passenger.common.domain.DataResult;
import com.zcy.rescue.passenger.common.domain.PageResult;
import com.zcy.rescue.passenger.common.exceptions.SystemException;
import com.zcy.rescue.passenger.common.utils.BeanUtil;
import com.zcy.rescue.passenger.common.utils.JsonUtil;
import com.zcy.rescue.passenger.dto.CarLevelDto;
import com.zcy.rescue.passenger.entity.CarLevel;
import com.zcy.rescue.passenger.enums.CarLevelErrorCodeEnum;
import com.zcy.rescue.passenger.service.CarLevelService;
import com.zcy.rescue.passenger.vo.CarLevelVO;
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
 * 车辆级别列表的Rest实现
 * 
 * @author zcy
 * @date 2024-1-28
 */
 @Api(tags = "车辆级别列表的Rest实现")
@RestController
@RequestMapping("/carLevel")
public class CarLevelController {
	
	private static final Logger logger = LoggerFactory.getLogger(CarLevelController.class);

    /**
     * 注入的车辆级别列表的Service接口实现
     */
	@Autowired
	private CarLevelService carLevelService;
	
    @PostMapping(value="/save", produces="application/json")
	
    public DataResult<CarLevelDto> save(@RequestBody CarLevelVO carLevelVO) {
        logger.debug("车辆级别列表的save carLevel={}", JsonUtil.toJson(carLevelVO));
        CarLevel carLevel = new CarLevel();
		BeanUtil.copyProperties(carLevelVO, carLevel);
        carLevel = carLevelService.insertSelective(carLevel);
        logger.debug("车辆级别列表的save result={}",JsonUtil.toJson(carLevel));
		if(carLevel != null){
			CarLevelDto carLevelDto = new CarLevelDto();
			BeanUtil.copyProperties(carLevel, carLevelDto);
			return DataResult.success(carLevelDto);
		}
		return DataResult.success(null);
		
    }
    
	
    @PostMapping(value="/update", produces="application/json")
    public DataResult<CarLevelDto> update(@RequestBody CarLevelVO carLevelVO) {
        logger.debug("车辆级别列表的update carLevel={}", JsonUtil.toJson(carLevelVO));
        CarLevel carLevel = new CarLevel();
		BeanUtil.copyProperties(carLevelVO, carLevel);
        carLevel = carLevelService.updateByPrimaryKeySelective(carLevel);
        logger.debug("车辆级别列表的update result={}",JsonUtil.toJson(carLevel));
		if(carLevel != null){
			CarLevelDto carLevelDto = new CarLevelDto();
			BeanUtil.copyProperties(carLevel, carLevelDto);
			return DataResult.success(carLevelDto);
		}
		return DataResult.success(null);
    }
	/**
     * 根据主键删除记录(逻辑删除)
	 * @param carLevelVO 车辆级别列表
     * @return
     */
    @ApiOperation("根据主键删除记录(逻辑删除)")
    @PostMapping(value="/delete", produces="application/json")
    public DataResult<CarLevelVO> delete(@RequestBody CarLevelVO carLevelVO) {
        logger.debug("车辆级别列表的delete carLevel={}", JsonUtil.toJson(carLevelVO));
        CarLevel carLevel = new CarLevel();
        BeanUtil.copyProperties(carLevelVO, carLevel);
        int result = carLevelService.deleteByPrimaryKey(carLevel);
        logger.debug("车辆级别列表的delete result={}",result); 
		if (result != 1) {
            throw new SystemException(CarLevelErrorCodeEnum.CARLEVEL_DATA_DELETE_FAIL.getValue()
                    , CarLevelErrorCodeEnum.CARLEVEL_DATA_DELETE_FAIL.getDescriptions());
		}  
		return DataResult.success(carLevelVO);
    }
	/**
     * 根据主键批量删除记录(逻辑删除)
	 * @param carLevelVO 车辆级别列表
     * @return
     */
	@ApiOperation("根据主键批量删除记录(逻辑删除),idList必须传值")
    @PostMapping(value="/deleteBatch", produces="application/json")
    public DataResult<CarLevelVO> deleteBatch(@RequestBody CarLevelVO carLevelVO) {
        logger.debug("车辆级别列表的deleteBatch carLevel={}", JsonUtil.toJson(carLevelVO)); 
        int result = carLevelService.deleteToUpdate(carLevelVO);
        logger.debug("车辆级别列表的deleteBatch result={}",result); 
		if (result < 1) {
            throw new SystemException(CarLevelErrorCodeEnum.CARLEVEL_DATA_DELETE_FAIL.getValue()
                    , CarLevelErrorCodeEnum.CARLEVEL_DATA_DELETE_FAIL.getDescriptions());
		}  
		return DataResult.success(carLevelVO);
    }
    /**
     * 根据主键批量更新状态
	 * @param carLevelVO 车辆级别列表
     * @return
     */
	@ApiOperation("根据主键批量更新状态,idList必须传值")
    @PostMapping(value="/updateBatchStatus", produces="application/json")
    public DataResult<CarLevelVO> updateBatchStatus(@RequestBody CarLevelVO carLevelVO) {
        logger.debug("车辆级别列表的updateBatchStatus carLevel={}", JsonUtil.toJson(carLevelVO)); 
        int result = carLevelService.updateBatchStatus(carLevelVO);
        logger.debug("车辆级别列表的updateBatchStatus result={}",result); 
		if (result < 1) { 
            throw new SystemException(CarLevelErrorCodeEnum.CARLEVEL_DATA_UPDATE_FAIL.getValue()
                    , CarLevelErrorCodeEnum.CARLEVEL_DATA_UPDATE_FAIL.getDescriptions());
		}  
		return DataResult.success(carLevelVO);
    }
    
    /**
     * 批量添加数据
     * @param carLevelList 字典管理
     * @return
     */
    @ApiOperation("批量添加数据")
    @PostMapping(value="/insertBatch", produces="application/json")
    public DataResult<List<Long>> insertBatch(@RequestBody List<CarLevel> carLevelList) {
        logger.debug("车辆级别列表insertBatch carLevel={}", JsonUtil.toJson(carLevelList));
        List<Long> ids = carLevelService.insertBatch(carLevelList);
        logger.debug("车辆级别列表insertBatch result={}",JsonUtil.toJson(ids));
        if (CollectionUtils.isEmpty(ids)) {
            throw new SystemException(CarLevelErrorCodeEnum.CARLEVEL_DATA_INSERT_FAIL.getValue()
                    , CarLevelErrorCodeEnum.CARLEVEL_DATA_INSERT_FAIL.getDescriptions());
        }
        return DataResult.success(ids);
    }
	
    @PostMapping(value="/get", produces="application/json")
    public DataResult<CarLevelDto> get (@RequestBody CarLevelVO carLevelVO) {
		logger.debug("车辆级别列表的get carLevelVO={}",JsonUtil.toJson(carLevelVO));
        CarLevel carLevel = new CarLevel();
        BeanUtil.copyProperties(carLevelVO, carLevel);
		carLevel = carLevelService.getByPrimaryKey(carLevel);
		logger.debug("车辆级别列表的get result={}", carLevel);
		CarLevelDto carLevelDto = new CarLevelDto();
		BeanUtil.copyProperties(carLevel, carLevelDto);
		return DataResult.success(carLevelDto);
    }
    
	
    @PostMapping(value="/getList", produces="application/json")
    public DataResult<List<CarLevelDto>> getList(@RequestBody CarLevelVO carLevelVO) {
        logger.debug("车辆级别列表的getList carLevelVO={}",JsonUtil.toJson( carLevelVO));
        List<CarLevel> carLevelList = carLevelService.getList(carLevelVO);
		List<CarLevelDto> carLevelDtoList = BeanUtil.copyList(carLevelList, CarLevelDto.class); 
		return DataResult.success(carLevelDtoList);
    }
    
    
    @PostMapping(value="/getPage", produces="application/json")
    public PageResult<CarLevelDto> getPage(@RequestBody CarLevelVO  carLevelVO) {
        logger.debug("车辆级别列表的getListWithPage carLevelVO={}", JsonUtil.toJson( carLevelVO));
		
		int count = carLevelService.getCount(carLevelVO);
        if (count < 1) {
            return PageResult.empty();
        }
        List<CarLevel> carLevelList = carLevelService.getPage(carLevelVO); 
		List<CarLevelDto> carLevelDtoList =BeanUtil.copyList(carLevelList, CarLevelDto.class); 
        PageResult<CarLevelDto> carLevelDtoPageResult = PageResult.page(carLevelVO,count,carLevelDtoList);
		return carLevelDtoPageResult;
    }
    
    
	
    @PostMapping(value="/count", produces="application/json")
    public DataResult<Integer> getCount(@RequestBody CarLevelVO carLevelVO) {
        logger.debug("车辆级别列表的getCount carLevelVO={}", JsonUtil.toJson(carLevelVO));
        int count = carLevelService.getCount(carLevelVO);
        logger.debug("车辆级别列表的getCount count={}", count);
        return DataResult.success(count);
    }
    

 
    
}
