package com.mskl.service.usercashout.impl;

import com.mskl.dao.model.MsklUserCashoutApplication;
import com.mskl.dao.usercashout.UserCashoutDao;
import com.mskl.service.base.BaseService;
import com.mskl.service.base.impl.BaseServiceImpl;
import com.mskl.service.usercashout.UserCashoutService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

@Service(value = "userCashout.userCashoutService")
public class UserCashoutServiceImpl extends BaseServiceImpl<MsklUserCashoutApplication,Serializable> implements UserCashoutService{

    private UserCashoutDao userCashoutDao;

    @Resource(name = "userCashout.userCashoutDao")
    public void setUserCashoutDao(UserCashoutDao userCashoutDao) {
        this.userCashoutDao = userCashoutDao;
        super.setBaseDaoImpl(userCashoutDao);
    }
}
