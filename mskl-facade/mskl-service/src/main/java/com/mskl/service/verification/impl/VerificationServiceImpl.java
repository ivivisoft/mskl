package com.mskl.service.verification.impl;


import com.mskl.common.dto.RestServiceResult;
import com.mskl.common.util.SignUtil;
import com.mskl.common.util.ValidatorUtil;
import com.mskl.service.token.TokenService;
import com.mskl.service.verification.VerificationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "verificationService")
public class VerificationServiceImpl implements VerificationService {

    @Resource(name = "tokenService")
    private TokenService tokenService;

    public <T> boolean verification(T t, RestServiceResult restServiceResult) {
        return verification(t, StringUtils.EMPTY, 0L, StringUtils.EMPTY, restServiceResult);
    }

    public <T> boolean verification(T t, String token, RestServiceResult restServiceResult) {
        return verification(t, token, 0L, StringUtils.EMPTY, restServiceResult);
    }

    public <T> boolean verification(T t, Long time, String md5str, RestServiceResult restServiceResult) {
        return verification(t, StringUtils.EMPTY, time, md5str, restServiceResult);
    }

    public <T> boolean verification(T t, String token, Long time, String md5str, RestServiceResult restServiceResult) {
        if (null == t) {
            restServiceResult.setSuccess(false);
            restServiceResult.setMessage("参数不正确!");
            return false;
        } else if (t instanceof String) {
            if (StringUtils.isBlank((String) t)) {
                restServiceResult.setSuccess(false);
                restServiceResult.setMessage("参数不正确!");
                return false;
            }
        } else {
            if (!ValidatorUtil.checkBean(t, restServiceResult)) {
                return false;
            }
        }

        if (StringUtils.isNotBlank(md5str) && time != null && time > 0) {
            if (!StringUtils.equals(md5str, SignUtil.signMethod(t, time))) {
                restServiceResult.setSuccess(false);
                restServiceResult.setMessage("方法签名不正确!");
                return false;
            }
        }

        if (StringUtils.isNotBlank(token)) {
            if (!tokenService.checkToken(token)) {
                restServiceResult.setSuccess(false);
                restServiceResult.setMessage("用户token校验失败!");
                return false;
            }
        }
        return true;
    }
}
