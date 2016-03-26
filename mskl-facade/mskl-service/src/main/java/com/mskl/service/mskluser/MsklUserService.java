package com.mskl.service.mskluser;

import com.mskl.common.dto.LoginDto;
import com.mskl.common.dto.ModifyPasswordDto;
import com.mskl.common.dto.RegisterDto;
import com.mskl.common.dto.RestServiceResult;
import com.mskl.dao.model.MsklUser;
import com.mskl.service.base.BaseService;

import java.io.Serializable;


public interface MsklUserService extends BaseService<MsklUser,Serializable>{

    /**
     * 用户注册
     * @param registerDto
     * @return
     */
    RestServiceResult<Boolean> register(RegisterDto registerDto);

    /**
     * 用户登录
     * @param loginDto
     * @return
     */
    RestServiceResult<String> login(LoginDto loginDto);

    /**
     * 修改密码
     * @param modifyPasswordDto
     * @return
     */
    RestServiceResult<Boolean> modifyPassword(ModifyPasswordDto modifyPasswordDto);
}
