package com.mskl.api.controller;


import com.mskl.common.dto.LoginDto;
import com.mskl.common.dto.ModifyPasswordDto;
import com.mskl.common.dto.RegisterDto;
import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.util.SignUtil;
import com.mskl.dao.model.MsklUser;
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
        return msklUserService.getObjectById(id);
    }

    @RequestMapping("/verificationCode/{mobile}")
    public RestServiceResult<String> getVerificationCode(@PathVariable String mobile) {
        RestServiceResult<String> result = new RestServiceResult<String>();
        if (checkParam(mobile)) {
            result.setSuccess(false);
            result.setMessage("注册手机号码为空!");
            return result;
        }
        return msklSmsCheckcodeService.getRegisterCheckcode(mobile);
    }

    private boolean checkParam(String mobile) {
        return StringUtils.isBlank(mobile);
    }

    @RequestMapping("/register")
    public RestServiceResult<Boolean> register(@RequestBody RegisterDto registerDto) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>();
        if (checkRegisterParam(registerDto)) {
            result.setSuccess(false);
            result.setData(Boolean.FALSE);
            result.setMessage("参数非法!");
        }
        return msklUserService.register(registerDto);
    }

    private boolean checkRegisterParam(RegisterDto registerDto) {
        return null == registerDto || StringUtils.isBlank(registerDto.getMobile()) || StringUtils.isBlank(registerDto.getVerificationCode()) || StringUtils.isBlank(registerDto.getPassword());
    }


    @RequestMapping("/login/{time}/{md5str}")
    public RestServiceResult<String> login(@RequestBody LoginDto loginDto,@PathVariable Long time,@PathVariable String md5str) {
        RestServiceResult<String> result = new RestServiceResult<String>();
        result.setSuccess(true);
        if(!StringUtils.equals(md5str, SignUtil.signMethod(loginDto,time))){
            result.setSuccess(false);
            result.setMessage("方法签名不正确!");
            return result;
        }
        if (checkLoginParam(loginDto)) {
            result.setSuccess(false);
            result.setMessage("用户名或者密码不正确!");
            return result;
        }
        return msklUserService.login(loginDto);
    }


    private boolean checkLoginParam(LoginDto loginDto) {
        return null == loginDto || StringUtils.isBlank(loginDto.getUsername()) || StringUtils.isBlank(loginDto.getPassword());
    }

    @RequestMapping("/modifyPassword")
    public RestServiceResult<Boolean> modifyPassword(@RequestBody ModifyPasswordDto modifyPasswordDto) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>();
        if (checkModifyPasswordParam(modifyPasswordDto)) {
            result.setSuccess(false);
            result.setData(Boolean.FALSE);
            result.setMessage("参数不正确！");
            return result;
        }
        return msklUserService.modifyPassword(modifyPasswordDto);
    }

    private boolean checkModifyPasswordParam(ModifyPasswordDto modifyPasswordDto) {
        return null == modifyPasswordDto || StringUtils.isBlank(modifyPasswordDto.getUserName()) || StringUtils.isBlank(modifyPasswordDto.getPassword()) || StringUtils.isBlank(modifyPasswordDto.getNewPassword()) || StringUtils.isBlank(modifyPasswordDto.getUserPwdStrength());
    }

}
