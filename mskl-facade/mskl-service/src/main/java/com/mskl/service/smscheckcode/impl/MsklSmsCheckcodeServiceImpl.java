package com.mskl.service.smscheckcode.impl;

import com.mskl.common.constant.RedisKeyConstant;
import com.mskl.common.util.VerifyCodeUtil;
import com.mskl.dao.model.MsklSmsCheckcode;
import com.mskl.dao.smscheckcode.MsklSmsCheckcodeDao;
import com.mskl.service.base.impl.BaseServiceImpl;
import com.mskl.service.constant.CheckcodeType;
import com.mskl.service.redis.RedisClient;
import com.mskl.service.sms.SmsClient;
import com.mskl.service.smscheckcode.MsklSmsCheckcodeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service(value = "smscheckcode.msklSmsCheckcodeService")
public class MsklSmsCheckcodeServiceImpl  extends BaseServiceImpl<MsklSmsCheckcode,String> implements MsklSmsCheckcodeService {

    private Log logger = LogFactory.getLog(MsklSmsCheckcodeServiceImpl.class);

    private MsklSmsCheckcodeDao msklSmsCheckcodeDao;

    @Resource(name = "smsCheckcode.msklSmsCheckcodeDao")
    public void setMsklSmsCheckcodeDao(MsklSmsCheckcodeDao msklSmsCheckcodeDao) {
        this.msklSmsCheckcodeDao = msklSmsCheckcodeDao;
        super.setBaseDaoImpl(msklSmsCheckcodeDao);
    }

    @Resource
    private RedisClient redisClient;

    @Resource
    private SmsClient smsClient;

    public String getRegisterCheckcode(String mobile) {
        //保存手机验证码
        MsklSmsCheckcode msklSmsCheckcode = new MsklSmsCheckcode();
        msklSmsCheckcode.setMobile(mobile);
        String checkCode = VerifyCodeUtil.verifyCode(6);
        msklSmsCheckcode.setCheckCode(checkCode);
        msklSmsCheckcode.setCheckcodeSetDatetime(new Date());
        msklSmsCheckcode.setSmsBizType(CheckcodeType.REGISTER.getCode());
        saveObject(msklSmsCheckcode);
        //保存redis
        String redisKey = RedisKeyConstant.REGISTORCHECKCODEPRE+mobile;
        redisClient.set(redisKey,checkCode);
        //发送验证码
        smsClient.sendSMS(mobile,checkCode);
        if (logger.isInfoEnabled()){
            logger.info("为手机号:"+mobile+",生成手机注册验证码:"+checkCode);
        }
        return checkCode;
    }

    public MsklSmsCheckcode getSmsCheckCodeByMobileAndBizType(String mobile, String smsBizType) {
        return msklSmsCheckcodeDao.selectByMobileAndSmsBizType(mobile,smsBizType);
    }
}
