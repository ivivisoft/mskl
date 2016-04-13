package com.mskl.dao.account.impl;

import com.mskl.dao.account.AccountDao;
import com.mskl.dao.base.impl.MsklBaseDao;
import com.mskl.dao.model.MsklAccount;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository(value = "account.accountDao")
public class AccountDaoImpl extends MsklBaseDao<MsklAccount,Serializable> implements AccountDao {

    @Override
    public String getIbatisSqlMapNamespace() {
        return ".MsklAccountMapper";
    }

    public MsklAccount getAccountByUserId(Long userId) {

        return (MsklAccount) selectOneObject("getAccountByUserId",userId);
    }
}
