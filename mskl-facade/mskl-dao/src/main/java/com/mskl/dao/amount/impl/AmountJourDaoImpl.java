package com.mskl.dao.amount.impl;


import com.mskl.dao.amount.AmountDao;
import com.mskl.dao.amount.AmountJourDao;
import com.mskl.dao.base.impl.MsklBaseDao;
import com.mskl.dao.model.MsklAccountJour;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository(value = "amountJour.amountJourDao")
public class AmountJourDaoImpl extends MsklBaseDao<MsklAccountJour,Serializable> implements AmountJourDao{

    @Override
    public String getIbatisSqlMapNamespace() {
        return ".MsklAccountJourMapper";
    }
}
