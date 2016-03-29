package com.mskl.service.verification;


import com.mskl.common.dto.RestServiceResult;

public interface VerificationService {

    /**
     * 验证token
     * @param token
     * @param restServiceResult
     * @return
     */
    boolean  verificationToken(String token,  RestServiceResult restServiceResult);

    /**
     * 验证实体
     * @param t
     * @param restServiceResult
     * @param <T>
     * @return
     */
    <T> boolean  verification(T t, RestServiceResult restServiceResult);

    /**
     * 验证实体和token
     * @param t
     * @param token
     * @param restServiceResult
     * @param <T>
     * @return
     */
    <T> boolean  verification(T t, String token,  RestServiceResult restServiceResult);

    /**
     * 验证实体和方法签名
     * @param t
     * @param time
     * @param md5str
     * @param restServiceResult
     * @param <T>
     * @return
     */
    <T> boolean  verification(T t, Long time, String md5str, RestServiceResult restServiceResult);

    /**
     * 验证实体和方法签名和token
     * @param t
     * @param token
     * @param time
     * @param md5str
     * @param restServiceResult
     * @param <T>
     * @return
     */
    <T> boolean  verification(T t, String token, Long time, String md5str, RestServiceResult restServiceResult);
}
