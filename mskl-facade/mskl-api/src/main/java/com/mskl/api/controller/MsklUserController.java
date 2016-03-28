package com.mskl.api.controller;


import com.mskl.common.dto.*;
import com.mskl.dao.model.MsklUser;
import com.mskl.service.mskluser.MsklUserService;
import com.mskl.service.verification.VerificationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/mskluser")
public class MsklUserController {

    private Log logger = LogFactory.getLog(MsklUserController.class);

    @Resource(name = "mskluser.msklUserService")
    private MsklUserService msklUserService;

    @Resource(name = "verificationService")
    private VerificationService verificationService;

    @RequestMapping("/{id}")
    public MsklUser getMsklUser(@PathVariable Long id) {
        return msklUserService.getObjectById(id);
    }


    @RequestMapping("/register")
    public RestServiceResult<Boolean> register(@RequestBody RegisterDto registerDto) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("进入注册服务controller类", false);
        if (!verificationService.verification(registerDto, result)) {
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        return msklUserService.register(registerDto);
    }


    @RequestMapping("/login/{time}/{md5str}")
    public RestServiceResult<String> login(@RequestBody LoginDto loginDto, @PathVariable Long time, @PathVariable String md5str) {
        RestServiceResult<String> result = new RestServiceResult<String>("进入登录服务controller类", true);
        if (!verificationService.verification(loginDto, time, md5str, result)) {
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        return msklUserService.login(loginDto);
    }

    @RequestMapping("/getLoginPassword/{token}")
    public RestServiceResult<Boolean> getLoginPassword(@RequestBody FindLoginPswDto findLoginPswDto, @PathVariable String token) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("进入找回密码服务controller类", true);
        if (!verificationService.verification(findLoginPswDto, token, result)) {
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        return msklUserService.findLoginPassword(findLoginPswDto, token);
    }


    @RequestMapping("/modifyPassword")
    public RestServiceResult<Boolean> modifyPassword(@RequestBody ModifyPasswordDto modifyPasswordDto) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>();
        if (!verificationService.verification(modifyPasswordDto, result)) {
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        return msklUserService.modifyPassword(modifyPasswordDto);
    }

}
