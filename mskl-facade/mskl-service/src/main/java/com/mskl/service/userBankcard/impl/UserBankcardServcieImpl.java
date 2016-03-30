package com.mskl.service.userBankcard.impl;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.UserBankcardDto;
import com.mskl.dao.UserBankcard.UserBankcardDao;
import com.mskl.dao.model.MsklOverseer;
import com.mskl.dao.model.MsklUserBankcard;
import com.mskl.service.base.impl.BaseServiceImpl;
import com.mskl.service.userBankcard.UserBankcardServcie;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@Service(value = "userBankcard.userBankcardService")
public class UserBankcardServcieImpl extends BaseServiceImpl<MsklUserBankcard,Serializable> implements UserBankcardServcie{

    private Log logger = LogFactory.getLog(UserBankcardServcieImpl.class);

    private UserBankcardDao userBankcardDao;

    @Resource(name = "userBankcard.userBankcardDao")
    public void setUserBankcardDao(UserBankcardDao userBankcardDao) {
        this.userBankcardDao = userBankcardDao;
        super.setBaseDaoImpl(userBankcardDao);
    }

    public RestServiceResult<List<MsklUserBankcard>> getBankcardByUserId(String userId) {
        RestServiceResult<List<MsklUserBankcard>> result = new RestServiceResult<List<MsklUserBankcard>>();
        result.setSuccess(false);

        List<MsklUserBankcard> lists = userBankcardDao.getBankcardByUserId(Long.parseLong(userId));
        if (null == lists) {
            result.setMessage("查询银行卡信息异常！");
            if (logger.isInfoEnabled()) {
                logger.info("查询银行卡" + result.toString());
            }
            return result;
        }
        result.setSuccess(true);
        result.setData(lists);
        result.setMessage("查询成功!");

        return result;
    }


    public RestServiceResult<Boolean> insertBankcard(UserBankcardDto userBankcardDto) {

        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>();

        result.setSuccess(false);
        result.setData(Boolean.FALSE);

        MsklUserBankcard userBankcard = new MsklUserBankcard();

        userBankcard.setIsDefault(userBankcardDto.getIsDefault());
        userBankcard.setBankAddrNo(userBankcardDto.getBankAddrNo());
        userBankcard.setBankName(userBankcardDto.getBankName());
        userBankcard.setBankNo(userBankcardDto.getBankNo());
        userBankcard.setCardNo(userBankcardDto.getCardNo());
        userBankcard.setUserId(Long.parseLong(userBankcardDto.getUserId()));

        if (saveObject(userBankcard)>0){
            result.setSuccess(true);
            result.setData(Boolean.TRUE);
            if (logger.isInfoEnabled()) {
                logger.info("添加银行卡" + result.toString());
            }
            return result;
        }
        result.setMessage("添加银行卡失败");
        return result;
    }

    public List<MsklUserBankcard> getBankcardsByUserId(Long userId) {
        List<MsklUserBankcard> lists = userBankcardDao.getBankcardByUserId(userId);
        return lists;
    }
}
