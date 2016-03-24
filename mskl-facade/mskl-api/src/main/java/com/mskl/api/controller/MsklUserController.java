package com.mskl.api.controller;


import com.mskl.dao.model.MsklUser;
import com.mskl.service.mskluser.MsklUserService;
import com.mskl.service.redis.RedisClient;
import com.mskl.service.sms.SmsClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
@RequestMapping("/mskluser")
public class MsklUserController{

    private Log logger = LogFactory.getLog(MsklUserController.class);

    @Resource(name = "mskluser.msklUserService")
    private MsklUserService msklUserService;

    @Resource
    private RedisClient redisClient;

    @Resource
    private SmsClient smsClient;

    @RequestMapping("/{id}")
    public  MsklUser getMsklUser(@PathVariable Long id){
        if(logger.isInfoEnabled()){
            logger.info("222222222222");
        }
        return msklUserService.getObjectById(id);
    }

    @RequestMapping("/verificationCode/{mobile}")
    public boolean getVerificationCode(@PathVariable String mobile){
        //String token = UUID.randomUUID().toString().replaceAll("-", "");
       // redisClient.set(mobile,token);
        smsClient.sendSMS(mobile,"789076");
        return false;
    }

}
