package com.mskl.dao.mskluser;

import com.mskl.dao.base.Dao;
import com.mskl.dao.model.MsklUser;

import java.io.Serializable;

public interface MsklUserDao extends Dao<MsklUser, Serializable> {
    /**
     * 通过用户名获取用户信息
     * @param username 用户名可以是手机号也可以是邮箱
     * @return
     */
    MsklUser selectMsklUserByMobileOrEmail(String username);

    /**
     * 增加登录次数和修改最后登录时间
     * @param username
     * @return
     */
    int increaseLoginCountAndChangeLastLoginTime(String username);

}
