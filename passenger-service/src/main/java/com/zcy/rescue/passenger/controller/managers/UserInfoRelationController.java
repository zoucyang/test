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
import com.zcy.rescue.passenger.dto.UserInfoRelationDto;
import com.zcy.rescue.passenger.entity.UserInfoRelation;
import com.zcy.rescue.passenger.enums.UserInfoRelationErrorCodeEnum;
import com.zcy.rescue.passenger.service.UserInfoRelationService;
import com.zcy.rescue.passenger.vo.UserInfoRelationVO;
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
 * 管理端/用户信息关系表的Rest实现
 *
 * @author zcy
 * @date 2024-2-5
 */
 @Api(tags = "用户信息关系表的Rest实现")
@RestController
@RequestMapping("/userInfoRelation")
public class UserInfoRelationController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserInfoRelationController.class);

    /**
     * 注入的用户信息关系表的Service接口实现
     */
	@Autowired
	private UserInfoRelationService userInfoRelationService;
	
    @PostMapping(value="/save", produces="application/json")
    public DataResult<UserInfoRelationDto> save(@RequestBody UserInfoRelationVO userInfoRelationVO) {
        logger.debug("用户信息关系表的save userInfoRelation={}", JsonUtil.toJson(userInfoRelationVO));
        UserInfoRelation userInfoRelation = new UserInfoRelation();
		BeanUtil.copyProperties(userInfoRelationVO, userInfoRelation);
        userInfoRelation = userInfoRelationService.insertSelective(userInfoRelation);
        logger.debug("用户信息关系表的save result={}",JsonUtil.toJson(userInfoRelation));
		if(userInfoRelation != null){
			UserInfoRelationDto userInfoRelationDto = new UserInfoRelationDto();
			BeanUtil.copyProperties(userInfoRelation, userInfoRelationDto);
			return DataResult.success(userInfoRelationDto);
		}
		return DataResult.success(null);
		
    }
    
    @PostMapping(value="/update", produces="application/json")
    public DataResult<UserInfoRelationDto> update(@RequestBody UserInfoRelationVO userInfoRelationVO) {
        logger.debug("用户信息关系表的update userInfoRelation={}", JsonUtil.toJson(userInfoRelationVO));
        UserInfoRelation userInfoRelation = new UserInfoRelation();
		BeanUtil.copyProperties(userInfoRelationVO, userInfoRelation);
        userInfoRelation = userInfoRelationService.updateByPrimaryKeySelective(userInfoRelation);
        logger.debug("用户信息关系表的update result={}",JsonUtil.toJson(userInfoRelation));
		if(userInfoRelation != null){
			UserInfoRelationDto userInfoRelationDto = new UserInfoRelationDto();
			BeanUtil.copyProperties(userInfoRelation, userInfoRelationDto);
			return DataResult.success(userInfoRelationDto);
		}
		return DataResult.success(null);
    }
	/**
     * 根据主键删除记录(逻辑删除)
	 * @param userInfoRelationVO 用户信息关系表
     * @return
     */
    @ApiOperation("根据主键删除记录(逻辑删除)")
    @PostMapping(value="/delete", produces="application/json")
    public DataResult<UserInfoRelationVO> delete(@RequestBody UserInfoRelationVO userInfoRelationVO) {
        logger.debug("用户信息关系表的delete userInfoRelation={}", JsonUtil.toJson(userInfoRelationVO));
        UserInfoRelation userInfoRelation = new UserInfoRelation();
        BeanUtil.copyProperties(userInfoRelationVO, userInfoRelation);
        int result = userInfoRelationService.deleteByPrimaryKey(userInfoRelation);
        logger.debug("用户信息关系表的delete result={}",result); 
		if (result != 1) {
            throw new SystemException(UserInfoRelationErrorCodeEnum.USERINFORELATION_DATA_DELETE_FAIL.getValue()
                    , UserInfoRelationErrorCodeEnum.USERINFORELATION_DATA_DELETE_FAIL.getDescriptions());
		}  
		return DataResult.success(userInfoRelationVO);
    }
	/**
     * 根据主键批量删除记录(逻辑删除)
	 * @param userInfoRelationVO 用户信息关系表
     * @return
     */
	@ApiOperation("根据主键批量删除记录(逻辑删除),idList必须传值")
    @PostMapping(value="/deleteBatch", produces="application/json")
    public DataResult<UserInfoRelationVO> deleteBatch(@RequestBody UserInfoRelationVO userInfoRelationVO) {
        logger.debug("用户信息关系表的deleteBatch userInfoRelation={}", JsonUtil.toJson(userInfoRelationVO)); 
        int result = userInfoRelationService.deleteToUpdate(userInfoRelationVO);
        logger.debug("用户信息关系表的deleteBatch result={}",result); 
		if (result < 1) {
            throw new SystemException(UserInfoRelationErrorCodeEnum.USERINFORELATION_DATA_DELETE_FAIL.getValue()
                    , UserInfoRelationErrorCodeEnum.USERINFORELATION_DATA_DELETE_FAIL.getDescriptions());
		}  
		return DataResult.success(userInfoRelationVO);
    }
    /**
     * 根据主键批量更新状态
	 * @param userInfoRelationVO 用户信息关系表
     * @return
     */
	@ApiOperation("根据主键批量更新状态,idList必须传值")
    @PostMapping(value="/updateBatchStatus", produces="application/json")
    public DataResult<UserInfoRelationVO> updateBatchStatus(@RequestBody UserInfoRelationVO userInfoRelationVO) {
        logger.debug("用户信息关系表的updateBatchStatus userInfoRelation={}", JsonUtil.toJson(userInfoRelationVO)); 
        int result = userInfoRelationService.updateBatchStatus(userInfoRelationVO);
        logger.debug("用户信息关系表的updateBatchStatus result={}",result); 
		if (result < 1) { 
            throw new SystemException(UserInfoRelationErrorCodeEnum.USERINFORELATION_DATA_UPDATE_FAIL.getValue()
                    , UserInfoRelationErrorCodeEnum.USERINFORELATION_DATA_UPDATE_FAIL.getDescriptions());
		}  
		return DataResult.success(userInfoRelationVO);
    }
    
    /**
     * 批量添加数据
     * @param userInfoRelationList 字典管理
     * @return
     */
    @ApiOperation("批量添加数据")
    @PostMapping(value="/insertBatch", produces="application/json")
    public DataResult<List<Long>> insertBatch(@RequestBody List<UserInfoRelation> userInfoRelationList) {
        logger.debug("用户信息关系表insertBatch userInfoRelation={}", JsonUtil.toJson(userInfoRelationList));
        List<Long> ids = userInfoRelationService.insertBatch(userInfoRelationList);
        logger.debug("用户信息关系表insertBatch result={}",JsonUtil.toJson(ids));
        if (CollectionUtils.isEmpty(ids)) {
            throw new SystemException(UserInfoRelationErrorCodeEnum.USERINFORELATION_DATA_INSERT_FAIL.getValue()
                    , UserInfoRelationErrorCodeEnum.USERINFORELATION_DATA_INSERT_FAIL.getDescriptions());
        }
        return DataResult.success(ids);
    }
    @PostMapping(value="/get", produces="application/json")
    public DataResult<UserInfoRelationDto> get (@RequestBody UserInfoRelationVO userInfoRelationVO) {
		logger.debug("用户信息关系表的get userInfoRelationVO={}",JsonUtil.toJson(userInfoRelationVO));
        UserInfoRelation userInfoRelation = new UserInfoRelation();
        BeanUtil.copyProperties(userInfoRelationVO, userInfoRelation);
		userInfoRelation = userInfoRelationService.getByPrimaryKey(userInfoRelation);
		logger.debug("用户信息关系表的get result={}", userInfoRelation);
		UserInfoRelationDto userInfoRelationDto = new UserInfoRelationDto();
		BeanUtil.copyProperties(userInfoRelation, userInfoRelationDto);
		return DataResult.success(userInfoRelationDto);
    }
    
    @PostMapping(value="/getList", produces="application/json")
    public DataResult<List<UserInfoRelationDto>> getList(@RequestBody UserInfoRelationVO userInfoRelationVO) {
        logger.debug("用户信息关系表的getList userInfoRelationVO={}",JsonUtil.toJson( userInfoRelationVO));
        List<UserInfoRelation> userInfoRelationList = userInfoRelationService.getList(userInfoRelationVO);
		List<UserInfoRelationDto> userInfoRelationDtoList = BeanUtil.copyList(userInfoRelationList, UserInfoRelationDto.class); 
		return DataResult.success(userInfoRelationDtoList);
    }
    
    @PostMapping(value="/getPage", produces="application/json")
    public PageResult<UserInfoRelationDto> getPage(@RequestBody UserInfoRelationVO  userInfoRelationVO) {
        logger.debug("用户信息关系表的getListWithPage userInfoRelationVO={}", JsonUtil.toJson( userInfoRelationVO));
		
		int count = userInfoRelationService.getCount(userInfoRelationVO);
        if (count < 1) {
            return PageResult.empty();
        }
        List<UserInfoRelation> userInfoRelationList = userInfoRelationService.getPage(userInfoRelationVO); 
		List<UserInfoRelationDto> userInfoRelationDtoList =BeanUtil.copyList(userInfoRelationList, UserInfoRelationDto.class); 
        PageResult<UserInfoRelationDto> userInfoRelationDtoPageResult = PageResult.page(userInfoRelationVO,count,userInfoRelationDtoList);
		return userInfoRelationDtoPageResult;
    }
    
    
	
    @PostMapping(value="/count", produces="application/json")
    public DataResult<Integer> getCount(@RequestBody UserInfoRelationVO userInfoRelationVO) {
        logger.debug("用户信息关系表的getCount userInfoRelationVO={}", JsonUtil.toJson(userInfoRelationVO));
        int count = userInfoRelationService.getCount(userInfoRelationVO);
        logger.debug("用户信息关系表的getCount count={}", count);
        return DataResult.success(count);
    }
    

 
    
}
