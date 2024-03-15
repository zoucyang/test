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
import com.zcy.rescue.passenger.dto.PassengerInfoDto;
import com.zcy.rescue.passenger.entity.PassengerInfo;
import com.zcy.rescue.passenger.enums.error.PassengerInfoErrorCodeEnum;
import com.zcy.rescue.passenger.service.PassengerInfoService;
import com.zcy.rescue.passenger.vo.PassengerInfoVO;
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
 * 管理端/乘客信息的Rest实现
 *
 * @author zcy
 * @date 2024-1-28
 */
 @Api(tags = "乘客信息的Rest实现")
@RestController
@RequestMapping("/passengerInfo")
public class PassengerInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(PassengerInfoController.class);

    /**
     * 注入的的Service接口实现
     */
	@Autowired
	private PassengerInfoService passengerInfoService;
	
    @PostMapping(value="/save", produces="application/json")
	
    public DataResult<PassengerInfoDto> save(@RequestBody PassengerInfoVO passengerInfoVO) {
        logger.debug("的save passengerInfo={}", JsonUtil.toJson(passengerInfoVO));
        PassengerInfo passengerInfo = new PassengerInfo();
		BeanUtil.copyProperties(passengerInfoVO, passengerInfo);
        passengerInfo = passengerInfoService.insertSelective(passengerInfo);
        logger.debug("的save result={}",JsonUtil.toJson(passengerInfo));
		if(passengerInfo != null){
			PassengerInfoDto passengerInfoDto = new PassengerInfoDto();
			BeanUtil.copyProperties(passengerInfo, passengerInfoDto);
			return DataResult.success(passengerInfoDto);
		}
		return DataResult.success(null);
		
    }
    
	
    @PostMapping(value="/update", produces="application/json")
    public DataResult<PassengerInfoDto> update(@RequestBody PassengerInfoVO passengerInfoVO) {
        logger.debug("的update passengerInfo={}", JsonUtil.toJson(passengerInfoVO));
        PassengerInfo passengerInfo = new PassengerInfo();
		BeanUtil.copyProperties(passengerInfoVO, passengerInfo);
        passengerInfo = passengerInfoService.updateByPrimaryKeySelective(passengerInfo);
        logger.debug("的update result={}",JsonUtil.toJson(passengerInfo));
		if(passengerInfo != null){
			PassengerInfoDto passengerInfoDto = new PassengerInfoDto();
			BeanUtil.copyProperties(passengerInfo, passengerInfoDto);
			return DataResult.success(passengerInfoDto);
		}
		return DataResult.success(null);
    }
	/**
     * 根据主键删除记录(逻辑删除)
	 * @param passengerInfoVO 
     * @return
     */
    @ApiOperation("根据主键删除记录(逻辑删除)")
    @PostMapping(value="/delete", produces="application/json")
    public DataResult<PassengerInfoVO> delete(@RequestBody PassengerInfoVO passengerInfoVO) {
        logger.debug("的delete passengerInfo={}", JsonUtil.toJson(passengerInfoVO));
        PassengerInfo passengerInfo = new PassengerInfo();
        BeanUtil.copyProperties(passengerInfoVO, passengerInfo);
        int result = passengerInfoService.deleteByPrimaryKey(passengerInfo);
        logger.debug("的delete result={}",result); 
		if (result != 1) {
            throw new SystemException(PassengerInfoErrorCodeEnum.PASSENGERINFO_DATA_DELETE_FAIL.getValue()
                    , PassengerInfoErrorCodeEnum.PASSENGERINFO_DATA_DELETE_FAIL.getDescriptions());
		}  
		return DataResult.success(passengerInfoVO);
    }
	/**
     * 根据主键批量删除记录(逻辑删除)
	 * @param passengerInfoVO 
     * @return
     */
	@ApiOperation("根据主键批量删除记录(逻辑删除),idList必须传值")
    @PostMapping(value="/deleteBatch", produces="application/json")
    public DataResult<PassengerInfoVO> deleteBatch(@RequestBody PassengerInfoVO passengerInfoVO) {
        logger.debug("的deleteBatch passengerInfo={}", JsonUtil.toJson(passengerInfoVO)); 
        int result = passengerInfoService.deleteToUpdate(passengerInfoVO);
        logger.debug("的deleteBatch result={}",result); 
		if (result < 1) {
            throw new SystemException(PassengerInfoErrorCodeEnum.PASSENGERINFO_DATA_DELETE_FAIL.getValue()
                    , PassengerInfoErrorCodeEnum.PASSENGERINFO_DATA_DELETE_FAIL.getDescriptions());
		}  
		return DataResult.success(passengerInfoVO);
    }
    /**
     * 根据主键批量更新状态
	 * @param passengerInfoVO 
     * @return
     */
	@ApiOperation("根据主键批量更新状态,idList必须传值")
    @PostMapping(value="/updateBatchStatus", produces="application/json")
    public DataResult<PassengerInfoVO> updateBatchStatus(@RequestBody PassengerInfoVO passengerInfoVO) {
        logger.debug("的updateBatchStatus passengerInfo={}", JsonUtil.toJson(passengerInfoVO)); 
        int result = passengerInfoService.updateBatchStatus(passengerInfoVO);
        logger.debug("的updateBatchStatus result={}",result); 
		if (result < 1) { 
            throw new SystemException(PassengerInfoErrorCodeEnum.PASSENGERINFO_DATA_UPDATE_FAIL.getValue()
                    , PassengerInfoErrorCodeEnum.PASSENGERINFO_DATA_UPDATE_FAIL.getDescriptions());
		}  
		return DataResult.success(passengerInfoVO);
    }
    
    /**
     * 批量添加数据
     * @param passengerInfoList 字典管理
     * @return
     */
    @ApiOperation("批量添加数据")
    @PostMapping(value="/insertBatch", produces="application/json")
    public DataResult<List<Long>> insertBatch(@RequestBody List<PassengerInfo> passengerInfoList) {
        logger.debug("insertBatch passengerInfo={}", JsonUtil.toJson(passengerInfoList));
        List<Long> ids = passengerInfoService.insertBatch(passengerInfoList);
        logger.debug("insertBatch result={}",JsonUtil.toJson(ids));
        if (CollectionUtils.isEmpty(ids)) {
            throw new SystemException(PassengerInfoErrorCodeEnum.PASSENGERINFO_DATA_INSERT_FAIL.getValue()
                    , PassengerInfoErrorCodeEnum.PASSENGERINFO_DATA_INSERT_FAIL.getDescriptions());
        }
        return DataResult.success(ids);
    }
	
    @PostMapping(value="/get", produces="application/json")
    public DataResult<PassengerInfoDto> get (@RequestBody PassengerInfoVO passengerInfoVO) {
		logger.debug("的get passengerInfoVO={}",JsonUtil.toJson(passengerInfoVO));
        PassengerInfo passengerInfo = new PassengerInfo();
        BeanUtil.copyProperties(passengerInfoVO, passengerInfo);
		passengerInfo = passengerInfoService.getByPrimaryKey(passengerInfo);
		logger.debug("的get result={}", passengerInfo);
		PassengerInfoDto passengerInfoDto = new PassengerInfoDto();
		BeanUtil.copyProperties(passengerInfo, passengerInfoDto);
		return DataResult.success(passengerInfoDto);
    }
    
	
    @PostMapping(value="/getList", produces="application/json")
    public DataResult<List<PassengerInfoDto>> getList(@RequestBody PassengerInfoVO passengerInfoVO) {
        logger.debug("的getList passengerInfoVO={}",JsonUtil.toJson( passengerInfoVO));
        List<PassengerInfo> passengerInfoList = passengerInfoService.getList(passengerInfoVO);
		List<PassengerInfoDto> passengerInfoDtoList = BeanUtil.copyList(passengerInfoList, PassengerInfoDto.class); 
		return DataResult.success(passengerInfoDtoList);
    }
    
    
    @PostMapping(value="/getPage", produces="application/json")
    public PageResult<PassengerInfoDto> getPage(@RequestBody PassengerInfoVO  passengerInfoVO) {
        logger.debug("的getListWithPage passengerInfoVO={}", JsonUtil.toJson( passengerInfoVO));
		
		int count = passengerInfoService.getCount(passengerInfoVO);
        if (count < 1) {
            return PageResult.empty();
        }
        List<PassengerInfo> passengerInfoList = passengerInfoService.getPage(passengerInfoVO); 
		List<PassengerInfoDto> passengerInfoDtoList =BeanUtil.copyList(passengerInfoList, PassengerInfoDto.class); 
        PageResult<PassengerInfoDto> passengerInfoDtoPageResult = PageResult.page(passengerInfoVO,count,passengerInfoDtoList);
		return passengerInfoDtoPageResult;
    }
    
    
	
    @PostMapping(value="/count", produces="application/json")
    public DataResult<Integer> getCount(@RequestBody PassengerInfoVO passengerInfoVO) {
        logger.debug("的getCount passengerInfoVO={}", JsonUtil.toJson(passengerInfoVO));
        int count = passengerInfoService.getCount(passengerInfoVO);
        logger.debug("的getCount count={}", count);
        return DataResult.success(count);
    }
    

 
    
}
