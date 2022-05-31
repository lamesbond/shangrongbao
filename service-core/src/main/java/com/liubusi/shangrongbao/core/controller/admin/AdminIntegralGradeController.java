package com.liubusi.shangrongbao.core.controller.admin;

import com.liubusi.common.exception.Assert;
import com.liubusi.common.result.ResponseEnum;
import com.liubusi.common.result.Result;
import com.liubusi.shangrongbao.core.pojo.entity.IntegralGrade;
import com.liubusi.shangrongbao.core.service.IntegralGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/admin/core/integralGrade")
@Slf4j
public class AdminIntegralGradeController {

    @Resource
    private IntegralGradeService integralGradeService;

    @ApiOperation("积分等级列表")
    @GetMapping("/list")
    public Result listAll() {
        log.info("hi i'm helen");
        log.warn("warning!!!");
        log.error("it's a error");
        List<IntegralGrade> list = integralGradeService.list();
        return Result.ok().data("list",list).message("成功获取列表");
    }

    @ApiOperation(value = "根据id删除积分等级", notes = "逻辑删除")
    @DeleteMapping("/remove/{id}")
    public Result removeById(@ApiParam(value = "数据id", required = true, example = "100") @PathVariable Long id) {
        boolean isSucceed = integralGradeService.removeById(id);
        return Result.ok().data("isSucceed", isSucceed).message("删除成功");
    }

    @ApiOperation(value = "新增积分等级")
    @PostMapping("/save")
    public Result save(@ApiParam(value = "积分等级对象", required = true)
                           @RequestBody IntegralGrade integralGrade) {
//        if (integralGrade.getBorrowAmount() == null) {
//            throw new BusinessException(ResponseEnum.BORROW_AMOUNT_NULL_ERROR);
//        }
        Assert.notNull(integralGrade.getBorrowAmount(),ResponseEnum.BORROW_AMOUNT_NULL_ERROR);
//        throw new BusinessException();

        boolean isSucceed = integralGradeService.save(integralGrade);
        if (isSucceed) {
            return Result.ok().message("保存成功");
        }else {
            return Result.error().message("保存失败");
        }
    }

    @ApiOperation(value = "根据ID获取积分等级")
    @GetMapping("/get/{id}")
    public Result getById(@ApiParam(value = "数据id", required = true, example = "1")
                              @PathVariable Long id) {
        IntegralGrade integralGrade = integralGradeService.getById(id);
        if (integralGrade != null) {
            return Result.ok().data("record", integralGrade);
        }else {
            return Result.error().message("数据获取失败");
        }
    }

    @ApiOperation(value = "修改积分等级")
    @PutMapping("/update")
    public Result updateById(@ApiParam(value = "积分等级对象", required = true)
                                 @RequestBody IntegralGrade integralGrade) {
        boolean isSucceed = integralGradeService.updateById(integralGrade);
        if (isSucceed) {
            return Result.ok().message("更新成功");
        }else {
            return Result.error().message("更新失败");
        }
    }
}

