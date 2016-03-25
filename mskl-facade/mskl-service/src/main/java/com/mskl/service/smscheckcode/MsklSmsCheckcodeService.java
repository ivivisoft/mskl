package com.mskl.service.smscheckcode;

import com.mskl.dao.model.MsklSmsCheckcode;
import com.mskl.service.base.BaseService;

import java.io.Serializable;

public interface MsklSmsCheckcodeService extends BaseService<MsklSmsCheckcode, Serializable> {

  /**
   * 获取注册时的手机验证码
   * @param mobile
   * @return
     */
  String getRegisterCheckcode(String mobile);

  /**
   * 通过手机号码和sms的业务类型获取验证码
   * @param mobile 手机号
   * @param smsBizType sms业务类型
   * @return
     */
  MsklSmsCheckcode getSmsCheckCodeByMobileAndBizType(String mobile,String smsBizType);
}
