package com.liubusi.shangrongbao.sms.service;

public interface SmsService {

    //发送手机验证码
    boolean send(String mobile, String code);

}
