package com.mskl.common.util;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Tools {
	/**
	 * 检测字符串是否不为空(null,"","null")
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s){
		return s!=null && !"".equals(s) && !"null".equals(s);
	}
	
	/**
	 * 检测字符串是否为空(null,"","null")
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s){
		return s==null || "".equals(s) || "null".equals(s);
	}
	
	/**
	 * 字符串转换为字符串数组
	 * @param str 字符串
	 * @param splitRegex 分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str,String splitRegex){
		if(isEmpty(str)){
			return null;
		}
		return str.split(splitRegex);
	}
	
	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * @param str	字符串
	 * @return
	 */
	public static String[] str2StrArray(String str){
		return str2StrArray(str,",\\s*");
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date){
		return date2Str(date,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date){
		if(notEmpty(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new Date();
		}else{
			return null;
		}
	}
	
	/**
	 * 按照参数format的格式，日期转字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date,String format){
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}else{
			return "";
		}
	}
	
	/**
	 * 按照参数format的格式，字符串转日期
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date str2Date(String dateStr,String format){
		if(notEmpty(dateStr)){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			try {
				return sdf.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	// 根据中英文Date将参数转换成相应的时间字符串格式
	public static String dateToZhOrEnFormat(Date date,String langType) {
		SimpleDateFormat formatZhT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 中文带时分秒
		SimpleDateFormat formatZh = new SimpleDateFormat("yyyy-MM-dd");// 中文不带时分秒
		SimpleDateFormat formatEnT = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");// 英文带时分秒
		SimpleDateFormat formatEn = new SimpleDateFormat("MM-dd-yyyy");// 英文不带时分秒
		SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
		//BasePage basePage = new BasePage();
		String dateStr = "";
		if (date != null && !"".equals(date)) {

			if ("00:00:00".equals(formatTime.format(date))) {
				if ("en".equalsIgnoreCase(langType)) {
					dateStr = formatEn.format(date);
				} else {

					dateStr = formatZh.format(date);
				}
			} else {
				if ("en".equalsIgnoreCase(langType)) {
					dateStr = formatEnT.format(date);
				} else {

					dateStr = formatZhT.format(date);
				}
			}
		}
		return dateStr;
	}
	
	
	
	   private static char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7',
		     '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
		     'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
		     'y', 'z', 'A', 'B','C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
		     'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
		     'Z','!','@','#','$','%','^','&','*','~','|'};
	
	   /**
	    * 
	    * @param type
	    * 		1. 数字
	    * 		2. 字符
	    * 		3. 数字 字符 特殊字符
	    * 		4. 数字 字符
	    * 		其他  数字 小写字符
	    * @param passLength
	    * @return
	    */
	public static String getPasswords(int type, int passLength) {
		Random random = new Random();
		if (type == 1) {
			StringBuilder password = new StringBuilder("");
			for (int m = 1; m <= passLength; m++) {
				password.append(chars[random.nextInt(10)]);
			}
			return password.toString();
		} else if (type == 2) {
			StringBuilder password = new StringBuilder("");
			for (int m = 1; m <= passLength; m++) {
				password.append(chars[random.nextInt(52) + 10]);
			}
			return password.toString();
		} else if (type == 3) {
			StringBuilder password = new StringBuilder("");
			for (int m = 1; m <= passLength; m++) {
				password.append(chars[random.nextInt(52)]);
			}
			return password.toString();
		} else if (type == 4) {
			StringBuilder password = new StringBuilder("");
			for (int m = 1; m <= passLength; m++) {
				password.append(chars[random.nextInt(72)]);
			}
			return password.toString();
		} else {
			StringBuilder password = new StringBuilder("");
			for (int m = 1; m <= passLength; m++) {
				password.append(chars[random.nextInt(36)]);
			}
			return password.toString();
		}
	}
	
	public static String getMD5Str(String pwd){
			MessageDigest messageDigest = null;
			try {
				messageDigest = MessageDigest.getInstance("MD5");
				messageDigest.reset();
				messageDigest.update(pwd.getBytes("UTF-8"));
			} catch (NoSuchAlgorithmException e) {
				//System.out.println("NoSuchAlgorithmException caught!");
				System.exit(-1);
			} catch (UnsupportedEncodingException e) {
				//e.printStackTrace();
			}
			byte[] byteArray = messageDigest.digest();
			StringBuffer md5StrBuff = new StringBuffer();
			for (int i = 0; i < byteArray.length; i++) {
				if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
					md5StrBuff.append("0").append(
							Integer.toHexString(0xFF & byteArray[i]));
				else
					md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
			return md5StrBuff.toString();
	}
	
}
