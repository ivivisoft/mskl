package com.mskl.service.mskluser.impl;

import com.mskl.common.constant.CheckcodeType;
import com.mskl.common.constant.RedisKeyConstant;
import com.mskl.common.dto.*;
import com.mskl.common.util.MD5Util;
import com.mskl.common.util.TokenUtil;
import com.mskl.common.vo.UserInfoVo;
import com.mskl.dao.model.MsklAccount;
import com.mskl.dao.model.MsklUser;
import com.mskl.dao.model.MsklUserExt;
import com.mskl.dao.model.MsklUserLoginLog;
import com.mskl.dao.mskluser.MsklUserDao;
import com.mskl.service.account.AccountService;
import com.mskl.service.base.impl.BaseServiceImpl;
import com.mskl.service.msklfile.MsklFileService;
import com.mskl.service.mskluser.MsklUserExtService;
import com.mskl.service.mskluser.MsklUserService;
import com.mskl.service.mskluserloginlog.MsklUserLoginLogService;
import com.mskl.service.otherserviceresult.ServiceResult;
import com.mskl.service.redis.RedisClient;
import com.mskl.service.smscheckcode.MsklSmsCheckcodeService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;


@Service(value = "mskluser.msklUserService")
public class MsklUserServiceImpl extends BaseServiceImpl<MsklUser, String> implements MsklUserService {

    private Log logger = LogFactory.getLog(MsklUserServiceImpl.class);

    private MsklUserDao msklUserDao;

    @Resource(name = "mskluser.msklUserDao")
    public void setMsklUserDao(MsklUserDao msklUserDao) {
        this.msklUserDao = msklUserDao;
        super.setBaseDaoImpl(msklUserDao);
    }

    @Resource
    private RedisClient redisClient;

    @Resource(name = "msklFile.msklFileService")
    private MsklFileService msklFileService;


    @Resource(name = "smscheckcode.msklSmsCheckcodeService")
    private MsklSmsCheckcodeService msklSmsCheckcodeService;

    @Resource(name = "mskluserloginlog.msklUserLoginLogService")
    private MsklUserLoginLogService msklUserLoginLogService;

    @Resource(name = "mskluser.msklUserExtService")
    private MsklUserExtService msklUserExtService;

    @Resource(name = "account.accountService")
    private AccountService accountService;

    @Transactional
    public RestServiceResult<Boolean> register(RegisterDto registerDto) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("用户注册服务", false);
        result.setData(Boolean.FALSE);
        //查看用户是否已经注册了
        MsklUser msklUser = msklUserDao.selectMsklUserByMobileOrEmail(registerDto.getMobile());
        if (null != msklUser) {
            result.setMessage("此用户手机号已经被注册了!");
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        if (!msklSmsCheckcodeService.checkSmsCode(registerDto.getMobile(), registerDto.getVerificationCode(), CheckcodeType.REGISTER)) {
            result.setMessage("注册验证码不正确!");
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        try {
            msklUser = new MsklUser();
            msklUser.setMobile(registerDto.getMobile());
            msklUser.setUserPwd(MD5Util.encode(registerDto.getPassword()));
            msklUser.setRegisterDatetime(new Date());
            msklUser.setUserInit("2");
            msklUser.setUserStatus("1");
            msklUser.setErrorCount(0);
            msklUser.setLoginCount(0);
            msklUser.setUserKind("01");
            if (StringUtils.isNotBlank(registerDto.getInvitationCode())) {
                msklUser.setRefUserId(Long.parseLong(registerDto.getInvitationCode()));
            }

            msklUserDao.insertSelectiveBackId(msklUser);
            //初始化账户余额
            MsklAccount msklAccount = new MsklAccount();
            msklAccount.setUserId(msklUser.getUserId());
            msklAccount.setAccountStatus("1");
            msklAccount.setAvalaibleAmount(0L);
            msklAccount.setCreateDatetime(new Date());
            msklAccount.setCurrencyType("1");
            msklAccount.setFreezeAmount(0L);
            msklAccount.setVersion(1L);
            msklAccount.setUpdateDatetime(new Date());
            msklAccount.setUserRealName(msklUser.getUserRealName());
            accountService.saveObject(msklAccount);
            result.setSuccess(true);
            result.setData(Boolean.TRUE);
        } catch (Exception e) {
            result.setMessage("增加用户到数据库失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
        }
        return result;
    }

    @Transactional
    public RestServiceResult<String> login(LoginDto loginDto) {
        RestServiceResult<String> result = new RestServiceResult<String>("用户登录服务", false);
        MsklUser msklUser = msklUserDao.selectMsklUserByMobileOrEmail(loginDto.getUsername());
        if (null == msklUser) {
            result.setMessage("查无此账号!");
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        String passwd = MD5Util.encode(loginDto.getPassword());
        if ((!StringUtils.equals(msklUser.getMobile(), loginDto.getUsername()) && !StringUtils.equals(msklUser.getEmail(), loginDto.getUsername())) || !StringUtils.equals(msklUser.getUserPwd(), passwd)) {
            result.setMessage("用户名或者密码不正确!");
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }

        //产生登录token并将她存入redis
        ServiceResult serviceResult = saveTokenToRedis(msklUser);
        if (!serviceResult.isSuccess()) {
            result.setMessage(serviceResult.getMessage());
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        try {
            //更新登录次数
            msklUserDao.increaseLoginCountAndChangeLastLoginTime(loginDto.getUsername());
            //添加登录流水
            MsklUserLoginLog msklUserLoginLog = new MsklUserLoginLog();
            msklUserLoginLog.setIsSuccess("1");
            msklUserLoginLog.setLoginDatetime(new Date());
            msklUserLoginLog.setUserId(msklUser.getUserId());
            msklUserLoginLogService.saveObject(msklUserLoginLog);
            result.setSuccess(true);
            result.setData(serviceResult.getMessage());
            return result;
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("登录更新相关数据库失败!");
            }
            result.setMessage("登录更新相关数据库失败!");
            return result;
        }
    }

    private ServiceResult saveTokenToRedis(MsklUser msklUser) {
        ServiceResult serviceResult = new ServiceResult(false);
        try {
            String loginKey = RedisKeyConstant.LOGINPRE + msklUser.getUserId();
            String token = UUID.randomUUID().toString().replace("-", "") + "|" + msklUser.getUserId();
            redisClient.set(loginKey, token);
            serviceResult.setSuccess(true);
            serviceResult.setMessage(token);
            return serviceResult;
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("保存验证码到redis错误!");
            }
            serviceResult.setMessage("保存验证码到redis错误!");
            return serviceResult;
        }
    }

    public RestServiceResult<Boolean> modifyPassword(ModifyPasswordDto modifyPasswordDto) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("用户修改登录密码服务", false);
        result.setData(Boolean.FALSE);
        if (StringUtils.equals(modifyPasswordDto.getPassword(), modifyPasswordDto.getNewPassword())) {
            result.setMessage("新密码与旧密码相同!");
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        MsklUser msklUser = msklUserDao.selectMsklUserByMobileOrEmail(modifyPasswordDto.getUserName());
        if (null == msklUser) {
            result.setMessage("查无此账号!");
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        String passwd = MD5Util.encode(modifyPasswordDto.getPassword());
        if (StringUtils.equals(msklUser.getUserPwd(), passwd)) {
            result.setMessage("原密码不正确!");
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        try {
            String newPasswd = MD5Util.encode(modifyPasswordDto.getNewPassword());
            msklUser.setUserPwd(newPasswd);
            msklUser.setUserPwdStrength(modifyPasswordDto.getUserPwdStrength());
            updateObject(msklUser);
            result.setSuccess(true);
            result.setData(Boolean.TRUE);
            result.setMessage("修改密码成功");
            return result;
        } catch (Exception e) {
            result.setMessage("修改密码失败");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
            return result;
        }

    }

    public RestServiceResult<Boolean> findLoginPassword(FindLoginPswDto findLoginPswDto) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("用户找回登录密码服务", false);
        if (!msklSmsCheckcodeService.checkSmsCode(findLoginPswDto.getMobile(), findLoginPswDto.getVerificationCode(), CheckcodeType.GETLOGINPSW)) {
            result.setMessage("找回密码验证码不正确!");
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        MsklUser msklUser = msklUserDao.selectMsklUserByMobileOrEmail(findLoginPswDto.getMobile());
        if (null == msklUser) {
            result.setMessage("查无此账号!");
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        String newPasswd = MD5Util.encode(findLoginPswDto.getNewPassword());
        msklUser.setUserPwd(newPasswd);
        try {
            updateObject(msklUser);
            result.setSuccess(true);
            result.setData(Boolean.TRUE);
            result.setMessage("找回密码成功!");

            return result;
        } catch (Exception e) {
            result.setMessage("找回密码更新用户到数据库失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
        }
        return result;
    }

    @Transactional
    public RestServiceResult<Boolean> addUserExtInfo(UserExtDto userExtDto, String token) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("添加用户扩展信息服务", false);
        try {
            MsklUser msklUser = msklUserDao.selectMsklUserByMobileOrEmail(userExtDto.getMobile());
            if (null == msklUser) {
                result.setMessage("查无此账号!");
                if (logger.isInfoEnabled()) {
                    logger.info(result.toString());
                }
                return result;
            }
            boolean updateMsklUser = false;
            if (StringUtils.isNotBlank(userExtDto.getEmail())) {
                msklUser.setEmail(userExtDto.getEmail());
                updateMsklUser = true;
            }
            if (StringUtils.isNotBlank(userExtDto.getNickName())) {
                msklUser.setUserNickName(userExtDto.getNickName());
                updateMsklUser = true;
            }
            //更新主用户信息
            if (updateMsklUser) {
                updateObject(msklUser);
            }
            //获取原来信息
            MsklUserExt msklUserExt = msklUserExtService.getObjectById(msklUser.getUserId());
            if (null == msklUserExt) {
                msklUserExt = new MsklUserExt();
                msklUserExt.setGender(userExtDto.getSex());
                msklUserExt.setUserComefrom(userExtDto.getComeFrom());
                msklUserExt.setUserId(msklUser.getUserId());
                msklUserExt.setUserAge(userExtDto.getAge());
                msklUserExt.setUserPhone(userExtDto.getMobile());
                if (!updateHeaderImg(userExtDto, result, msklUserExt)) {
                    return result;
                }
                msklUserExtService.saveObject(msklUserExt);
            } else {
                msklUserExt.setGender(userExtDto.getSex());
                msklUserExt.setUserComefrom(userExtDto.getComeFrom());
                msklUserExt.setUserAge(userExtDto.getAge());
                if (!updateHeaderImg(userExtDto, result, msklUserExt)) {
                    return result;
                }
                msklUserExtService.updateObject(msklUserExt);
            }
            result.setSuccess(true);
            result.setData(Boolean.TRUE);
            return result;
        } catch (Exception e) {
            result.setMessage("添加用户信息失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
            return result;
        }
    }

    public RestServiceResult<UserInfoVo> getUserInfo(String token) {
        RestServiceResult<UserInfoVo> result = new RestServiceResult<UserInfoVo>("获取用户信息服务", false);
        Long userId = TokenUtil.getUserIdFromToken(token);
        MsklUser msklUser = getObjectById(userId);
        UserInfoVo userInfoVo = new UserInfoVo();
        if (null == msklUser) {
            result.setMessage("查无此账号!");
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        userInfoVo.setEmail(msklUser.getEmail());
        userInfoVo.setMobile(msklUser.getMobile());
        userInfoVo.setNickName(msklUser.getUserNickName());
        MsklUserExt msklUserExt = msklUserExtService.getObjectById(userId);
        if (null != msklUserExt) {
            userInfoVo.setAge(msklUserExt.getUserAge());
            userInfoVo.setComeFrom(msklUserExt.getUserComefrom());
            userInfoVo.setPhoto(msklUserExt.getUserPhoto());
            userInfoVo.setSex(msklUserExt.getGender());
        }

        result.setSuccess(true);
        result.setData(userInfoVo);
        return result;
    }

    private boolean updateHeaderImg(UserExtDto userExtDto, RestServiceResult<Boolean> result, MsklUserExt msklUserExt) {
        if (StringUtils.isNotBlank(userExtDto.getPhoto())) {
            try {
                String filePath = msklFileService.saveFileToServer("/upload/image/header/", userExtDto.getPhoto(), "header.png");
                msklUserExt.setUserPhoto(filePath);
                return true;
            } catch (Exception e) {
                result.setMessage("更新图像失败!");
                e.printStackTrace();
                if (logger.isErrorEnabled()) {
                    logger.error(result.toString());
                }
                return false;
            }
        }
        return true;
    }
}

