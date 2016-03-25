package com.mskl.service.mskluser;

import com.mskl.common.dto.LoginDto;
import com.mskl.common.dto.RegisterDto;
import com.mskl.dao.model.MsklUser;
import com.mskl.service.base.BaseService;

import java.io.Serializable;


public interface MsklUserService extends BaseService<MsklUser,Serializable>{

    /**
     * 用户注册
     * @param registerDto
     * @return
     */
    boolean register(RegisterDto registerDto);

    /**
     * 用户登录
     * @param loginDto
     * @return
     */
    String login(LoginDto loginDto);

}
