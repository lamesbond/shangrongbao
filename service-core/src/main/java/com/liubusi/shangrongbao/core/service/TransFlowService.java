package com.liubusi.shangrongbao.core.service;

import com.liubusi.shangrongbao.core.pojo.bo.TransFlowBO;
import com.liubusi.shangrongbao.core.pojo.entity.TransFlow;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 交易流水表 服务类
 * </p>
 *
 * @author liubusi
 * @since 2022-05-21
 */
public interface TransFlowService extends IService<TransFlow> {
    void saveTransFlow(TransFlowBO transFlowBO);

    boolean isSaveTransFlow(String agentBillNo);

}
