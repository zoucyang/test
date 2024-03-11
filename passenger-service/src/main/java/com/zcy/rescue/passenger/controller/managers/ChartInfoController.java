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
import com.zcy.rescue.passenger.dto.ChartInfoDto;
import com.zcy.rescue.passenger.entity.ChartInfo;
import com.zcy.rescue.passenger.enums.ChartInfoErrorCodeEnum;
import com.zcy.rescue.passenger.service.ChartInfoService;
import com.zcy.rescue.passenger.vo.ChartInfoVO;
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
 * 管理端/微信信息表的Rest实现
 *
 * @author zcy
 * @date 2024-2-5
 */
 @Api(tags = "微信信息表的Rest实现")
@RestController
@RequestMapping("/chartInfo")
public class ChartInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(ChartInfoController.class);

    /**
     * 注入的微信信息表的Service接口实现
     */
	@Autowired
	private ChartInfoService chartInfoService;
	
    @PostMapping(value="/save", produces="application/json")
    public DataResult<ChartInfoDto> save(@RequestBody ChartInfoVO chartInfoVO) {
        logger.debug("微信信息表的save chartInfo={}", JsonUtil.toJson(chartInfoVO));
        ChartInfo chartInfo = new ChartInfo();
		BeanUtil.copyProperties(chartInfoVO, chartInfo);
        chartInfo = chartInfoService.insertSelective(chartInfo);
        logger.debug("微信信息表的save result={}",JsonUtil.toJson(chartInfo));
		if(chartInfo != null){
			ChartInfoDto chartInfoDto = new ChartInfoDto();
			BeanUtil.copyProperties(chartInfo, chartInfoDto);
			return DataResult.success(chartInfoDto);
		}
		return DataResult.success(null);
		
    }
    
    @PostMapping(value="/update", produces="application/json")
    public DataResult<ChartInfoDto> update(@RequestBody ChartInfoVO chartInfoVO) {
        logger.debug("微信信息表的update chartInfo={}", JsonUtil.toJson(chartInfoVO));
        ChartInfo chartInfo = new ChartInfo();
		BeanUtil.copyProperties(chartInfoVO, chartInfo);
        chartInfo = chartInfoService.updateByPrimaryKeySelective(chartInfo);
        logger.debug("微信信息表的update result={}",JsonUtil.toJson(chartInfo));
		if(chartInfo != null){
			ChartInfoDto chartInfoDto = new ChartInfoDto();
			BeanUtil.copyProperties(chartInfo, chartInfoDto);
			return DataResult.success(chartInfoDto);
		}
		return DataResult.success(null);
    }
	/**
     * 根据主键删除记录(逻辑删除)
	 * @param chartInfoVO 微信信息表
     * @return
     */
    @ApiOperation("根据主键删除记录(逻辑删除)")
    @PostMapping(value="/delete", produces="application/json")
    public DataResult<ChartInfoVO> delete(@RequestBody ChartInfoVO chartInfoVO) {
        logger.debug("微信信息表的delete chartInfo={}", JsonUtil.toJson(chartInfoVO));
        ChartInfo chartInfo = new ChartInfo();
        BeanUtil.copyProperties(chartInfoVO, chartInfo);
        int result = chartInfoService.deleteByPrimaryKey(chartInfo);
        logger.debug("微信信息表的delete result={}",result); 
		if (result != 1) {
            throw new SystemException(ChartInfoErrorCodeEnum.CHARTINFO_DATA_DELETE_FAIL.getValue()
                    , ChartInfoErrorCodeEnum.CHARTINFO_DATA_DELETE_FAIL.getDescriptions());
		}  
		return DataResult.success(chartInfoVO);
    }
	/**
     * 根据主键批量删除记录(逻辑删除)
	 * @param chartInfoVO 微信信息表
     * @return
     */
	@ApiOperation("根据主键批量删除记录(逻辑删除),idList必须传值")
    @PostMapping(value="/deleteBatch", produces="application/json")
    public DataResult<ChartInfoVO> deleteBatch(@RequestBody ChartInfoVO chartInfoVO) {
        logger.debug("微信信息表的deleteBatch chartInfo={}", JsonUtil.toJson(chartInfoVO)); 
        int result = chartInfoService.deleteToUpdate(chartInfoVO);
        logger.debug("微信信息表的deleteBatch result={}",result); 
		if (result < 1) {
            throw new SystemException(ChartInfoErrorCodeEnum.CHARTINFO_DATA_DELETE_FAIL.getValue()
                    , ChartInfoErrorCodeEnum.CHARTINFO_DATA_DELETE_FAIL.getDescriptions());
		}  
		return DataResult.success(chartInfoVO);
    }
    /**
     * 根据主键批量更新状态
	 * @param chartInfoVO 微信信息表
     * @return
     */
	@ApiOperation("根据主键批量更新状态,idList必须传值")
    @PostMapping(value="/updateBatchStatus", produces="application/json")
    public DataResult<ChartInfoVO> updateBatchStatus(@RequestBody ChartInfoVO chartInfoVO) {
        logger.debug("微信信息表的updateBatchStatus chartInfo={}", JsonUtil.toJson(chartInfoVO)); 
        int result = chartInfoService.updateBatchStatus(chartInfoVO);
        logger.debug("微信信息表的updateBatchStatus result={}",result); 
		if (result < 1) { 
            throw new SystemException(ChartInfoErrorCodeEnum.CHARTINFO_DATA_UPDATE_FAIL.getValue()
                    , ChartInfoErrorCodeEnum.CHARTINFO_DATA_UPDATE_FAIL.getDescriptions());
		}  
		return DataResult.success(chartInfoVO);
    }
    
    /**
     * 批量添加数据
     * @param chartInfoList 字典管理
     * @return
     */
    @ApiOperation("批量添加数据")
    @PostMapping(value="/insertBatch", produces="application/json")
    public DataResult<List<Long>> insertBatch(@RequestBody List<ChartInfo> chartInfoList) {
        logger.debug("微信信息表insertBatch chartInfo={}", JsonUtil.toJson(chartInfoList));
        List<Long> ids = chartInfoService.insertBatch(chartInfoList);
        logger.debug("微信信息表insertBatch result={}",JsonUtil.toJson(ids));
        if (CollectionUtils.isEmpty(ids)) {
            throw new SystemException(ChartInfoErrorCodeEnum.CHARTINFO_DATA_INSERT_FAIL.getValue()
                    , ChartInfoErrorCodeEnum.CHARTINFO_DATA_INSERT_FAIL.getDescriptions());
        }
        return DataResult.success(ids);
    }
    @PostMapping(value="/get", produces="application/json")
    public DataResult<ChartInfoDto> get (@RequestBody ChartInfoVO chartInfoVO) {
		logger.debug("微信信息表的get chartInfoVO={}",JsonUtil.toJson(chartInfoVO));
        ChartInfo chartInfo = new ChartInfo();
        BeanUtil.copyProperties(chartInfoVO, chartInfo);
		chartInfo = chartInfoService.getByPrimaryKey(chartInfo);
		logger.debug("微信信息表的get result={}", chartInfo);
		ChartInfoDto chartInfoDto = new ChartInfoDto();
		BeanUtil.copyProperties(chartInfo, chartInfoDto);
		return DataResult.success(chartInfoDto);
    }
    
    @PostMapping(value="/getList", produces="application/json")
    public DataResult<List<ChartInfoDto>> getList(@RequestBody ChartInfoVO chartInfoVO) {
        logger.debug("微信信息表的getList chartInfoVO={}",JsonUtil.toJson( chartInfoVO));
        List<ChartInfo> chartInfoList = chartInfoService.getList(chartInfoVO);
		List<ChartInfoDto> chartInfoDtoList = BeanUtil.copyList(chartInfoList, ChartInfoDto.class); 
		return DataResult.success(chartInfoDtoList);
    }
    
    @PostMapping(value="/getPage", produces="application/json")
    public PageResult<ChartInfoDto> getPage(@RequestBody ChartInfoVO  chartInfoVO) {
        logger.debug("微信信息表的getListWithPage chartInfoVO={}", JsonUtil.toJson( chartInfoVO));
		
		int count = chartInfoService.getCount(chartInfoVO);
        if (count < 1) {
            return PageResult.empty();
        }
        List<ChartInfo> chartInfoList = chartInfoService.getPage(chartInfoVO); 
		List<ChartInfoDto> chartInfoDtoList =BeanUtil.copyList(chartInfoList, ChartInfoDto.class); 
        PageResult<ChartInfoDto> chartInfoDtoPageResult = PageResult.page(chartInfoVO,count,chartInfoDtoList);
		return chartInfoDtoPageResult;
    }
    
    
	
    @PostMapping(value="/count", produces="application/json")
    public DataResult<Integer> getCount(@RequestBody ChartInfoVO chartInfoVO) {
        logger.debug("微信信息表的getCount chartInfoVO={}", JsonUtil.toJson(chartInfoVO));
        int count = chartInfoService.getCount(chartInfoVO);
        logger.debug("微信信息表的getCount count={}", count);
        return DataResult.success(count);
    }
    

 
    
}
