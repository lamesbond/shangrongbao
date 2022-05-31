package com.liubusi.shangrongbao.core.controller;


import com.liubusi.shangrongbao.core.pojo.entity.IntegralGrade;
import com.liubusi.shangrongbao.core.service.IntegralGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 积分等级表 前端控制器
 * </p>
 *
 * @author liubusi
 * @since 2022-05-21
 */
@Api(tags = "积分等级管理")

@RestController
@RequestMapping("/api/core/integralGrade")
public class IntegralGradeController {

    @Resource
    private IntegralGradeService integralGradeService;

    @ApiOperation("积分等级列表")
    @GetMapping("/list")
    public List<IntegralGrade> listAll() {
        return integralGradeService.list();
    }


    @ApiOperation(value = "根据id删除积分等级", notes = "逻辑删除")
    @DeleteMapping("/remove/{id}")
    public boolean removeById(@ApiParam(value = "数据id", required = true, example = "100") @PathVariable Long id) {
        return integralGradeService.removeById(id);
    }
}

