package com.mskl.common.util;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SignUtil {

    private final static String signKey = "U5rRI65jvGBFNG23aZHZGxOUQnmIEPyV";

    public static <T> String signMethod(T i, Long time) {
        String result = MD5Util.encode(getBeanSign(i,time));
        return result;
    }

    private static <T> String getBeanSign(T t, Long time) {
        StringBuilder result = new StringBuilder();
        if (t instanceof  String || t instanceof Integer || t instanceof Long){
            result.append(t).append("='").append(t).append("'&");
        }else {
            Field[] fields = t.getClass().getDeclaredFields();
            List<String> fieldStrs = new ArrayList<String>();
            for (Field fieldStr : fields) {
                fieldStrs.add(fieldStr.getName());
            }
            Collections.sort(fieldStrs);
            for (String field : fieldStrs) {
                try {
                    Object value = ReflectHelper.getValueByFieldName(t, field);
                    result.append(field).append("='").append(value).append("'&");
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        result.append("t=").append(time).append(signKey);
        return result.toString();
    }
}
