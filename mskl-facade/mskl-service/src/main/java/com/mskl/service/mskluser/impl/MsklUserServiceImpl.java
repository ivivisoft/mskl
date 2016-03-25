package com.mskl.service.mskluser.impl;

import com.mskl.common.constant.RedisKeyConstant;
import com.mskl.common.dto.LoginDto;
import com.mskl.common.dto.RegisterDto;
import com.mskl.common.util.MD5Util;
import com.mskl.dao.model.MsklSmsCheckcode;
import com.mskl.dao.model.MsklUser;
import com.mskl.dao.model.MsklUserLoginLog;
import com.mskl.dao.mskluser.MsklUserDao;
import com.mskl.service.base.impl.BaseServiceImpl;
import com.mskl.service.constant.CheckcodeType;
import com.mskl.service.mskluser.MsklUserService;
import com.mskl.service.mskluserloginlog.MsklUserLoginLogService;
import com.mskl.service.redis.RedisClient;
import com.mskl.service.smscheckcode.MsklSmsCheckcodeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;


@Service(value = "mskluser.msklUserService")
public class MsklUserServiceImpl extends BaseServiceImpl<MsklUser, String> implements MsklUserService {

    private MsklUserDao msklUserDao;

    @Resource(name = "mskluser.msklUserDao")
    public void setMsklUserDao(MsklUserDao msklUserDao) {
        this.msklUserDao = msklUserDao;
        super.setBaseDaoImpl(msklUserDao);
    }

    @Resource
    private RedisClient redisClient;


    @Resource(name = "smscheckcode.msklSmsCheckcodeService")
    private MsklSmsCheckcodeService msklSmsCheckcodeService;

    @Resource(name = "mskluserloginlog.msklUserLoginLogService")
    private MsklUserLoginLogService msklUserLoginLogService;

    public boolean register(RegisterDto registerDto) {
        if (checkSmsCode(registerDto.getMobile(), registerDto.getVerificationCode())) {
            MsklUser msklUser = new MsklUser();
            msklUser.setMobile(registerDto.getMobile());
            msklUser.setUserPwd(MD5Util.encode(registerDto.getPassword()));
            msklUser.setRegisterDatetime(new Date());
            msklUser.setUserInit("2");
            msklUser.setUserStatus("1");
            msklUser.setErrorCount(0);
            msklUser.setLoginCount(0);
            if (StringUtils.isNotBlank(registerDto.getInvitationCode())) {
                msklUser.setRefUserId(Long.parseLong(registerDto.getInvitationCode()));
            }
            return saveObject(msklUser) > 0;
        }
        return false;
    }

    public String login(LoginDto loginDto) {
        MsklUser msklUser = msklUserDao.selectMsklUserByMobileOrEmail(loginDto.getUsername());
        if (null != msklUser) {
            String passwd = MD5Util.encode(loginDto.getPassword());
            if ((StringUtils.equals(msklUser.getMobile(), loginDto.getUsername()) || StringUtils.equals(msklUser.getEmail(), loginDto.getUsername())) && StringUtils.equals(msklUser.getUserPwd(), passwd)) {
                //产生登录token并将她存入redis
                String loginKey = RedisKeyConstant.LOGINPRE + loginDto.getUsername();
                String token = UUID.randomUUID().toString().replace("-", "");
                redisClient.set(loginKey, token);
                //更新登录次数
                msklUserDao.increaseLoginCountAndChangeLastLoginTime(loginDto.getUsername());
                //添加登录流水
                MsklUserLoginLog msklUserLoginLog = new MsklUserLoginLog();
                msklUserLoginLog.setIsSuccess("1");
                msklUserLoginLog.setLoginDatetime(new Date());
                msklUserLoginLog.setUserId(msklUser.getUserId());
                msklUserLoginLogService.saveObject(msklUserLoginLog);
                return token;
            } else {
                return "用户名或者密码错误!";
            }
        }
        return StringUtils.EMPTY;
    }

    //检查注册验证码
    private boolean checkSmsCode(String mobile, String verificationCode) {
        String code = redisClient.get(RedisKeyConstant.REGISTORCHECKCODEPRE + mobile);
        if (StringUtils.isNotBlank(code)) {
            return StringUtils.equals(code, verificationCode);
        } else {
            MsklSmsCheckcode msklSmsCheckcode = msklSmsCheckcodeService.getSmsCheckCodeByMobileAndBizType(mobile, CheckcodeType.REGISTER.getCode());
            return null != msklSmsCheckcode && StringUtils.isNotBlank(msklSmsCheckcode.getCheckCode()) && StringUtils.equals(msklSmsCheckcode.getCheckCode(), verificationCode);
        }
    }
}
