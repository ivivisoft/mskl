package com.mskl.api.controller;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.service.smscheckcode.MsklSmsCheckcodeService;
import com.mskl.service.token.TokenService;
import com.mskl.service.verification.VerificationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/verificationCode")
public class VerificationCodeController {
    private Log logger = LogFactory.getLog(VerificationCodeController.class);

    @Resource(name = "smscheckcode.msklSmsCheckcodeService")
    private MsklSmsCheckcodeService msklSmsCheckcodeService;

    @Resource(name = "verificationService")
    private VerificationService verificationService;

    @RequestMapping("/register/{mobile}")
    public RestServiceResult<String> getRegisterVerificationCode(@PathVariable String mobile) {
        RestServiceResult<String> result = new RestServiceResult<String>("进入获取注册服务验证码controller类", false);
        if (!verificationService.verification(mobile, result)) {
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        return msklSmsCheckcodeService.getRegisterCheckcode(mobile);
    }

    @RequestMapping("/getLoginPsw/{mobile}/{token}")
    public RestServiceResult<String> getGetLoginPswVerificationCode(@PathVariable String mobile, @PathVariable String token) {
        RestServiceResult<String> result = new RestServiceResult<String>("进入找回密码服务验证码controller类", false);
        if (!verificationService.verification(mobile, token, result)) {
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        return msklSmsCheckcodeService.getGetLoginPswCheckcode(mobile);
    }

}
