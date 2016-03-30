package com.mskl.dao.amount.impl;

import com.mskl.dao.amount.AmountDao;
import com.mskl.dao.base.impl.MsklBaseDao;
import com.mskl.dao.model.MsklAccount;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository(value = "amount.amountDao")
public class AmountDaoImpl extends MsklBaseDao<MsklAccount,Serializable> implements AmountDao {

    @Override
    public String getIbatisSqlMapNamespace() {
        return ".MsklAccountMapper";
    }

    public MsklAccount getAmountByUserId(Long userId) {

        return (MsklAccount) selectOneObject("getAmountByUserId",userId);
    }
}
