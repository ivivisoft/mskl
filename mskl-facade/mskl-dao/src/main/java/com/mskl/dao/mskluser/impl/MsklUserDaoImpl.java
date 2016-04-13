package com.mskl.dao.mskluser.impl;


import com.mskl.dao.base.impl.MsklBaseDao;
import com.mskl.dao.model.MsklUser;
import com.mskl.dao.mskluser.MsklUserDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository(value = "mskluser.msklUserDao")
public class MsklUserDaoImpl extends MsklBaseDao<MsklUser, Serializable> implements MsklUserDao {
    @Override
    public String getIbatisSqlMapNamespace() {
        return ".MsklUserMapper";
    }

    public MsklUser selectMsklUserByMobileOrEmail(String username) {
        return (MsklUser) selectOneObject("selectMsklUserByMobileOrEmail", username);
    }

    public int increaseLoginCountAndChangeLastLoginTime(String username) {
        return updateByStatementName("increaseLoginCountAndChangeLastLoginTime",username);
    }

    public void insertSelectiveBackId(MsklUser msklUser) {
        super.getSqlSession().insert(".MsklUserMapper.insertSelectiveBackId", msklUser);
    }
}

