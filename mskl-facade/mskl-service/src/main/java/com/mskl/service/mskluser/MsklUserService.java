package com.mskl.service.mskluser;

import com.mskl.common.dto.*;
import com.mskl.common.vo.UserInfoVo;
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

    /**
     * 找回用户密码
     * @param findLoginPswDto
     * @param token
     * @return
     */
    RestServiceResult<Boolean> findLoginPassword(FindLoginPswDto findLoginPswDto, String token);

    /**
     * 添加用户扩展信息
     * @param userExtDto
     * @param token
     * @return
     */
    RestServiceResult<Boolean> addUserExtInfo(UserExtDto userExtDto, String token);

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    RestServiceResult<UserInfoVo> getUserInfo(String token);
}
