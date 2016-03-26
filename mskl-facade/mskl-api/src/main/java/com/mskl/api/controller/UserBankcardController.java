package com.mskl.api.controller;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.dto.UserBankcardDto;
import com.mskl.dao.model.MsklUserBankcard;
import com.mskl.service.userBankcard.UserBankcardServcie;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/userBankcard")
public class UserBankcardController {

    @Resource(name = "userBankcard.userBankcardService")
    private UserBankcardServcie userBankcardServcie;

    @RequestMapping("/{userId}")
    RestServiceResult<List<MsklUserBankcard>> getUserBankcards(@PathVariable String userId){
        RestServiceResult<List<MsklUserBankcard>> result = new RestServiceResult<List<MsklUserBankcard>>();
        if (StringUtils.isBlank(userId)){
            result.setSuccess(false);
            result.setMessage("userId不能为空！");
            return result;
        }
        return userBankcardServcie.getBankcardByUserId(userId);
    }

    @RequestMapping("/insert")
    RestServiceResult<Boolean> insertBankcard(@RequestBody UserBankcardDto userBankcardDto){
        RestServiceResult<Boolean> result = new RestServiceResult<Boolean>();
        if(checkBankcardParam(userBankcardDto)){
            result.setSuccess(false);
            result.setData(Boolean.FALSE);
            result.setMessage("非法参数!");
            return result;
        }
        return userBankcardServcie.insertBankcard(userBankcardDto);
    }

    private boolean checkBankcardParam(UserBankcardDto userBankcardDto) {
        return null == userBankcardDto || StringUtils.isBlank(userBankcardDto.getUserId()) || StringUtils.isBlank(userBankcardDto.getCardNo());
    }

}
