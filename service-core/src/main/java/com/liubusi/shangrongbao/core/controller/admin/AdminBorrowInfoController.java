package com.liubusi.shangrongbao.core.controller.admin;

import com.liubusi.common.result.Result;
import com.liubusi.shangrongbao.core.pojo.entity.BorrowInfo;
import com.liubusi.shangrongbao.core.pojo.vo.BorrowInfoApprovalVO;
import com.liubusi.shangrongbao.core.service.BorrowInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Api(tags = "借款管理")
@RestController
@RequestMapping("/admin/core/borrowInfo")
@Slf4j
public class AdminBorrowInfoController {

    @Resource
    private BorrowInfoService borrowInfoService;

    @ApiOperation("借款信息列表")
    @GetMapping("/list")
    public Result list() {
        List<BorrowInfo> borrowInfoList = borrowInfoService.selectList();
        return Result.ok().data("list", borrowInfoList);
    }

    @ApiOperation("获取借款信息")
    @GetMapping("/show/{id}")
    public Result show(@ApiParam(value = "借款id", required = true) @PathVariable Long id) {
        Map<String, Object> borrowInfoDetail = borrowInfoService.getBorrowInfoDetail(id);
        return Result.ok().data("borrowInfoDetail", borrowInfoDetail);
    }

    @ApiOperation("审批借款信息")
    @PostMapping("/approval")
    public Result approval(@RequestBody BorrowInfoApprovalVO borrowInfoApprovalVO) {
        borrowInfoService.approval(borrowInfoApprovalVO);
        return Result.ok().message("审批完成");
    }
}
