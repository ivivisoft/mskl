package com.mskl.service.amount.impl;

import com.mskl.dao.amount.AmountDao;
import com.mskl.dao.model.MsklAccount;
import com.mskl.service.amount.AmountService;
import com.mskl.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

@Service(value = "amount.amountService")
public class AmountServiceImpl extends BaseServiceImpl<MsklAccount,Serializable> implements AmountService{

    @Resource(name = "amount.amountDao")
    private AmountDao amountDao;

    public void setAmountDao(AmountDao amountDao) {
        this.amountDao = amountDao;
        super.setBaseDaoImpl(amountDao);
    }

    public MsklAccount getAmountByUserId(Long userId) {
        return amountDao.getAmountByUserId(userId);
    }
}
