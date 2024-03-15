/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.controller.managers;

import com.zcy.rescue.passenger.common.domain.DataResult;
import com.zcy.rescue.passenger.common.domain.PageResult;
import com.zcy.rescue.passenger.common.exceptions.SystemException;
import com.zcy.rescue.passenger.common.utils.BeanUtil;
import com.zcy.rescue.passenger.common.utils.JsonUtil;
import com.zcy.rescue.passenger.dto.DriverInfoDto;
import com.zcy.rescue.passenger.entity.DriverInfo;
import com.zcy.rescue.passenger.enums.error.DriverInfoErrorCodeEnum;
import com.zcy.rescue.passenger.service.DriverInfoService;
import com.zcy.rescue.passenger.vo.DriverInfoVO;
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

import java.util.List;


/**
 * 管理端/司机信息表的Rest实现
 *
 * @author zcy
 * @date 2024-1-28
 */
 @Api(tags = "司机信息表的Rest实现")
@RestController
@RequestMapping("/driver")
public class DriverController {
	
	private static final Logger logger = LoggerFactory.getLogger(DriverController.class);

    /**
     * 注入的司机信息表的Service接口实现
     */
	@Autowired
	private DriverInfoService driverInfoService;
	
    @PostMapping(value="/save", produces="application/json")
	
    public DataResult<DriverInfoDto> save(@RequestBody DriverInfoVO driverInfoVO) {
        logger.debug("司机信息表的save driverInfo={}", JsonUtil.toJson(driverInfoVO));
        DriverInfo driverInfo = new DriverInfo();
		BeanUtil.copyProperties(driverInfoVO, driverInfo);
        driverInfo = driverInfoService.insertSelective(driverInfo);
        logger.debug("司机信息表的save result={}",JsonUtil.toJson(driverInfo));
		if(driverInfo != null){
			DriverInfoDto driverInfoDto = new DriverInfoDto();
			BeanUtil.copyProperties(driverInfo, driverInfoDto);
			return DataResult.success(driverInfoDto);
		}
		return DataResult.success(null);
		
    }
    
	
    @PostMapping(value="/update", produces="application/json")
    public DataResult<DriverInfoDto> update(@RequestBody DriverInfoVO driverInfoVO) {
        logger.debug("司机信息表的update driverInfo={}", JsonUtil.toJson(driverInfoVO));
        DriverInfo driverInfo = new DriverInfo();
		BeanUtil.copyProperties(driverInfoVO, driverInfo);
        driverInfo = driverInfoService.updateByPrimaryKeySelective(driverInfo);
        logger.debug("司机信息表的update result={}",JsonUtil.toJson(driverInfo));
		if(driverInfo != null){
			DriverInfoDto driverInfoDto = new DriverInfoDto();
			BeanUtil.copyProperties(driverInfo, driverInfoDto);
			return DataResult.success(driverInfoDto);
		}
		return DataResult.success(null);
    }
	/**
     * 根据主键删除记录(逻辑删除)
	 * @param driverInfoVO 司机信息表
     * @return
     */
    @ApiOperation("根据主键删除记录(逻辑删除)")
    @PostMapping(value="/delete", produces="application/json")
    public DataResult<DriverInfoVO> delete(@RequestBody DriverInfoVO driverInfoVO) {
        logger.debug("司机信息表的delete driverInfo={}", JsonUtil.toJson(driverInfoVO));
        DriverInfo driverInfo = new DriverInfo();
        BeanUtil.copyProperties(driverInfoVO, driverInfo);
        int result = driverInfoService.deleteByPrimaryKey(driverInfo);
        logger.debug("司机信息表的delete result={}",result); 
		if (result != 1) {
            throw new SystemException(DriverInfoErrorCodeEnum.DRIVERINFO_DATA_DELETE_FAIL.getValue()
                    , DriverInfoErrorCodeEnum.DRIVERINFO_DATA_DELETE_FAIL.getDescriptions());
		}  
		return DataResult.success(driverInfoVO);
    }
	/**
     * 根据主键批量删除记录(逻辑删除)
	 * @param driverInfoVO 司机信息表
     * @return
     */
	@ApiOperation("根据主键批量删除记录(逻辑删除),idList必须传值")
    @PostMapping(value="/deleteBatch", produces="application/json")
    public DataResult<DriverInfoVO> deleteBatch(@RequestBody DriverInfoVO driverInfoVO) {
        logger.debug("司机信息表的deleteBatch driverInfo={}", JsonUtil.toJson(driverInfoVO)); 
        int result = driverInfoService.deleteToUpdate(driverInfoVO);
        logger.debug("司机信息表的deleteBatch result={}",result); 
		if (result < 1) {
            throw new SystemException(DriverInfoErrorCodeEnum.DRIVERINFO_DATA_DELETE_FAIL.getValue()
                    , DriverInfoErrorCodeEnum.DRIVERINFO_DATA_DELETE_FAIL.getDescriptions());
		}  
		return DataResult.success(driverInfoVO);
    }
    /**
     * 根据主键批量更新状态
	 * @param driverInfoVO 司机信息表
     * @return
     */
	@ApiOperation("根据主键批量更新状态,idList必须传值")
    @PostMapping(value="/updateBatchStatus", produces="application/json")
    public DataResult<DriverInfoVO> updateBatchStatus(@RequestBody DriverInfoVO driverInfoVO) {
        logger.debug("司机信息表的updateBatchStatus driverInfo={}", JsonUtil.toJson(driverInfoVO)); 
        int result = driverInfoService.updateBatchStatus(driverInfoVO);
        logger.debug("司机信息表的updateBatchStatus result={}",result); 
		if (result < 1) { 
            throw new SystemException(DriverInfoErrorCodeEnum.DRIVERINFO_DATA_UPDATE_FAIL.getValue()
                    , DriverInfoErrorCodeEnum.DRIVERINFO_DATA_UPDATE_FAIL.getDescriptions());
		}  
		return DataResult.success(driverInfoVO);
    }
    
    /**
     * 批量添加数据
     * @param driverInfoList 字典管理
     * @return
     */
    @ApiOperation("批量添加数据")
    @PostMapping(value="/insertBatch", produces="application/json")
    public DataResult<List<Long>> insertBatch(@RequestBody List<DriverInfo> driverInfoList) {
        logger.debug("司机信息表insertBatch driverInfo={}", JsonUtil.toJson(driverInfoList));
        List<Long> ids = driverInfoService.insertBatch(driverInfoList);
        logger.debug("司机信息表insertBatch result={}",JsonUtil.toJson(ids));
        if (CollectionUtils.isEmpty(ids)) {
            throw new SystemException(DriverInfoErrorCodeEnum.DRIVERINFO_DATA_INSERT_FAIL.getValue()
                    , DriverInfoErrorCodeEnum.DRIVERINFO_DATA_INSERT_FAIL.getDescriptions());
        }
        return DataResult.success(ids);
    }
	
    @PostMapping(value="/get", produces="application/json")
    public DataResult<DriverInfoDto> get (@RequestBody DriverInfoVO driverInfoVO) {
		logger.debug("司机信息表的get driverInfoVO={}",JsonUtil.toJson(driverInfoVO));
        DriverInfo driverInfo = new DriverInfo();
        BeanUtil.copyProperties(driverInfoVO, driverInfo);
		driverInfo = driverInfoService.getByPrimaryKey(driverInfo);
		logger.debug("司机信息表的get result={}", driverInfo);
		DriverInfoDto driverInfoDto = new DriverInfoDto();
		BeanUtil.copyProperties(driverInfo, driverInfoDto);
		return DataResult.success(driverInfoDto);
    }
    
	
    @PostMapping(value="/getList", produces="application/json")
    public DataResult<List<DriverInfoDto>> getList(@RequestBody DriverInfoVO driverInfoVO) {
        logger.debug("司机信息表的getList driverInfoVO={}",JsonUtil.toJson( driverInfoVO));
        List<DriverInfo> driverInfoList = driverInfoService.getList(driverInfoVO);
		List<DriverInfoDto> driverInfoDtoList = BeanUtil.copyList(driverInfoList, DriverInfoDto.class); 
		return DataResult.success(driverInfoDtoList);
    }
    
    
    @PostMapping(value="/getPage", produces="application/json")
    public PageResult<DriverInfoDto> getPage(@RequestBody DriverInfoVO  driverInfoVO) {
        logger.debug("司机信息表的getListWithPage driverInfoVO={}", JsonUtil.toJson( driverInfoVO));
		
		int count = driverInfoService.getCount(driverInfoVO);
        if (count < 1) {
            return PageResult.empty();
        }
        List<DriverInfo> driverInfoList = driverInfoService.getPage(driverInfoVO); 
		List<DriverInfoDto> driverInfoDtoList =BeanUtil.copyList(driverInfoList, DriverInfoDto.class); 
        PageResult<DriverInfoDto> driverInfoDtoPageResult = PageResult.page(driverInfoVO,count,driverInfoDtoList);
		return driverInfoDtoPageResult;
    }
    
    
	
    @PostMapping(value="/count", produces="application/json")
    public DataResult<Integer> getCount(@RequestBody DriverInfoVO driverInfoVO) {
        logger.debug("司机信息表的getCount driverInfoVO={}", JsonUtil.toJson(driverInfoVO));
        int count = driverInfoService.getCount(driverInfoVO);
        logger.debug("司机信息表的getCount count={}", count);
        return DataResult.success(count);
    }
    

 
    
}
