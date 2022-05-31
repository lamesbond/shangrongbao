package com.liubusi.shangrongbao.sms.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SmsPropertiesUtils implements InitializingBean {

    @Value("${aliyun.sms.regionId}")
    private String regionId;

    @Value("${aliyun.sms.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.sms.secret}")
    private String secret;

    @Value("${edisim.AppCode}")
    private String edisimAppCode;

    public static String REGION_Id;
    public static String ACCESS_KEY_ID;
    public static String SECRECT;
    public static String EDISIMAPPCODE;

    @Override
    public void afterPropertiesSet() throws Exception {
        REGION_Id=regionId;
        ACCESS_KEY_ID=accessKeyId;
        SECRECT=secret;
        EDISIMAPPCODE=edisimAppCode;
    }
}
