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
import com.zcy.rescue.passenger.dto.UserCarDto;
import com.zcy.rescue.passenger.entity.UserCar;
import com.zcy.rescue.passenger.enums.error.UserCarErrorCodeEnum;
import com.zcy.rescue.passenger.service.UserCarService;
import com.zcy.rescue.passenger.vo.UserCarVO;
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
 * 管理端/用户车辆关系表的Rest实现
 *
 * @author zcy
 * @date 2024-1-28
 */
 @Api(tags = "用户车辆关系表的Rest实现")
@RestController
@RequestMapping("/userCar")
public class UserCarController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserCarController.class);

    /**
     * 注入的用户车辆关系表的Service接口实现
     */
	@Autowired
	private UserCarService userCarService;
	
    @PostMapping(value="/save", produces="application/json")
	
    public DataResult<UserCarDto> save(@RequestBody UserCarVO userCarVO) {
        logger.debug("用户车辆关系表的save userCar={}", JsonUtil.toJson(userCarVO));
        UserCar userCar = new UserCar();
		BeanUtil.copyProperties(userCarVO, userCar);
        userCar = userCarService.insertSelective(userCar);
        logger.debug("用户车辆关系表的save result={}",JsonUtil.toJson(userCar));
		if(userCar != null){
			UserCarDto userCarDto = new UserCarDto();
			BeanUtil.copyProperties(userCar, userCarDto);
			return DataResult.success(userCarDto);
		}
		return DataResult.success(null);
		
    }
    
	
    @PostMapping(value="/update", produces="application/json")
    public DataResult<UserCarDto> update(@RequestBody UserCarVO userCarVO) {
        logger.debug("用户车辆关系表的update userCar={}", JsonUtil.toJson(userCarVO));
        UserCar userCar = new UserCar();
		BeanUtil.copyProperties(userCarVO, userCar);
        userCar = userCarService.updateByPrimaryKeySelective(userCar);
        logger.debug("用户车辆关系表的update result={}",JsonUtil.toJson(userCar));
		if(userCar != null){
			UserCarDto userCarDto = new UserCarDto();
			BeanUtil.copyProperties(userCar, userCarDto);
			return DataResult.success(userCarDto);
		}
		return DataResult.success(null);
    }
	/**
     * 根据主键删除记录(逻辑删除)
	 * @param userCarVO 用户车辆关系表
     * @return
     */
    @ApiOperation("根据主键删除记录(逻辑删除)")
    @PostMapping(value="/delete", produces="application/json")
    public DataResult<UserCarVO> delete(@RequestBody UserCarVO userCarVO) {
        logger.debug("用户车辆关系表的delete userCar={}", JsonUtil.toJson(userCarVO));
        UserCar userCar = new UserCar();
        BeanUtil.copyProperties(userCarVO, userCar);
        int result = userCarService.deleteByPrimaryKey(userCar);
        logger.debug("用户车辆关系表的delete result={}",result); 
		if (result != 1) {
            throw new SystemException(UserCarErrorCodeEnum.USERCAR_DATA_DELETE_FAIL.getValue()
                    , UserCarErrorCodeEnum.USERCAR_DATA_DELETE_FAIL.getDescriptions());
		}  
		return DataResult.success(userCarVO);
    }
	/**
     * 根据主键批量删除记录(逻辑删除)
	 * @param userCarVO 用户车辆关系表
     * @return
     */
	@ApiOperation("根据主键批量删除记录(逻辑删除),idList必须传值")
    @PostMapping(value="/deleteBatch", produces="application/json")
    public DataResult<UserCarVO> deleteBatch(@RequestBody UserCarVO userCarVO) {
        logger.debug("用户车辆关系表的deleteBatch userCar={}", JsonUtil.toJson(userCarVO)); 
        int result = userCarService.deleteToUpdate(userCarVO);
        logger.debug("用户车辆关系表的deleteBatch result={}",result); 
		if (result < 1) {
            throw new SystemException(UserCarErrorCodeEnum.USERCAR_DATA_DELETE_FAIL.getValue()
                    , UserCarErrorCodeEnum.USERCAR_DATA_DELETE_FAIL.getDescriptions());
		}  
		return DataResult.success(userCarVO);
    }
    /**
     * 根据主键批量更新状态
	 * @param userCarVO 用户车辆关系表
     * @return
     */
	@ApiOperation("根据主键批量更新状态,idList必须传值")
    @PostMapping(value="/updateBatchStatus", produces="application/json")
    public DataResult<UserCarVO> updateBatchStatus(@RequestBody UserCarVO userCarVO) {
        logger.debug("用户车辆关系表的updateBatchStatus userCar={}", JsonUtil.toJson(userCarVO)); 
        int result = userCarService.updateBatchStatus(userCarVO);
        logger.debug("用户车辆关系表的updateBatchStatus result={}",result); 
		if (result < 1) { 
            throw new SystemException(UserCarErrorCodeEnum.USERCAR_DATA_UPDATE_FAIL.getValue()
                    , UserCarErrorCodeEnum.USERCAR_DATA_UPDATE_FAIL.getDescriptions());
		}  
		return DataResult.success(userCarVO);
    }
    
    /**
     * 批量添加数据
     * @param userCarList 字典管理
     * @return
     */
    @ApiOperation("批量添加数据")
    @PostMapping(value="/insertBatch", produces="application/json")
    public DataResult<List<Long>> insertBatch(@RequestBody List<UserCar> userCarList) {
        logger.debug("用户车辆关系表insertBatch userCar={}", JsonUtil.toJson(userCarList));
        List<Long> ids = userCarService.insertBatch(userCarList);
        logger.debug("用户车辆关系表insertBatch result={}",JsonUtil.toJson(ids));
        if (CollectionUtils.isEmpty(ids)) {
            throw new SystemException(UserCarErrorCodeEnum.USERCAR_DATA_INSERT_FAIL.getValue()
                    , UserCarErrorCodeEnum.USERCAR_DATA_INSERT_FAIL.getDescriptions());
        }
        return DataResult.success(ids);
    }
	
    @PostMapping(value="/get", produces="application/json")
    public DataResult<UserCarDto> get (@RequestBody UserCarVO userCarVO) {
		logger.debug("用户车辆关系表的get userCarVO={}",JsonUtil.toJson(userCarVO));
        UserCar userCar = new UserCar();
        BeanUtil.copyProperties(userCarVO, userCar);
		userCar = userCarService.getByPrimaryKey(userCar);
		logger.debug("用户车辆关系表的get result={}", userCar);
		UserCarDto userCarDto = new UserCarDto();
		BeanUtil.copyProperties(userCar, userCarDto);
		return DataResult.success(userCarDto);
    }
    
	
    @PostMapping(value="/getList", produces="application/json")
    public DataResult<List<UserCarDto>> getList(@RequestBody UserCarVO userCarVO) {
        logger.debug("用户车辆关系表的getList userCarVO={}",JsonUtil.toJson( userCarVO));
        List<UserCar> userCarList = userCarService.getList(userCarVO);
		List<UserCarDto> userCarDtoList = BeanUtil.copyList(userCarList, UserCarDto.class); 
		return DataResult.success(userCarDtoList);
    }
    
    
    @PostMapping(value="/getPage", produces="application/json")
    public PageResult<UserCarDto> getPage(@RequestBody UserCarVO  userCarVO) {
        logger.debug("用户车辆关系表的getListWithPage userCarVO={}", JsonUtil.toJson( userCarVO));
		
		int count = userCarService.getCount(userCarVO);
        if (count < 1) {
            return PageResult.empty();
        }
        List<UserCar> userCarList = userCarService.getPage(userCarVO); 
		List<UserCarDto> userCarDtoList =BeanUtil.copyList(userCarList, UserCarDto.class); 
        PageResult<UserCarDto> userCarDtoPageResult = PageResult.page(userCarVO,count,userCarDtoList);
		return userCarDtoPageResult;
    }
    
    
	
    @PostMapping(value="/count", produces="application/json")
    public DataResult<Integer> getCount(@RequestBody UserCarVO userCarVO) {
        logger.debug("用户车辆关系表的getCount userCarVO={}", JsonUtil.toJson(userCarVO));
        int count = userCarService.getCount(userCarVO);
        logger.debug("用户车辆关系表的getCount count={}", count);
        return DataResult.success(count);
    }
    

 
    
}
