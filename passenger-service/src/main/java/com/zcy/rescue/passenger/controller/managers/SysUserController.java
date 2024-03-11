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
import com.zcy.rescue.passenger.dto.SysUserDto;
import com.zcy.rescue.passenger.entity.SysUser;
import com.zcy.rescue.passenger.enums.SysUserErrorCodeEnum;
import com.zcy.rescue.passenger.service.SysUserService;
import com.zcy.rescue.passenger.vo.SysUserVO;
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
 * 管理端/用户的Rest实现
 *
 * @author zcy
 * @date 2024-1-28
 */
 @Api(tags = "用户的Rest实现")
@RestController
@RequestMapping("/sysUser")
public class SysUserController {
	
	private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    /**
     * 注入的的Service接口实现
     */
	@Autowired
	private SysUserService sysUserService;
	
    @PostMapping(value="/save", produces="application/json")
	
    public DataResult<SysUserDto> save(@RequestBody SysUserVO sysUserVO) {
        logger.debug("的save sysUser={}", JsonUtil.toJson(sysUserVO));
        SysUser sysUser = new SysUser();
		BeanUtil.copyProperties(sysUserVO, sysUser);
        sysUser = sysUserService.insertSelective(sysUser);
        logger.debug("的save result={}",JsonUtil.toJson(sysUser));
		if(sysUser != null){
			SysUserDto sysUserDto = new SysUserDto();
			BeanUtil.copyProperties(sysUser, sysUserDto);
			return DataResult.success(sysUserDto);
		}
		return DataResult.success(null);
		
    }
    
	
    @PostMapping(value="/update", produces="application/json")
    public DataResult<SysUserDto> update(@RequestBody SysUserVO sysUserVO) {
        logger.debug("的update sysUser={}", JsonUtil.toJson(sysUserVO));
        SysUser sysUser = new SysUser();
		BeanUtil.copyProperties(sysUserVO, sysUser);
        sysUser = sysUserService.updateByPrimaryKeySelective(sysUser);
        logger.debug("的update result={}",JsonUtil.toJson(sysUser));
		if(sysUser != null){
			SysUserDto sysUserDto = new SysUserDto();
			BeanUtil.copyProperties(sysUser, sysUserDto);
			return DataResult.success(sysUserDto);
		}
		return DataResult.success(null);
    }
	/**
     * 根据主键删除记录(逻辑删除)
	 * @param sysUserVO 
     * @return
     */
    @ApiOperation("根据主键删除记录(逻辑删除)")
    @PostMapping(value="/delete", produces="application/json")
    public DataResult<SysUserVO> delete(@RequestBody SysUserVO sysUserVO) {
        logger.debug("的delete sysUser={}", JsonUtil.toJson(sysUserVO));
        SysUser sysUser = new SysUser();
        BeanUtil.copyProperties(sysUserVO, sysUser);
        int result = sysUserService.deleteByPrimaryKey(sysUser);
        logger.debug("的delete result={}",result); 
		if (result != 1) {
            throw new SystemException(SysUserErrorCodeEnum.SYSUSER_DATA_DELETE_FAIL.getValue()
                    , SysUserErrorCodeEnum.SYSUSER_DATA_DELETE_FAIL.getDescriptions());
		}  
		return DataResult.success(sysUserVO);
    }
	/**
     * 根据主键批量删除记录(逻辑删除)
	 * @param sysUserVO 
     * @return
     */
	@ApiOperation("根据主键批量删除记录(逻辑删除),idList必须传值")
    @PostMapping(value="/deleteBatch", produces="application/json")
    public DataResult<SysUserVO> deleteBatch(@RequestBody SysUserVO sysUserVO) {
        logger.debug("的deleteBatch sysUser={}", JsonUtil.toJson(sysUserVO)); 
        int result = sysUserService.deleteToUpdate(sysUserVO);
        logger.debug("的deleteBatch result={}",result); 
		if (result < 1) {
            throw new SystemException(SysUserErrorCodeEnum.SYSUSER_DATA_DELETE_FAIL.getValue()
                    , SysUserErrorCodeEnum.SYSUSER_DATA_DELETE_FAIL.getDescriptions());
		}  
		return DataResult.success(sysUserVO);
    }
    /**
     * 根据主键批量更新状态
	 * @param sysUserVO 
     * @return
     */
	@ApiOperation("根据主键批量更新状态,idList必须传值")
    @PostMapping(value="/updateBatchStatus", produces="application/json")
    public DataResult<SysUserVO> updateBatchStatus(@RequestBody SysUserVO sysUserVO) {
        logger.debug("的updateBatchStatus sysUser={}", JsonUtil.toJson(sysUserVO)); 
        int result = sysUserService.updateBatchStatus(sysUserVO);
        logger.debug("的updateBatchStatus result={}",result); 
		if (result < 1) { 
            throw new SystemException(SysUserErrorCodeEnum.SYSUSER_DATA_UPDATE_FAIL.getValue()
                    , SysUserErrorCodeEnum.SYSUSER_DATA_UPDATE_FAIL.getDescriptions());
		}  
		return DataResult.success(sysUserVO);
    }
    
    /**
     * 批量添加数据
     * @param sysUserList 字典管理
     * @return
     */
    @ApiOperation("批量添加数据")
    @PostMapping(value="/insertBatch", produces="application/json")
    public DataResult<List<Long>> insertBatch(@RequestBody List<SysUser> sysUserList) {
        logger.debug("insertBatch sysUser={}", JsonUtil.toJson(sysUserList));
        List<Long> ids = sysUserService.insertBatch(sysUserList);
        logger.debug("insertBatch result={}",JsonUtil.toJson(ids));
        if (CollectionUtils.isEmpty(ids)) {
            throw new SystemException(SysUserErrorCodeEnum.SYSUSER_DATA_INSERT_FAIL.getValue()
                    , SysUserErrorCodeEnum.SYSUSER_DATA_INSERT_FAIL.getDescriptions());
        }
        return DataResult.success(ids);
    }
	
    @PostMapping(value="/get", produces="application/json")
    public DataResult<SysUserDto> get (@RequestBody SysUserVO sysUserVO) {
		logger.debug("的get sysUserVO={}",JsonUtil.toJson(sysUserVO));
        SysUser sysUser = new SysUser();
        BeanUtil.copyProperties(sysUserVO, sysUser);
		sysUser = sysUserService.getByPrimaryKey(sysUser);
		logger.debug("的get result={}", sysUser);
		SysUserDto sysUserDto = new SysUserDto();
		BeanUtil.copyProperties(sysUser, sysUserDto);
		return DataResult.success(sysUserDto);
    }
    
	
    @PostMapping(value="/getList", produces="application/json")
    public DataResult<List<SysUserDto>> getList(@RequestBody SysUserVO sysUserVO) {
        logger.debug("的getList sysUserVO={}",JsonUtil.toJson( sysUserVO));
        List<SysUser> sysUserList = sysUserService.getList(sysUserVO);
		List<SysUserDto> sysUserDtoList = BeanUtil.copyList(sysUserList, SysUserDto.class); 
		return DataResult.success(sysUserDtoList);
    }
    
    
    @PostMapping(value="/getPage", produces="application/json")
    public PageResult<SysUserDto> getPage(@RequestBody SysUserVO  sysUserVO) {
        logger.debug("的getListWithPage sysUserVO={}", JsonUtil.toJson( sysUserVO));
		
		int count = sysUserService.getCount(sysUserVO);
        if (count < 1) {
            return PageResult.empty();
        }
        List<SysUser> sysUserList = sysUserService.getPage(sysUserVO); 
		List<SysUserDto> sysUserDtoList =BeanUtil.copyList(sysUserList, SysUserDto.class); 
        PageResult<SysUserDto> sysUserDtoPageResult = PageResult.page(sysUserVO,count,sysUserDtoList);
		return sysUserDtoPageResult;
    }
    
    
	
    @PostMapping(value="/count", produces="application/json")
    public DataResult<Integer> getCount(@RequestBody SysUserVO sysUserVO) {
        logger.debug("的getCount sysUserVO={}", JsonUtil.toJson(sysUserVO));
        int count = sysUserService.getCount(sysUserVO);
        logger.debug("的getCount count={}", count);
        return DataResult.success(count);
    }
    

 
    
}
