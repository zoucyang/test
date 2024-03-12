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
import com.zcy.rescue.passenger.dto.CarBrandDto;
import com.zcy.rescue.passenger.entity.CarBrand;
import com.zcy.rescue.passenger.enums.CarBrandErrorCodeEnum;
import com.zcy.rescue.passenger.service.CarBrandService;
import com.zcy.rescue.passenger.vo.CarBrandVO;
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
 * 管理端/车辆品牌的Rest实现
 *
 * @author zcy
 * @date 2024-1-28
 */
 @Api(tags = "车辆品牌的Rest实现")
@RestController
@RequestMapping("/carBrand")
public class CarBrandController {
	
	private static final Logger logger = LoggerFactory.getLogger(CarBrandController.class);

    /**
     * 注入的的Service接口实现
     */
	@Autowired
	private CarBrandService carBrandService;
	
    @PostMapping(value="/save", produces="application/json") 
    public DataResult<CarBrandDto> save(@RequestBody CarBrandVO carBrandVO) {
        logger.debug("的save carBrand={}", JsonUtil.toJson(carBrandVO));
        CarBrand carBrand = new CarBrand();
		BeanUtil.copyProperties(carBrandVO, carBrand);
        carBrand = carBrandService.insertSelective(carBrand);
        logger.debug("的save result={}",JsonUtil.toJson(carBrand));
		if(carBrand != null){
			CarBrandDto carBrandDto = new CarBrandDto();
			BeanUtil.copyProperties(carBrand, carBrandDto);
			return DataResult.success(carBrandDto);
		}
		return DataResult.success(null);
		
    }
    
	
    @PostMapping(value="/update", produces="application/json")
    public DataResult<CarBrandDto> update(@RequestBody CarBrandVO carBrandVO) {
        logger.debug("的update carBrand={}", JsonUtil.toJson(carBrandVO));
        CarBrand carBrand = new CarBrand();
		BeanUtil.copyProperties(carBrandVO, carBrand);
        carBrand = carBrandService.updateByPrimaryKeySelective(carBrand);
        logger.debug("的update result={}",JsonUtil.toJson(carBrand));
		if(carBrand != null){
			CarBrandDto carBrandDto = new CarBrandDto();
			BeanUtil.copyProperties(carBrand, carBrandDto);
			return DataResult.success(carBrandDto);
		}
		return DataResult.success(null);
    }
	/**
     * 根据主键删除记录(逻辑删除)
	 * @param carBrandVO 
     * @return
     */
    @ApiOperation("根据主键删除记录(逻辑删除)")
    @PostMapping(value="/delete", produces="application/json")
    public DataResult<CarBrandVO> delete(@RequestBody CarBrandVO carBrandVO) {
        logger.debug("的delete carBrand={}", JsonUtil.toJson(carBrandVO));
        CarBrand carBrand = new CarBrand();
        BeanUtil.copyProperties(carBrandVO, carBrand);
        int result = carBrandService.deleteByPrimaryKey(carBrand);
        logger.debug("的delete result={}",result); 
		if (result != 1) {
            throw new SystemException(CarBrandErrorCodeEnum.CARBRAND_DATA_DELETE_FAIL.getValue()
                    , CarBrandErrorCodeEnum.CARBRAND_DATA_DELETE_FAIL.getDescriptions());
		}  
		return DataResult.success(carBrandVO);
    }
	/**
     * 根据主键批量删除记录(逻辑删除)
	 * @param carBrandVO 
     * @return
     */
	@ApiOperation("根据主键批量删除记录(逻辑删除),idList必须传值")
    @PostMapping(value="/deleteBatch", produces="application/json")
    public DataResult<CarBrandVO> deleteBatch(@RequestBody CarBrandVO carBrandVO) {
        logger.debug("的deleteBatch carBrand={}", JsonUtil.toJson(carBrandVO)); 
        int result = carBrandService.deleteToUpdate(carBrandVO);
        logger.debug("的deleteBatch result={}",result); 
		if (result < 1) {
            throw new SystemException(CarBrandErrorCodeEnum.CARBRAND_DATA_DELETE_FAIL.getValue()
                    , CarBrandErrorCodeEnum.CARBRAND_DATA_DELETE_FAIL.getDescriptions());
		}  
		return DataResult.success(carBrandVO);
    }
    /**
     * 根据主键批量更新状态
	 * @param carBrandVO 
     * @return
     */
	@ApiOperation("根据主键批量更新状态,idList必须传值")
    @PostMapping(value="/updateBatchStatus", produces="application/json")
    public DataResult<CarBrandVO> updateBatchStatus(@RequestBody CarBrandVO carBrandVO) {
        logger.debug("的updateBatchStatus carBrand={}", JsonUtil.toJson(carBrandVO)); 
        int result = carBrandService.updateBatchStatus(carBrandVO);
        logger.debug("的updateBatchStatus result={}",result); 
		if (result < 1) { 
            throw new SystemException(CarBrandErrorCodeEnum.CARBRAND_DATA_UPDATE_FAIL.getValue()
                    , CarBrandErrorCodeEnum.CARBRAND_DATA_UPDATE_FAIL.getDescriptions());
		}  
		return DataResult.success(carBrandVO);
    }
    
    /**
     * 批量添加数据
     * @param carBrandList 字典管理
     * @return
     */
    @ApiOperation("批量添加数据")
    @PostMapping(value="/insertBatch", produces="application/json")
    public DataResult<List<Long>> insertBatch(@RequestBody List<CarBrand> carBrandList) {
        logger.debug("insertBatch carBrand={}", JsonUtil.toJson(carBrandList));
        List<Long> ids = carBrandService.insertBatch(carBrandList);
        logger.debug("insertBatch result={}",JsonUtil.toJson(ids));
        if (CollectionUtils.isEmpty(ids)) {
            throw new SystemException(CarBrandErrorCodeEnum.CARBRAND_DATA_INSERT_FAIL.getValue()
                    , CarBrandErrorCodeEnum.CARBRAND_DATA_INSERT_FAIL.getDescriptions());
        }
        return DataResult.success(ids);
    }
	
    @PostMapping(value="/get", produces="application/json")
    public DataResult<CarBrandDto> get (@RequestBody CarBrandVO carBrandVO) {
		logger.debug("的get carBrandVO={}",JsonUtil.toJson(carBrandVO));
        CarBrand carBrand = new CarBrand();
        BeanUtil.copyProperties(carBrandVO, carBrand);
		carBrand = carBrandService.getByPrimaryKey(carBrand);
		logger.debug("的get result={}", carBrand);
		CarBrandDto carBrandDto = new CarBrandDto();
		BeanUtil.copyProperties(carBrand, carBrandDto);
		return DataResult.success(carBrandDto);
    }
    
	
    @PostMapping(value="/getList", produces="application/json")
    public DataResult<List<CarBrandDto>> getList(@RequestBody CarBrandVO carBrandVO) {
        logger.debug("的getList carBrandVO={}",JsonUtil.toJson( carBrandVO));
        List<CarBrand> carBrandList = carBrandService.getList(carBrandVO);
		List<CarBrandDto> carBrandDtoList = BeanUtil.copyList(carBrandList, CarBrandDto.class); 
		return DataResult.success(carBrandDtoList);
    }
    
    
    @PostMapping(value="/getPage", produces="application/json")
    public PageResult<CarBrandDto> getPage(@RequestBody CarBrandVO  carBrandVO) {
        logger.debug("的getListWithPage carBrandVO={}", JsonUtil.toJson( carBrandVO));
		
		int count = carBrandService.getCount(carBrandVO);
        if (count < 1) {
            return PageResult.empty();
        }
        List<CarBrand> carBrandList = carBrandService.getPage(carBrandVO); 
		List<CarBrandDto> carBrandDtoList =BeanUtil.copyList(carBrandList, CarBrandDto.class); 
        PageResult<CarBrandDto> carBrandDtoPageResult = PageResult.page(carBrandVO,count,carBrandDtoList);
		return carBrandDtoPageResult;
    }
    
    
	
    @PostMapping(value="/count", produces="application/json")
    public DataResult<Integer> getCount(@RequestBody CarBrandVO carBrandVO) {
        logger.debug("的getCount carBrandVO={}", JsonUtil.toJson(carBrandVO));
        int count = carBrandService.getCount(carBrandVO);
        logger.debug("的getCount count={}", count);
        return DataResult.success(count);
    }
    

 
    
}
