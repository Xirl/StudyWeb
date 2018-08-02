package com.sx.utils;

import java.io.Reader;
import java.io.StringReader;
import java.sql.Clob;
import java.sql.SQLException;

public class Utils {
	//ת��Clob����Ϊ�ַ���
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
	
	//ת���ַ���ΪReader����
	public static Reader convertReader(String content){
		if(content!=null){
			Reader reader=new StringReader(content);
			return reader;
		}
		return null;
	}
	
	//ת���շ�ԭ����ַ���Ϊ�»���
	public static String convertTo(String str){
		StringBuffer sf=new StringBuffer();
		char[] chars=null;
		if(str!=null){
			chars=str.toCharArray();
			for (int i = 0, length = chars.length; i < length; i++) {  
	             
	            //�ж���ĸ�ǲ��Ǵ�д������Ǵ�д��ΪСд  
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
