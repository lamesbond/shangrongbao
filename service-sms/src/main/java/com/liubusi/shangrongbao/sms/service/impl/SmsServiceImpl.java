package com.liubusi.shangrongbao.sms.service.impl;

import com.liubusi.shangrongbao.sms.service.SmsService;
import com.liubusi.shangrongbao.sms.utils.SmsPropertiesUtils;
import com.liubusi.shangrongbao.sms.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;


@Service
public class SmsServiceImpl implements SmsService {
    @Override
    public boolean send(String mobile, String code) {
        //判断手机号是否为空
        if(StringUtils.isEmpty(mobile)) {
            return false;
        }
        //整合阿里云短信服务，从阿里云市场“北京一砂信息技术有限公司”买的
        //设置相关参数
        String host = "https://edisim.market.alicloudapi.com";
        String path = "/comms/sms/sendmsg";
        String method = "POST";
        String appcode = SmsPropertiesUtils.EDISIMAPPCODE;
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("callbackUrl", "http://test.dev.esandcloud.com");
        bodys.put("channel", "0");
        bodys.put("mobile", mobile);
        bodys.put("templateID", "0000000");
        bodys.put("templateParamSet", code+",2");

        boolean isSuccess = false;
        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println("anfitnh======"+response.toString());
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode == 200) {
                isSuccess = true;
            }
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}

