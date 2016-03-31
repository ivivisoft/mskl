package com.mskl.service.smscheckcode.impl;

import com.mskl.common.constant.RedisKeyConstant;
import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.util.VerifyCodeUtil;
import com.mskl.dao.model.MsklSmsCheckcode;
import com.mskl.dao.smscheckcode.MsklSmsCheckcodeDao;
import com.mskl.service.base.impl.BaseServiceImpl;
import com.mskl.common.constant.CheckcodeType;
import com.mskl.service.otherserviceresult.ServiceResult;
import com.mskl.service.redis.RedisClient;
import com.mskl.service.sms.SmsClient;
import com.mskl.service.smscheckcode.MsklSmsCheckcodeService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service(value = "smscheckcode.msklSmsCheckcodeService")
public class MsklSmsCheckcodeServiceImpl extends BaseServiceImpl<MsklSmsCheckcode, String> implements MsklSmsCheckcodeService {

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

    public RestServiceResult<String> getRegisterCheckcode(String mobile) {
        RestServiceResult<String> result = new RestServiceResult<String>("获取手机注册验证码服务", false);
        sendVerificationCode(mobile, CheckcodeType.REGISTER, result);
        if (logger.isInfoEnabled()) {
            logger.info(result.toString());
        }
        return result;
    }

    public boolean checkSmsCode(String mobile, String verificationCode, CheckcodeType checkcodeType) {
        String redisKey = getRedisKey(mobile, checkcodeType);
        String checkCodeInRedis =   redisClient.get(redisKey);
        if (StringUtils.isNotBlank(checkCodeInRedis)) {
            return StringUtils.equals(checkCodeInRedis, verificationCode);
        }
        return false;
    }

    private String getRedisKey(String mobile, CheckcodeType checkcodeType) {
        String redisKey = StringUtils.EMPTY;
        switch (checkcodeType){
            case REGISTER:
                redisKey = RedisKeyConstant.REGISTORCHECKCODEPRE + mobile;
                break;
            case GETLOGINPSW:
                redisKey = RedisKeyConstant.GETLOGINPSWCHECKCODEPRE + mobile;
                break;
        }
        return redisKey;
    }

    public RestServiceResult<String> getGetLoginPswCheckcode(String mobile) {
        RestServiceResult<String> result = new RestServiceResult<String>("获取找回密码手机注册验证码服务", false);
        sendVerificationCode(mobile, CheckcodeType.GETLOGINPSW, result);
        if (logger.isInfoEnabled()) {
            logger.info(result.toString());
        }
        return result;
    }

    /**
     * 发送手机验证码
     * @param mobile 手机号
     * @param checkcodeType 业务类型
     * @param result 处理结果
     */
    private void sendVerificationCode(String mobile, CheckcodeType checkcodeType, RestServiceResult<String> result) {

        //1.获取或者产生验证码
        MsklSmsCheckcode msklSmsCheckcode = getMsklSmsCheckcode(mobile, checkcodeType);

        //2.保存redis
        saveVerifyCodeToRedis(mobile, checkcodeType, msklSmsCheckcode.getCheckCode());

        //3.发送验证码
        ServiceResult serviceResult = smsClient.sendVerificationCode(mobile, msklSmsCheckcode.getCheckCode(),checkcodeType);
        if (!serviceResult.isSuccess()) {
            result.setMessage("发送手机验证码失败!" + serviceResult.getMessage());
            return;
        }
        result.setSuccess(true);
        result.setData(msklSmsCheckcode.getCheckCode());
    }

    /**
     * 获取或者产生验证码
     * @param mobile 手机
     * @param checkcodeType 验证码类型
     * @return
     */
    private MsklSmsCheckcode getMsklSmsCheckcode(String mobile, CheckcodeType checkcodeType) {
        //检查原先有没有为这个手机号的这个业务生成验证码
        MsklSmsCheckcode msklSmsCheckcode = msklSmsCheckcodeDao.selectByMobileAndSmsBizType(mobile, checkcodeType);
        if (null != msklSmsCheckcode) {
            msklSmsCheckcode.setCheckTimes(msklSmsCheckcode.getCheckTimes() + 1);
            msklSmsCheckcode.setLastCheckDatetime(new Date());
            String checkCode = VerifyCodeUtil.verifyCode(6);
            msklSmsCheckcode.setCheckCode(checkCode);
            updateObject(msklSmsCheckcode);
        } else {
            //保存手机验证码
            msklSmsCheckcode = new MsklSmsCheckcode();
            msklSmsCheckcode.setMobile(mobile);
            String checkCode = VerifyCodeUtil.verifyCode(6);
            msklSmsCheckcode.setCheckCode(checkCode);
            msklSmsCheckcode.setCheckcodeSetDatetime(new Date());
            msklSmsCheckcode.setSmsBizType(checkcodeType.getCode());
            msklSmsCheckcode.setCheckTimes(1);
            saveObject(msklSmsCheckcode);
        }
        return msklSmsCheckcode;
    }

    /**
     * 保存验证码到redis中
     * @param mobile 手机号
     * @param checkcodeType 验证码类型
     * @param checkcode 验证码
     */
    private void saveVerifyCodeToRedis(String mobile, CheckcodeType checkcodeType, String  checkcode) {
        redisClient.set(getRedisKey(mobile,checkcodeType), checkcode,1800);
    }

    public MsklSmsCheckcode getSmsCheckCodeByMobileAndBizType(String mobile, CheckcodeType smsBizType) {
        return msklSmsCheckcodeDao.selectByMobileAndSmsBizType(mobile, smsBizType);
    }
}
