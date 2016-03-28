package com.mskl.service.sms;


import com.mskl.common.constant.CheckcodeType;
import com.mskl.service.otherserviceresult.ServiceResult;

public interface SmsClient {
    /**
     * 发送手机验证码
     * @param mobile 手机号
     * @param code 验证码
     * @param checkcodeType 验证码类型
     * @return
     */
    ServiceResult sendVerificationCode(String mobile, String code, CheckcodeType checkcodeType);
}
