package com.commom.Utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class StringUtils {
	public static boolean strIsNotNull(String str){
		if(str!=null && !"".equals(str)){
			return true;
		}
		return false;
	}
	
	/**
	 * 将字符串转Integer类型
	 * @param str		要转换的字符串
	 * @param defVal	如果转换失败返回的默认值
	 * @return
	 */
	public static Integer str2Integer(String str,Integer defVal){
		if(strIsNotNull(str)){
			try {
				return new Integer(str);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return defVal;
	}
	
	/**
	 * 将字符串转Double类型
	 * @param str		要转换的字符串
	 * @param defVal	如果转换失败返回的默认值
	 * @return
	 */
	public static Double str2Double(String str,Double defVal){
		if(strIsNotNull(str)){
			try {
				return new Double(str);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return defVal;
	}
	
	/**
	 * 将字符串转java.sql.Date类型
	 * @param str		要转换的字符串
	 * @param defVal	如果转换失败返回的默认值
	 * @return
	 */
	public static java.sql.Date str2DateBySql(String str,java.util.Date defVal){
		if(strIsNotNull(str)){
			try {
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return new java.sql.Date(sf.parse(str).getTime());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(defVal!=null){
			return new java.sql.Date(defVal.getTime());
		}else{
			return new java.sql.Date(new java.util.Date().getTime());
		}
		
	}
	public static Timestamp str2Timestamp(String str,java.util.Date defVal){
		if(strIsNotNull(str)){
			try {
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
				return new Timestamp(sf.parse(str).getTime());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(defVal!=null){
			return new Timestamp(defVal.getTime());
		}else{
			return new Timestamp(new java.util.Date().getTime());
		}
		
	}
}
