package com.mskl.dao.amount;

import com.mskl.dao.base.Dao;
import com.mskl.dao.model.MsklAccount;

import java.io.Serializable;

public interface AmountDao extends Dao<MsklAccount,Serializable>{

    MsklAccount getAmountByUserId(Long userId);
}
