package com.mskl.service.amount;

import com.mskl.dao.model.MsklAccount;
import com.mskl.service.base.BaseService;

import java.io.Serializable;

public interface AmountService extends BaseService<MsklAccount,Serializable>{

    /**
     * 通过userId获取账户信息
     * @param userId
     * @return
     */
    public MsklAccount getAmountByUserId(Long userId);
}
