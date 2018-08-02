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
	 * ���ַ���תInteger����
	 * @param str		Ҫת�����ַ���
	 * @param defVal	���ת��ʧ�ܷ��ص�Ĭ��ֵ
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
	 * ���ַ���תDouble����
	 * @param str		Ҫת�����ַ���
	 * @param defVal	���ת��ʧ�ܷ��ص�Ĭ��ֵ
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
	 * ���ַ���תjava.sql.Date����
	 * @param str		Ҫת�����ַ���
	 * @param defVal	���ת��ʧ�ܷ��ص�Ĭ��ֵ
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
