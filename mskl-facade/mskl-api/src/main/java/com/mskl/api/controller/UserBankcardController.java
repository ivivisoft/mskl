package com.mskl.api.controller;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.UserBankcardDto;
import com.mskl.dao.model.MsklUserBankcard;
import com.mskl.service.userBankcard.UserBankcardServcie;
import com.mskl.service.verification.VerificationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/userBankcard")
public class UserBankcardController {

    private Log logger = LogFactory.getLog(UserBankcardController.class);

    @Resource(name = "userBankcard.userBankcardService")
    private UserBankcardServcie userBankcardServcie;

    @Resource(name = "verificationService")
    private VerificationService verificationService;

    @RequestMapping("/select/{token}")
    RestServiceResult<List<MsklUserBankcard>> getUserBankcards(@PathVariable String token) {
        RestServiceResult<List<MsklUserBankcard>> result = new RestServiceResult<List<MsklUserBankcard>>("进入查询银行卡Controller类!", true);
        if (!verificationService.verification(token, result)) {
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        return userBankcardServcie.getBankcardByUserId(token);
    }

    @RequestMapping("/insert/{time}/{md5str}/{token}")
    RestServiceResult<Boolean> insertBankcard(@RequestBody UserBankcardDto userBankcardDto, @PathVariable Long time, @PathVariable String md5str, @PathVariable String token) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("进入添加银行卡Controller类", true);
        if (!verificationService.verification(userBankcardDto, token, time, md5str, result)) {
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        return userBankcardServcie.insertBankcard(userBankcardDto,token);
    }
}
