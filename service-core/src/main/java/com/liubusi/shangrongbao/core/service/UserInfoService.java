package com.liubusi.shangrongbao.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liubusi.shangrongbao.core.pojo.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liubusi.shangrongbao.core.pojo.query.UserInfoQuery;
import com.liubusi.shangrongbao.core.pojo.vo.LoginVO;
import com.liubusi.shangrongbao.core.pojo.vo.RegisterVO;
import com.liubusi.shangrongbao.core.pojo.vo.UserInfoVO;

/**
 * <p>
 * 用户基本信息 服务类
 * </p>
 *
 * @author liubusi
 * @since 2022-05-21
 */
public interface UserInfoService extends IService<UserInfo> {

    void register(RegisterVO registerVO);

    UserInfoVO login(LoginVO loginVO, String ip);

    IPage<UserInfo> listPage(Page<UserInfo> pageParam, UserInfoQuery userInfoQuery);

    void lock(Long id, Integer status);

    boolean checkMobile(String mobile);
}
