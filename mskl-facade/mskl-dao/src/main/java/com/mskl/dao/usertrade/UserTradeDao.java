package com.mskl.dao.usertrade;

import com.mskl.dao.base.Dao;
import com.mskl.dao.model.MsklUserTrade;

import java.io.Serializable;

public interface UserTradeDao extends Dao<MsklUserTrade,Serializable>{
    MsklUserTrade getTradeByUserId(Long userId);
}
