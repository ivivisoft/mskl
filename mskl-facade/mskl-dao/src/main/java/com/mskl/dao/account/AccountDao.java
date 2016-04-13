package com.mskl.dao.account;

import com.mskl.dao.base.Dao;
import com.mskl.dao.model.MsklAccount;

import java.io.Serializable;

public interface AccountDao extends Dao<MsklAccount,Serializable>{

    MsklAccount getAccountByUserId(Long userId);
}
