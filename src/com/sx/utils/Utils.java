package com.sx.utils;

import java.io.Reader;
import java.io.StringReader;
import java.sql.Clob;
import java.sql.SQLException;

public class Utils {
	//转换Clob对象为字符串
	public static String convertClob(Clob clob,String daul){
		String reString=null;
		try {
			if(clob!=null&&clob.length()>0){
				reString = clob.getSubString(1,(int)clob.length());
				return reString;
			}else{
				return daul;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	//转换字符串为Reader对象
	public static Reader convertReader(String content){
		if(content!=null){
			Reader reader=new StringReader(content);
			return reader;
		}
		return null;
	}
	
	//转换驼峰原则的字符串为下划线
	public static String convertTo(String str){
		StringBuffer sf=new StringBuffer();
		char[] chars=null;
		if(str!=null){
			chars=str.toCharArray();
			for (int i = 0, length = chars.length; i < length; i++) {  
	             
	            //判断字母是不是大写，如果是大写变为小写  
	            if (Character.isUpperCase(chars[i])){  
	            	sf.append("_");
	            	sf.append(chars[i]);
	                continue;  
	            } 
	            sf.append(chars[i]);
	        }  
 
		}
		return sf.toString(); 	
	}
}
