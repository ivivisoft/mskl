package com.mskl.common.util;


import org.apache.commons.lang.StringUtils;

public final class TokenUtil {
    public static Long  getUserIdFromToken(String token){
        if (StringUtils.isNotBlank(token)){
            return Long.parseLong(StringUtils.substringAfter(token,"|"));
        }
        return 0l;
    }
}
