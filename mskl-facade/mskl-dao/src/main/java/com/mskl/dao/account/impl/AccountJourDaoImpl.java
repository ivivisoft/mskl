package com.mskl.dao.account.impl;


import com.mskl.dao.account.AccountJourDao;
import com.mskl.dao.base.impl.MsklBaseDao;
import com.mskl.dao.model.MsklAccountJour;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository(value = "accountJour.accountJourDao")
public class AccountJourDaoImpl extends MsklBaseDao<MsklAccountJour,Serializable> implements AccountJourDao {

    @Override
    public String getIbatisSqlMapNamespace() {
        return ".MsklAccountJourMapper";
    }
}
