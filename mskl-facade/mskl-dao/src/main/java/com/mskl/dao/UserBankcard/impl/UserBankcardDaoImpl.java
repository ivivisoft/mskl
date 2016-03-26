package com.mskl.dao.UserBankcard.impl;

import com.mskl.dao.UserBankcard.UserBankcardDao;
import com.mskl.dao.base.impl.MsklBaseDao;
import com.mskl.dao.model.MsklUserBankcard;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository(value = "userBankcard.userBankcardDao")
public class UserBankcardDaoImpl extends MsklBaseDao<MsklUserBankcard,Serializable> implements UserBankcardDao{

    @Override
    public String getIbatisSqlMapNamespace() {
        return ".MsklUserBankcardMapper";
    }

    public List<MsklUserBankcard> getBankcardByUserId(long userId) {
        return queryForList("getBankcardByUserId",userId);
    }
}
