package com.liubusi.shangrongbao.sms.controller.api;

import com.liubusi.common.exception.Assert;
import com.liubusi.common.result.ResponseEnum;
import com.liubusi.common.result.Result;
import com.liubusi.common.util.RandomUtils;
import com.liubusi.common.util.RegexValidateUtils;
import com.liubusi.shangrongbao.sms.client.CoreUserInfoClient;
import com.liubusi.shangrongbao.sms.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Api(tags = "短信管理")
@RestController
@RequestMapping("/api/sms")
public class ApiSmsController {

    @Autowired
    private SmsService smsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private CoreUserInfoClient coreUserInfoClient;

    //发送手机验证码
    @ApiOperation("获取验证码")
    @GetMapping("send/{mobile}")
    public Result send(@ApiParam(value = "手机号", required = true)
                           @PathVariable String mobile) {
        //从redis获取验证码，如果获取获取到，返回ok
        // key 手机号  value 验证码
        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);
        Assert.isTrue(RegexValidateUtils.checkCellphone(mobile), ResponseEnum.MOBILE_ERROR);

        //手机号是否注册
        boolean result = coreUserInfoClient.checkMobile(mobile);
        System.out.println("result = " + result);
        Assert.isTrue(result == false, ResponseEnum.MOBILE_EXIST_ERROR);

        String code = (String)redisTemplate.opsForValue().get("srb:sms:code:" + mobile);
        if(!StringUtils.isEmpty(code)) {
            return Result.ok();
        }
        //如果从redis获取不到，
        // 生成验证码，
        code = RandomUtils.getSixBitRandom();
        System.out.println("生成的验证码是：" + code);
        //调用service方法，通过整合短信服务进行发送
        boolean isSend = smsService.send(mobile,code);
        //生成验证码放到redis里面，设置有效时间
        if(isSend) {
            redisTemplate.opsForValue().set("srb:sms:code:" + mobile, code,2, TimeUnit.MINUTES);
            return Result.ok();
        } else {
            return Result.error().message("发送短信失败");
        }
    }
}
