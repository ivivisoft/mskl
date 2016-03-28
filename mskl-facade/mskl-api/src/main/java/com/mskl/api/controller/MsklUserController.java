package com.mskl.api.controller;


import com.mskl.common.dto.*;
import com.mskl.common.util.SignUtil;
import com.mskl.dao.model.MsklUser;
import com.mskl.service.mskluser.MsklUserService;
import com.mskl.service.token.TokenService;
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

    @Resource(name = "tokenService")
    private TokenService tokenService;

    @RequestMapping("/{id}")
    public MsklUser getMsklUser(@PathVariable Long id) {
        return msklUserService.getObjectById(id);
    }


    @RequestMapping("/register")
    public RestServiceResult<Boolean> register(@RequestBody RegisterDto registerDto) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("进入注册服务controller类", false);
        if (checkRegisterParam(registerDto)) {
            result.setData(Boolean.FALSE);
            result.setMessage("参数非法!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
            return result;
        }
        return msklUserService.register(registerDto);
    }

    private boolean checkRegisterParam(RegisterDto registerDto) {
        return null == registerDto || StringUtils.isBlank(registerDto.getMobile()) || StringUtils.isBlank(registerDto.getVerificationCode()) || StringUtils.isBlank(registerDto.getPassword());
    }


    @RequestMapping("/login/{time}/{md5str}")
    public RestServiceResult<String> login(@RequestBody LoginDto loginDto, @PathVariable Long time, @PathVariable String md5str) {
        RestServiceResult<String> result = new RestServiceResult<String>("进入登录服务controller类", true);
        if (!StringUtils.equals(md5str, SignUtil.signMethod(loginDto, time))) {
            result.setSuccess(false);
            result.setMessage("方法签名不正确!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
            return result;
        }
        if (checkLoginParam(loginDto)) {
            result.setSuccess(false);
            result.setMessage("用户名或者密码不正确!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
            return result;
        }
        return msklUserService.login(loginDto);
    }

    @RequestMapping("/getLoginPassword/{token}")
    public RestServiceResult<Boolean> getLoginPassword(@RequestBody FindLoginPswDto findLoginPswDto,@PathVariable String token){
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("进入找回密码服务controller类", true);
        result.setData(Boolean.TRUE);
        if(!checkFindPswParam(findLoginPswDto,token,result)){
           return result;
        }
        return msklUserService.findLoginPassword(findLoginPswDto,token);
    }

    private boolean checkFindPswParam(FindLoginPswDto findLoginPswDto, String token, RestServiceResult<Boolean> result) {
        if (StringUtils.isBlank(findLoginPswDto.getMobile())||StringUtils.isBlank(findLoginPswDto.getNewPassword())||StringUtils.isBlank(findLoginPswDto.getVerificationCode())) {
            result.setSuccess(false);
            result.setData(Boolean.FALSE);
            result.setMessage("参数非法!");
            return false;
        }

        if (!tokenService.checkToken(token)) {
            result.setSuccess(false);
            result.setData(Boolean.FALSE);
            result.setMessage("用户token校验失败!");
            return false;
        }
        return true;
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
