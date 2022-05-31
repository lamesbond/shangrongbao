package com.liubusi.shangrongbao.core.controller.admin;


import com.liubusi.common.result.Result;
import com.liubusi.shangrongbao.core.mapper.UserLoginRecordMapper;
import com.liubusi.shangrongbao.core.pojo.entity.UserLoginRecord;
import com.liubusi.shangrongbao.core.service.UserLoginRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户登录记录表 前端控制器
 * </p>
 *
 * @author liubusi
 * @since 2022-05-21
 */
@Api(tags = "会员登录日志接口")
@RestController
@RequestMapping("/admin/core/userLoginRecord")
@Slf4j
public class AdminUserLoginRecordController {

    @Resource
    private UserLoginRecordService userLoginRecordService;

    @ApiOperation("获取会员登录日志列表")
    @GetMapping("/listTop50/{userId}")
    public Result listTop50(@ApiParam(value = "用户id", required = true) @PathVariable Long userId) {
        List<UserLoginRecord> userLoginRecordList = userLoginRecordService.listTop50(userId);
        return Result.ok().data("list", userLoginRecordList);
    }
}

