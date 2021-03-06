package com.mskl.service.usercashout.impl;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.UserCashoutDto;
import com.mskl.common.util.TokenUtil;
import com.mskl.dao.model.MsklAccount;
import com.mskl.dao.model.MsklAccountJour;
import com.mskl.dao.model.MsklUserBankcard;
import com.mskl.dao.model.MsklUserCashoutApplication;
import com.mskl.dao.usercashout.UserCashoutDao;
import com.mskl.service.account.AccountService;
import com.mskl.service.account.AccountJourService;
import com.mskl.service.base.impl.BaseServiceImpl;
import com.mskl.service.userBankcard.UserBankcardServcie;
import com.mskl.service.usercashout.UserCashoutService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service(value = "userCashout.userCashoutService")
public class UserCashoutServiceImpl extends BaseServiceImpl<MsklUserCashoutApplication, Serializable> implements UserCashoutService {

    private Log logger = LogFactory.getLog(UserCashoutServiceImpl.class);

    private UserCashoutDao userCashoutDao;

    @Resource(name = "userCashout.userCashoutDao")
    public void setUserCashoutDao(UserCashoutDao userCashoutDao) {
        this.userCashoutDao = userCashoutDao;
        super.setBaseDaoImpl(userCashoutDao);
    }

    @Resource(name = "userBankcard.userBankcardService")
    private UserBankcardServcie userBankcardServcie;

    @Resource(name = "account.accountService")
    private AccountService accountService;

    @Resource(name = "accountJour.accountJourService")
    private AccountJourService accountJourService;


    public RestServiceResult<Boolean> applyCashout(UserCashoutDto userCashoutDto, String token) {

        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("进入体现申请服务", false);
        Long userId = TokenUtil.getUserIdFromToken(token);

        MsklAccount msklAccount = accountService.getAccountByUserId(userId);
        if (null == msklAccount) {
            result.setMessage("查询账户余额异常!");
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }

        if (Long.parseLong(userCashoutDto.getAmount()) > msklAccount.getAvalaibleAmount()) {
            result.setMessage("账户余额不足!");
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }

        List<MsklUserBankcard> userBankcards = userBankcardServcie.getBankcardsByUserId(userId);
        if (null == userBankcards || userBankcards.size() == 0) {
            result.setMessage("查询银行卡异常!");
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        MsklUserBankcard userBankcard = userBankcards.get(0);
        MsklAccountJour accountJour = new MsklAccountJour();
        accountJour.setMsklAccountId(msklAccount.getMsklAccountId());
        accountJour.setUserId(userId);
        accountJour.setTransDatetime(new Date());
        accountJour.setAccountBizType("03");
        accountJour.setSeqFlag("1");
        accountJour.setRefSerialNo(UUID.randomUUID().toString());
        accountJour.setRemark("用户提现申请");
        accountJour.setWorkDate("");
        accountJour.setVersion(1L);
        try {
            accountJourService.saveObject(accountJour);
        } catch (Exception e) {
            result.setMessage("增加账户流水到数据库失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
        }

        MsklUserCashoutApplication userCashoutApplication = new MsklUserCashoutApplication();
        userCashoutApplication.setAmount(Long.parseLong(userCashoutDto.getAmount()));
        userCashoutApplication.setUserId(userId);
        userCashoutApplication.setBankNo(userBankcard.getBankNo());
        userCashoutApplication.setBankAddrNo(userBankcard.getBankAddrNo());
        userCashoutApplication.setBankName(userBankcard.getBankName());
        userCashoutApplication.setApplicationDatetime(new Date());
        try {
            saveObject(userCashoutApplication);
            result.setSuccess(true);
            result.setData(Boolean.TRUE);
        } catch (Exception e) {
            result.setMessage("增加提现申请到数据库失败!");
            if (logger.isErrorEnabled()) {
                logger.error(result.toString());
            }
        }

        return result;
    }
}
