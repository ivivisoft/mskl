package com.mskl.api.controller;

import com.mskl.common.dto.ModifyTradeDto;
import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.UserTradeDto;
import com.mskl.service.usertrade.UserTradeService;
import com.mskl.service.verification.VerificationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/userTrade")
public class UserTradeController {

    private Log logger = LogFactory.getLog(UserTradeController.class);

    @Resource(name = "verificationService")
    private VerificationService verificationService;

    @Resource(name = "userTrade.userTradeService")
    private UserTradeService userTradeService;



    @RequestMapping("/tradePassword/insert/{token}")
    public RestServiceResult<Boolean> insertTradePassword(@RequestBody UserTradeDto userTradeDto, @PathVariable String token) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("进入添加提现密码Controller类",true);
        if (!verificationService.verification(userTradeDto, token, result)) {
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        return userTradeService.insertTradePassword(userTradeDto,token);
    }


    @RequestMapping("/tradePassword/update/{token}")
    public RestServiceResult<Boolean> updateTradePassword(@RequestBody ModifyTradeDto modifyTradeDto,@PathVariable String token) {

        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>("进入修改提现密码Controller类!",true);
        if (!verificationService.verification(modifyTradeDto, token, result)) {
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }
        return userTradeService.updateTradePassword(modifyTradeDto,token);
    }
}
