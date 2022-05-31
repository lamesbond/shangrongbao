package com.liubusi.shangrongbao.core.service;

import com.liubusi.shangrongbao.core.pojo.entity.LendItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liubusi.shangrongbao.core.pojo.vo.InvestVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 标的出借记录表 服务类
 * </p>
 *
 * @author liubusi
 * @since 2022-05-21
 */
public interface LendItemService extends IService<LendItem> {

    String commitInvest(InvestVO investVO);

    void notify(Map<String, Object> paramMap);

    List<LendItem> selectByLendId(Long lendId, Integer status);
}
