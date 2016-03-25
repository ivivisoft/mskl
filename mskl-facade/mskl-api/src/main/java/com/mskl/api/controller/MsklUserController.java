package com.mskl.api.controller;


import com.mskl.common.dto.LoginDto;
import com.mskl.common.dto.RegisterDto;
import com.mskl.dao.model.MsklSmsCheckcode;
import com.mskl.dao.model.MsklUser;
import com.mskl.service.constant.CheckcodeType;
import com.mskl.service.mskluser.MsklUserService;
import com.mskl.service.smscheckcode.MsklSmsCheckcodeService;
import org.apache.commons.lang.StringUtils;
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

    @Resource(name = "smscheckcode.msklSmsCheckcodeService")
    private MsklSmsCheckcodeService msklSmsCheckcodeService;


    @RequestMapping("/{id}")
    public MsklUser getMsklUser(@PathVariable Long id) {
        if (logger.isInfoEnabled()) {
            logger.info("222222222222");
        }
        return msklUserService.getObjectById(id);
    }

    @RequestMapping("/verificationCode/{mobile}")
    public String getVerificationCode(@PathVariable String mobile) {
        return msklSmsCheckcodeService.getRegisterCheckcode(mobile);
    }

    @RequestMapping("/register")
    public boolean register(@RequestBody RegisterDto registerDto) {
        if (checkRegisterParam(registerDto)) {
            return msklUserService.register(registerDto);
        }
        return false;
    }

    private boolean checkRegisterParam(RegisterDto registerDto) {
        return null != registerDto && StringUtils.isNotBlank(registerDto.getMobile()) && StringUtils.isNotBlank(registerDto.getVerificationCode()) && StringUtils.isNotBlank(registerDto.getPassword());
    }


    @RequestMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        if (checkLoginParam(loginDto)) {
            return msklUserService.login(loginDto);
        }
        return StringUtils.EMPTY;
    }

    private boolean checkLoginParam(LoginDto loginDto) {
        return null != loginDto && StringUtils.isNotBlank(loginDto.getUsername()) && StringUtils.isNotBlank(loginDto.getPassword());
    }

    @RequestMapping("/test")
    public MsklSmsCheckcode go() {
        return msklSmsCheckcodeService.getSmsCheckCodeByMobileAndBizType("15024480545", CheckcodeType.REGISTER.getCode());
    }
}
