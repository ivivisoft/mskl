package com.mskl.api.controller;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.UserTradeDto;
import com.mskl.service.usertrade.UserTradeService;
import com.sun.org.apache.xpath.internal.operations.String;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/userTrade")
public class userTradeController {

    @Resource(name = "userTrade.userTradeService")
    private UserTradeService userTradeService;


    @RequestMapping("/tradePassword/insert")
    public RestServiceResult<Boolean> insertTradePassword(@RequestBody UserTradeDto userTradeDto) {
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>();
        if (checkUserTradeParam(userTradeDto)) {
            result.setSuccess(false);
            result.setData(Boolean.FALSE);
            result.setMessage("参数非法！");
            return result;
        }
        return userTradeService.insertTradePassword(userTradeDto);
    }

    private boolean checkUserTradeParam(UserTradeDto userTradeDto) {
        return null == userTradeDto || StringUtils.isBlank(userTradeDto.getUserId()) || StringUtils.isBlank(userTradeDto.getUserTradePwd()) || StringUtils.isBlank(userTradeDto.getUserTradePwdStrength());
    }

    @RequestMapping("/tradePassword/update")
    public RestServiceResult<Boolean> updateTradePassword(@RequestBody UserTradeDto userTradeDto){
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>();
        if(checkNewUserTradeParam(userTradeDto)){
            result.setSuccess(false);
            result.setData(Boolean.FALSE);
            result.setMessage("参数非法！");
            return result;
        }
        return userTradeService.updateTradePassword(userTradeDto);
    }
    private boolean checkNewUserTradeParam(UserTradeDto userTradeDto) {
        return null == userTradeDto || StringUtils.isBlank(userTradeDto.getUserId()) || StringUtils.isBlank(userTradeDto.getUserTradePwd()) || StringUtils.isBlank(userTradeDto.getUserTradePwdStrength()) ||StringUtils.isBlank(userTradeDto.getNewUserTradePwd());
    }
}
