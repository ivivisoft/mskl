package com.mskl.service.smscheckcode;

import com.mskl.common.constant.CheckcodeType;
import com.mskl.common.dto.RestServiceResult;
import com.mskl.dao.model.MsklSmsCheckcode;
import com.mskl.service.base.BaseService;

import java.io.Serializable;

public interface MsklSmsCheckcodeService extends BaseService<MsklSmsCheckcode, Serializable> {

  /**
   * 获取注册时的手机验证码
   * @param mobile
   * @return
     */
  RestServiceResult<String> getRegisterCheckcode(String mobile);

  /**
   * 检查手机相应的验证码
   * @param mobile 手机号
   * @param verificationCode 验证码
   * @param checkcodeType 类型
     * @return
     */
  boolean checkSmsCode(String mobile,String verificationCode,CheckcodeType checkcodeType);

  /**
   * 获取找回密码时的手机验证码
   * @param mobile
   * @return
   */
  RestServiceResult<String> getGetLoginPswCheckcode(String mobile);

  /**
   * 通过手机号码和sms的业务类型获取验证码
   * @param mobile 手机号
   * @param smsBizType sms业务类型
   * @return
     */
  MsklSmsCheckcode getSmsCheckCodeByMobileAndBizType(String mobile,CheckcodeType smsBizType);
}
