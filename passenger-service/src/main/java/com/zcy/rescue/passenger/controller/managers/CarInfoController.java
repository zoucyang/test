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
import com.zcy.rescue.passenger.dto.CarInfoDto;
import com.zcy.rescue.passenger.entity.CarInfo;
import com.zcy.rescue.passenger.enums.error.CarInfoErrorCodeEnum;
import com.zcy.rescue.passenger.service.CarInfoService;
import com.zcy.rescue.passenger.vo.CarInfoVO; 
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
 * 管理端/车辆信息的的Rest实现
 *
 * @author zcy
 * @date 2024-1-28
 */
 @Api(tags = "车辆信息的Rest实现")
@RestController
@RequestMapping("/carInfo")
public class CarInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(CarInfoController.class);

    /**
     * 注入的的Service接口实现
     */
	@Autowired
	private CarInfoService carInfoService;
	
    @PostMapping(value="/save", produces="application/json")
    public DataResult<CarInfoDto> save(@RequestBody CarInfoVO carInfoVO) {
        logger.debug("的save carInfo={}", JsonUtil.toJson(carInfoVO));
        CarInfo carInfo = new CarInfo();
		BeanUtil.copyProperties(carInfoVO, carInfo);
        carInfo = carInfoService.insertSelective(carInfo);
        logger.debug("的save result={}",JsonUtil.toJson(carInfo));
		if(carInfo != null){
			CarInfoDto carInfoDto = new CarInfoDto();
			BeanUtil.copyProperties(carInfo, carInfoDto);
			return DataResult.success(carInfoDto);
		}
		return DataResult.success(null);
		
    }
    
	
    @PostMapping(value="/update", produces="application/json")
    public DataResult<CarInfoDto> update(@RequestBody CarInfoVO carInfoVO) {
        logger.debug("的update carInfo={}", JsonUtil.toJson(carInfoVO));
        CarInfo carInfo = new CarInfo();
		BeanUtil.copyProperties(carInfoVO, carInfo);
        carInfo = carInfoService.updateByPrimaryKeySelective(carInfo);
        logger.debug("的update result={}",JsonUtil.toJson(carInfo));
		if(carInfo != null){
			CarInfoDto carInfoDto = new CarInfoDto();
			BeanUtil.copyProperties(carInfo, carInfoDto);
			return DataResult.success(carInfoDto);
		}
		return DataResult.success(null);
    }
	/**
     * 根据主键删除记录(逻辑删除)
	 * @param carInfoVO 
     * @return
     */
    @ApiOperation("根据主键删除记录(逻辑删除)")
    @PostMapping(value="/delete", produces="application/json")
    public DataResult<CarInfoVO> delete(@RequestBody CarInfoVO carInfoVO) {
        logger.debug("的delete carInfo={}", JsonUtil.toJson(carInfoVO));
        CarInfo carInfo = new CarInfo();
        BeanUtil.copyProperties(carInfoVO, carInfo);
        int result = carInfoService.deleteByPrimaryKey(carInfo);
        logger.debug("的delete result={}",result); 
		if (result != 1) {
            throw new SystemException(CarInfoErrorCodeEnum.CARINFO_DATA_DELETE_FAIL.getValue()
                    , CarInfoErrorCodeEnum.CARINFO_DATA_DELETE_FAIL.getDescriptions());
		}  
		return DataResult.success(carInfoVO);
    }
	/**
     * 根据主键批量删除记录(逻辑删除)
	 * @param carInfoVO 
     * @return
     */
	@ApiOperation("根据主键批量删除记录(逻辑删除),idList必须传值")
    @PostMapping(value="/deleteBatch", produces="application/json")
    public DataResult<CarInfoVO> deleteBatch(@RequestBody CarInfoVO carInfoVO) {
        logger.debug("的deleteBatch carInfo={}", JsonUtil.toJson(carInfoVO)); 
        int result = carInfoService.deleteToUpdate(carInfoVO);
        logger.debug("的deleteBatch result={}",result); 
		if (result < 1) {
            throw new SystemException(CarInfoErrorCodeEnum.CARINFO_DATA_DELETE_FAIL.getValue()
                    , CarInfoErrorCodeEnum.CARINFO_DATA_DELETE_FAIL.getDescriptions());
		}  
		return DataResult.success(carInfoVO);
    }
    /**
     * 根据主键批量更新状态
	 * @param carInfoVO 
     * @return
     */
	@ApiOperation("根据主键批量更新状态,idList必须传值")
    @PostMapping(value="/updateBatchStatus", produces="application/json")
    public DataResult<CarInfoVO> updateBatchStatus(@RequestBody CarInfoVO carInfoVO) {
        logger.debug("的updateBatchStatus carInfo={}", JsonUtil.toJson(carInfoVO)); 
        int result = carInfoService.updateBatchStatus(carInfoVO);
        logger.debug("的updateBatchStatus result={}",result); 
		if (result < 1) { 
            throw new SystemException(CarInfoErrorCodeEnum.CARINFO_DATA_UPDATE_FAIL.getValue()
                    , CarInfoErrorCodeEnum.CARINFO_DATA_UPDATE_FAIL.getDescriptions());
		}  
		return DataResult.success(carInfoVO);
    }
    
    /**
     * 批量添加数据
     * @param carInfoList 字典管理
     * @return
     */
    @ApiOperation("批量添加数据")
    @PostMapping(value="/insertBatch", produces="application/json")
    public DataResult<List<Long>> insertBatch(@RequestBody List<CarInfo> carInfoList) {
        logger.debug("insertBatch carInfo={}", JsonUtil.toJson(carInfoList));
        List<Long> ids = carInfoService.insertBatch(carInfoList);
        logger.debug("insertBatch result={}",JsonUtil.toJson(ids));
        if (CollectionUtils.isEmpty(ids)) {
            throw new SystemException(CarInfoErrorCodeEnum.CARINFO_DATA_INSERT_FAIL.getValue()
                    , CarInfoErrorCodeEnum.CARINFO_DATA_INSERT_FAIL.getDescriptions());
        }
        return DataResult.success(ids);
    }
	
    @PostMapping(value="/get", produces="application/json")
    public DataResult<CarInfoDto> get (@RequestBody CarInfoVO carInfoVO) {
		logger.debug("的get carInfoVO={}",JsonUtil.toJson(carInfoVO));
        CarInfo carInfo = new CarInfo();
        BeanUtil.copyProperties(carInfoVO, carInfo);
		carInfo = carInfoService.getByPrimaryKey(carInfo);
		logger.debug("的get result={}", carInfo);
		CarInfoDto carInfoDto = new CarInfoDto();
		BeanUtil.copyProperties(carInfo, carInfoDto);
		return DataResult.success(carInfoDto);
    }
    
	
    @PostMapping(value="/getList", produces="application/json")
    public DataResult<List<CarInfoDto>> getList(@RequestBody CarInfoVO carInfoVO) {
        logger.debug("的getList carInfoVO={}",JsonUtil.toJson( carInfoVO));
        List<CarInfo> carInfoList = carInfoService.getList(carInfoVO);
		List<CarInfoDto> carInfoDtoList = BeanUtil.copyList(carInfoList, CarInfoDto.class); 
		return DataResult.success(carInfoDtoList);
    }
    
    
    @PostMapping(value="/getPage", produces="application/json")
    public PageResult<CarInfoDto> getPage(@RequestBody CarInfoVO  carInfoVO) {
        logger.debug("的getListWithPage carInfoVO={}", JsonUtil.toJson( carInfoVO));
		
		int count = carInfoService.getCount(carInfoVO);
        if (count < 1) {
            return PageResult.empty();
        }
        List<CarInfo> carInfoList = carInfoService.getPage(carInfoVO); 
		List<CarInfoDto> carInfoDtoList =BeanUtil.copyList(carInfoList, CarInfoDto.class); 
        PageResult<CarInfoDto> carInfoDtoPageResult = PageResult.page(carInfoVO,count,carInfoDtoList);
		return carInfoDtoPageResult;
    }
    
    
	
    @PostMapping(value="/count", produces="application/json")
    public DataResult<Integer> getCount(@RequestBody CarInfoVO carInfoVO) {
        logger.debug("的getCount carInfoVO={}", JsonUtil.toJson(carInfoVO));
        int count = carInfoService.getCount(carInfoVO);
        logger.debug("的getCount count={}", count);
        return DataResult.success(count);
    }
    

 
    
}
