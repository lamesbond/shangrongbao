package com.liubusi.shangrongbao.core.controller.admin;


import com.liubusi.common.result.Result;
import com.liubusi.shangrongbao.core.pojo.entity.Lend;
import com.liubusi.shangrongbao.core.service.LendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 标的准备表 前端控制器
 * </p>
 *
 * @author liubusi
 * @since 2022-05-21
 */
@Api(tags = "标的管理")
@RestController
@RequestMapping("/admin/core/lend")
@Slf4j
public class AdminLendController {
    @Resource
    private LendService lendService;

    @ApiOperation("标的列表")
    @GetMapping("/list")
    public Result list() {
        List<Lend> lendList = lendService.selectList();
        return Result.ok().data("list", lendList);
    }

    @ApiOperation("获取标的信息")
    @GetMapping("/show/{id}")
    public Result show(
            @ApiParam(value = "标的id", required = true)
            @PathVariable Long id) {
        Map<String, Object> result = lendService.getLendDetail(id);
        return Result.ok().data("lendDetail", result);
    }

    @ApiOperation("放款")
    @GetMapping("/makeLoan/{id}")
    public Result makeLoan(
            @ApiParam(value = "标的id", required = true)
            @PathVariable("id") Long id) {
        lendService.makeLoan(id);
        return Result.ok().message("放款成功");
    }
}

