package com.mskl.service.usertrade.impl;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.UserTradeDto;
import com.mskl.common.util.MD5Util;
import com.mskl.dao.model.MsklUserTrade;
import com.mskl.dao.usertrade.UserTradeDao;
import com.mskl.service.base.impl.BaseServiceImpl;
import com.mskl.service.usertrade.UserTradeService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

@Service(value = "userTrade.userTradeService")
public class UserTradeServiceImpl extends BaseServiceImpl<MsklUserTrade, Serializable> implements UserTradeService {

    private Log logger = LogFactory.getLog(UserTradeServiceImpl.class);

    private UserTradeDao userTradeDao;

    @Resource(name = "userTrade.userTradeDao")
    public void setUserTradeDao(UserTradeDao userTradeDao) {
        this.userTradeDao = userTradeDao;
        super.setBaseDaoImpl(userTradeDao);
    }

    public RestServiceResult<Boolean> insertTradePassword(UserTradeDto userTradeDto) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>();
        result.setSuccess(false);
        result.setData(Boolean.FALSE);

        MsklUserTrade userTrade = userTradeDao.getTradeByUserId(Long.parseLong(userTradeDto.getUserId()));
        if (null == userTrade) {
            result.setMessage("用户ID不正确!");
            if (logger.isInfoEnabled()) {
                logger.info("添加交易密码" + result.toString());
            }
            return result;

        }
        userTrade.setUserTradePwd(MD5Util.encode(userTradeDto.getUserTradePwd()));
        userTrade.setUserTradePwdStrength(userTradeDto.getUserTradePwdStrength());
        if (saveObject(userTrade) > 0) {
            result.setSuccess(true);
            result.setData(Boolean.TRUE);
            result.setMessage("添加成功！");
            return result;
        }
        result.setMessage("添加失败!");
        return result;
    }

    public RestServiceResult<Boolean> updateTradePassword(UserTradeDto userTradeDto) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>();
        result.setSuccess(false);
        result.setData(Boolean.FALSE);

        MsklUserTrade userTrade = userTradeDao.getTradeByUserId(Long.parseLong(userTradeDto.getUserId()));
        if (null == userTrade) {
            result.setMessage("用户ID不正确!");
            if (logger.isInfoEnabled()) {
                logger.info("修改交易密码" + result.toString());
            }
            return result;
        }
        //原交易密码
        String tradePassword = MD5Util.encode(userTradeDto.getUserTradePwd());
        if (!StringUtils.equals(tradePassword, userTrade.getUserTradePwd())) {
            result.setMessage("原交易密码不正确!");
            if (logger.isInfoEnabled()) {
                logger.info("修改交易密码" + result.toString());
            }
            return result;
        }
        //新交易密码
        String newTradePassword = MD5Util.encode(userTradeDto.getNewUserTradePwd());
        if (StringUtils.equals(tradePassword, newTradePassword)) {
            result.setMessage("交易密码与原密码相同!");
            if (logger.isInfoEnabled()) {
                logger.info("修改交易密码" + result.toString());
            }
            return result;
        }
        userTrade.setUserTradePwd(newTradePassword);
        userTrade.setUserTradePwdStrength(userTradeDto.getUserTradePwdStrength());
        if (updateObject(userTrade) > 0) {
            result.setSuccess(true);
            result.setData(Boolean.TRUE);
            result.setMessage("添加成功！");
            return result;
        }
        result.setMessage("添加失败!");
        return result;
    }
}
