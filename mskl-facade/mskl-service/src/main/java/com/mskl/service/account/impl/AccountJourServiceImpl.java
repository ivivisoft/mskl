package com.mskl.service.account.impl;

import com.mskl.dao.account.AccountJourDao;
import com.mskl.dao.model.MsklAccountJour;
import com.mskl.service.account.AccountJourService;
import com.mskl.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

@Service(value = "accountJour.accountJourService")
public class AccountJourServiceImpl extends BaseServiceImpl<MsklAccountJour,Serializable> implements AccountJourService {

    private AccountJourDao accountJourDao;

    @Resource(name = "accountJour.accountJourDao")
    public void setAccountJourDao(AccountJourDao accountJourDao) {
        this.accountJourDao = accountJourDao;
        super.setBaseDaoImpl(accountJourDao);
    }
}
