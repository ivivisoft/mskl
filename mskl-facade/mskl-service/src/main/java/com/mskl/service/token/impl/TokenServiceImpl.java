package com.mskl.service.token.impl;

import com.mskl.common.constant.RedisKeyConstant;
import com.mskl.common.util.TokenUtil;
import com.mskl.service.redis.RedisClient;
import com.mskl.service.token.TokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "tokenService")
public class TokenServiceImpl implements TokenService {

    @Resource
    private RedisClient redisClient;

    public boolean checkToken(String token) {
        if (StringUtils.isBlank(token)) {
            return false;
        }
        try {
            Long userId = TokenUtil.getUserIdFromToken(token);
            if (0L == userId) {
                return false;
            }
            String loginKey = RedisKeyConstant.LOGINPRE + userId;
            String tokenInRedis = redisClient.get(loginKey);
            if (!StringUtils.equals(tokenInRedis, token)) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
