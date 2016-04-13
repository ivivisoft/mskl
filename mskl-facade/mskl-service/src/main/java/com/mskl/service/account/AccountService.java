package com.mskl.service.account;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.dao.model.MsklAccount;
import com.mskl.service.base.BaseService;

import java.io.Serializable;

public interface AccountService extends BaseService<MsklAccount,Serializable>{

    /**
     * 通过userId获取账户信息
     * @param userId
     * @return
     */
    MsklAccount getAccountByUserId(Long userId);

    /**
     * 通过token获取用户账户信息
     * @param token
     * @return
     */
    RestServiceResult<MsklAccount> getAccountInfo(String token);
}
