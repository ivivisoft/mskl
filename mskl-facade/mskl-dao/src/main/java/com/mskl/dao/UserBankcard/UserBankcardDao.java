package com.mskl.dao.UserBankcard;

import com.mskl.dao.base.Dao;
import com.mskl.dao.base.impl.BaseDao;
import com.mskl.dao.model.MsklFeedback;
import com.mskl.dao.model.MsklUserBankcard;

import java.io.Serializable;
import java.util.List;

public interface UserBankcardDao extends Dao<MsklUserBankcard,Serializable> {

    List<MsklUserBankcard> getBankcardByUserId(long userId);
}
