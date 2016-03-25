package com.mskl.common.util;


import java.util.Random;

public final class VerifyCodeUtil {
    private static final String[] POOL ={"1","2","3","4","5","6","7","8","9","0"};

    private static final Random rd = new Random();

    public static String verifyCode(int length){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++){
            sb.append(POOL[rd.nextInt(POOL.length)]);
        }
        return sb.toString();
    }
}
