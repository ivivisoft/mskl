package com.mskl.api.controller;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.UserCashoutDto;
import com.mskl.service.usercashout.UserCashoutService;
import com.mskl.service.verification.VerificationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/userCashout")
public class UserCashoutController {

    private Log logger = LogFactory.getLog(UserCashoutController.class);

    @Resource(name = "userCashout.userCashoutService")
    private UserCashoutService userCashoutService;

    @Resource(name = "verificationService")
    private VerificationService verificationService;

    @RequestMapping("/apply/{time}/{md5str}/{token}")
    public RestServiceResult<Boolean> applyCashout(@RequestBody UserCashoutDto userCashoutDto, @PathVariable Long time, @PathVariable String md5str, @PathVariable String token) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("进入提现申请controller类", true);
        if (!verificationService.verification(userCashoutDto, token, time, md5str, result)) {
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        return userCashoutService.applyCashout(userCashoutDto,token);
    }
}
