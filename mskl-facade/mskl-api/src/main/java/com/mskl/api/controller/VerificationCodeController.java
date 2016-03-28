package com.mskl.api.controller;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.service.smscheckcode.MsklSmsCheckcodeService;
import com.mskl.service.token.TokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/verificationCode")
public class VerificationCodeController {

    @Resource(name = "smscheckcode.msklSmsCheckcodeService")
    private MsklSmsCheckcodeService msklSmsCheckcodeService;

    @Resource(name = "tokenService")
    private TokenService tokenService;

    @RequestMapping("/register/{mobile}")
    public RestServiceResult<String> getRegisterVerificationCode(@PathVariable String mobile) {
        RestServiceResult<String> result = new RestServiceResult<String>();
        if (!checkParam(mobile,result)) {
            return result;
        }
        return msklSmsCheckcodeService.getRegisterCheckcode(mobile);
    }

    @RequestMapping("/getLoginPsw/{mobile}/{token}")
    public RestServiceResult<String> getGetLoginPswVerificationCode(@PathVariable String mobile, @PathVariable String token) {
        RestServiceResult<String> result = new RestServiceResult<String>();
        if (!checkParam(mobile, token, result)) {
            return result;
        }
        return msklSmsCheckcodeService.getGetLoginPswCheckcode(mobile);
    }


    private boolean checkParam(String mobile, RestServiceResult<String> result){
        if (StringUtils.isBlank(mobile)) {
            result.setSuccess(false);
            result.setMessage("手机号码为空!");
            return false;
        }
        return true;
    }

    private boolean checkParam(String mobile, String token, RestServiceResult<String> result) {
        if (StringUtils.isBlank(mobile)) {
            result.setSuccess(false);
            result.setMessage("手机号码为空!");
            return false;
        }

        if (!tokenService.checkToken(token)) {
            result.setSuccess(false);
            result.setMessage("用户token校验失败!");
            return false;
        }

        return true;
    }
}
