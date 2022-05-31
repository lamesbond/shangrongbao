package com.liubusi.shangrongbao.core.service;

import com.liubusi.shangrongbao.core.pojo.entity.BorrowInfo;
import com.liubusi.shangrongbao.core.pojo.entity.Lend;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liubusi.shangrongbao.core.pojo.vo.BorrowInfoApprovalVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 标的准备表 服务类
 * </p>
 *
 * @author liubusi
 * @since 2022-05-21
 */
public interface LendService extends IService<Lend> {

    void createLend(BorrowInfoApprovalVO borrowInfoApprovalVO, BorrowInfo borrowInfo);

    List<Lend> selectList();

    Map<String, Object> getLendDetail(Long id);

    BigDecimal getInterestCount(BigDecimal invest, BigDecimal yearRate, Integer totalmonth, Integer returnMethod);

    void makeLoan(Long id);

}
