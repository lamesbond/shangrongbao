package com.liubusi.shangrongbao.core.controller.admin;

import com.alibaba.excel.EasyExcel;
import com.liubusi.common.exception.BusinessException;
import com.liubusi.common.result.ResponseEnum;
import com.liubusi.common.result.Result;
import com.liubusi.shangrongbao.core.pojo.dto.ExcelDictDTO;
import com.liubusi.shangrongbao.core.pojo.entity.Dict;
import com.liubusi.shangrongbao.core.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 * 字典管理表 前端控制器
 * </p>
 *
 * @author liubusi
 * @since 2022-05-22
 */
@Api(tags = "数据字典管理")
@RestController
@RequestMapping("/admin/core/dict")
@Slf4j
public class AdminDictController {

    @Resource
    private DictService dictService;

    @ApiOperation("Excel批量导入数据字典")
    @PostMapping("/import")
    public Result batchImport(@ApiParam(value = "Excel文件", required = true)
                                  @RequestParam("file") MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            dictService.importData(inputStream);
            return Result.ok().message("批量导入成功");
        } catch (Exception e) {
            //UPLOAD_ERROR(-103, "文件上传错误"),
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR, e);
        }
    }

    @ApiOperation("Excel数据的导出")
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("mydict", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), ExcelDictDTO.class).sheet("数据字典").doWrite(dictService.listDictData());
    }

    @ApiOperation("根据上级ID获取子节点数据列表")
    @GetMapping("/listByParentId/{parentId}")
    public Result listByParentId(@ApiParam(value = "上级节点id", required = true)
            @PathVariable Long parentId){
        List<Dict> dictList = dictService.listByParentId(parentId);
        return Result.ok().data("list",dictList);
    }

}

