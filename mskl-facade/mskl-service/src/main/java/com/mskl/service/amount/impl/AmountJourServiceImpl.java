package com.mskl.service.amount.impl;

import com.mskl.dao.amount.AmountJourDao;
import com.mskl.dao.model.MsklAccountJour;
import com.mskl.service.amount.AmountJourService;
import com.mskl.service.amount.AmountService;
import com.mskl.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

@Service(value = "amountJour.amountJourService")
public class AmountJourServiceImpl extends BaseServiceImpl<MsklAccountJour,Serializable> implements AmountJourService{

    private AmountJourDao amountJourDao;

    @Resource(name = "amountJour.amountJourDao")
    public void setAmountJourDao(AmountJourDao amountJourDao) {
        this.amountJourDao = amountJourDao;
        super.setBaseDaoImpl(amountJourDao);
    }
}
