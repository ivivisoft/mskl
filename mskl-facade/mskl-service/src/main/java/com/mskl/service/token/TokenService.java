package com.mskl.service.token;


public interface TokenService {
    /**
     * 校验token是否合法,检查内容包括,token是否为空,格式是否正确,是不是合法
     * @param token
     * @return
     */
    boolean checkToken(String token);
}
