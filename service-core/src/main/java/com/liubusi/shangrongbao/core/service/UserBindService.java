package com.liubusi.shangrongbao.core.service;

import com.liubusi.shangrongbao.core.pojo.entity.UserBind;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liubusi.shangrongbao.core.pojo.vo.UserBindVO;

import java.util.Map;

/**
 * <p>
 * 用户绑定表 服务类
 * </p>
 *
 * @author liubusi
 * @since 2022-05-21
 */
public interface UserBindService extends IService<UserBind> {

    String commitBindUser(UserBindVO userBindVO, Long userId);

    void notify(Map<String, Object> paramMap);

    String getBindCodeByUserId(Long userId);
}
