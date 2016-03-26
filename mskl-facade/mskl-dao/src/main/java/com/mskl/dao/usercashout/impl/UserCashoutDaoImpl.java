package com.mskl.dao.usercashout.impl;

import com.mskl.dao.base.impl.BaseDao;
import com.mskl.dao.base.impl.MsklBaseDao;
import com.mskl.dao.model.MsklUserCashoutApplication;
import com.mskl.dao.usercashout.UserCashoutDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository(value = "userCashout.userCashoutDao")
public class UserCashoutDaoImpl extends MsklBaseDao<MsklUserCashoutApplication,Serializable> implements UserCashoutDao{

    @Override
    public String getIbatisSqlMapNamespace() {
        return ".MsklUserCashoutApplicationMapper";
    }
}
