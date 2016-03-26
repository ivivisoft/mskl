package com.mskl.dao.usertrade.impl;

import com.mskl.dao.base.impl.MsklBaseDao;
import com.mskl.dao.model.MsklUserTrade;
import com.mskl.dao.usertrade.UserTradeDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository(value = "userTrade.userTradeDao")
public class UserTradeDaoImpl extends MsklBaseDao<MsklUserTrade,Serializable> implements UserTradeDao{
    @Override
    public String getIbatisSqlMapNamespace() {
        return ".MsklUserTradeMapper";
    }

    public MsklUserTrade getTradeByUserId(Long userId) {
        return (MsklUserTrade) selectOneObject("getTradeByUserId",userId);
    }
}
