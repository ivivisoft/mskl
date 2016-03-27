package com.mskl.common.util;

import com.mskl.common.dto.LoginDto;

import java.security.MessageDigest;

public class MD5Util {

		 // 全局数组
	    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
	            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	    // 返回形式为数字跟字符串
	    private static String byteToArrayString(byte byt) {
	        int iRet = byt;
	        if (iRet < 0) {
	            iRet += 256;
	        }
	        int iD1 = iRet / 16;
	        int iD2 = iRet % 16;
	        return strDigits[iD1] + strDigits[iD2];
	    }

	    // 转换字节数组为16进制字串
	    private static String byteToString(byte[] bytAry) {
	        StringBuffer sBuffer = new StringBuffer();
	        for (int i = 0; i < bytAry.length; i++) {
	            sBuffer.append(byteToArrayString(bytAry[i]));
	        }
	        return sBuffer.toString();
	    }

	    public static String encode(String str) {
	        String resultString = null;
	        try {
	            resultString = new String(str);
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            // md.digest() 该函数返回值为存放哈希值结果的byte数组
	            resultString = byteToString(md.digest(str.getBytes()));
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return resultString;
	    }

	    public static void main(String[] args) {
			LoginDto loginDto =new LoginDto();
			loginDto.setUsername("15024480545");
			loginDto.setPassword("123456");
			System.out.println(MD5Util.encode(SignUtil.signMethod(loginDto,122341514514L)));

	    }
}
