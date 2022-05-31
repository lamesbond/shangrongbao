package com.liubusi.shangrongbao.core.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liubusi.shangrongbao.core.pojo.dto.ExcelDictDTO;
import com.liubusi.shangrongbao.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author liubusi
 * @since 2022-05-21
 */
public interface DictMapper extends BaseMapper<Dict> {

    void insertBatch(List<ExcelDictDTO> list);

}
