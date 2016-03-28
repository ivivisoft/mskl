package com.mskl.dao.smscheckcode;

import com.mskl.common.constant.CheckcodeType;
import com.mskl.dao.base.Dao;
import com.mskl.dao.model.MsklSmsCheckcode;

import java.io.Serializable;


public interface MsklSmsCheckcodeDao extends Dao<MsklSmsCheckcode,Serializable> {

    /**
     * 获取注册时手机验证码
     * @param mobile 手机号码
     * @param smsBizType 业务类型
     * @return
     */
    MsklSmsCheckcode selectByMobileAndSmsBizType(String mobile, CheckcodeType smsBizType);
}
