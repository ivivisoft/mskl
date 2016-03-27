package com.mskl.common.util;


public final class SignUtil {

    private final static String signKey = "U5rRI65jvGBFNG23aZHZGxOUQnmIEPyV";

    public static <T> String signMethod(T i, Long time) {
        return MD5Util.encode(i.toString() + "&t=" + time + signKey);
    }
}
