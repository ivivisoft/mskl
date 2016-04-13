package com.mskl.service.account.impl;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.util.TokenUtil;
import com.mskl.dao.account.AccountDao;
import com.mskl.dao.model.MsklAccount;
import com.mskl.service.account.AccountService;
import com.mskl.service.base.impl.BaseServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

@Service(value = "account.accountService")
public class AccountServiceImpl extends BaseServiceImpl<MsklAccount, Serializable> implements AccountService {
    private Log logger = LogFactory.getLog(AccountServiceImpl.class);

    private AccountDao accountDao;

    @Resource(name = "account.accountDao")
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
        super.setBaseDaoImpl(accountDao);
    }

    public MsklAccount getAccountByUserId(Long userId) {
        return accountDao.getAccountByUserId(userId);
    }

    public RestServiceResult<MsklAccount> getAccountInfo(String token) {
        RestServiceResult<MsklAccount> result = new RestServiceResult<MsklAccount>("查询用户账户信息服务", false);
        Long userId = TokenUtil.getUserIdFromToken(token);
        try {
            MsklAccount account = accountDao.getAccountByUserId(userId);
            result.setSuccess(true);
            result.setData(account);
            return result;
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("查询用户账户信息相关数据库失败!");
            }
            result.setMessage("查询用户账户信息相关数据库失败!");
            return result;
        }
    }
}
