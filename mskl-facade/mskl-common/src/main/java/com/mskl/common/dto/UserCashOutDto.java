package com.mskl.common.dto;

import java.io.Serializable;

public class UserCashoutDto implements Serializable{

    /**提现金额*/
    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "amount='" + amount + "'";
    }
}