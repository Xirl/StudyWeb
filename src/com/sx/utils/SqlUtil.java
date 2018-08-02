package com.sx.utils;

import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlUtil<T> {
  private T obj;
  private Class<T> clazz;
  
  
  public SqlUtil(T obj){
	  this.obj=obj;
	  this.clazz=(Class<T>) obj.getClass();
  }
  
	public String insertSql(String type) {
		StringBuffer sb = new StringBuffer();
		String ClassName = clazz.getSimpleName();
		Field[] field = clazz.getDeclaredFields();
		if ("insert".equals(type)) {
			String sql = "begin insert into x_" + ClassName + " values(seq_"+ ClassName+".nextval";
			sb.append(sql);
			for (Field f : field) {
				sb.append(",?");
			}
			sb.delete(sb.length() - 2, sb.length());
			sb.append(") returning id into ?;end;");
		} else {
			String sql = "begin insert into x_" + ClassName + " values(";
			sb.append(sql);
			for (Field f : field) {
				sb.append("?,");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append(") returning id into ?;end;");
		}
		return sb.toString();
	}
  
  public String updateSql(){
	  StringBuffer sb = new StringBuffer();
	  Field[] field = clazz.getDeclaredFields();
	  sb.append("update x_"+clazz.getSimpleName()+" set ");
	  
		  for(Field f:field){
			  if (f.getName().equals("id")) {
	              continue;
	          }
			  sb.append(f.getName()+"=?,");
		  }
		  sb.deleteCharAt(sb.length()-1);
		  sb.append(" where id =?");
  
	//  sb.append(")");  
	  System.out.println(sb.toString()+"更新的sql");
	return sb.toString();  
  }
  
  
  public String deleteBySql(T entity){
	  StringBuffer sb = new StringBuffer();
	  Field[] field = clazz.getDeclaredFields();
	  sb.append("delete from x_"+clazz.getSimpleName()+" where ");
	  for(Field f:field){
		  try {
		 f.setAccessible(true);
			if (f.get(entity)!=null) {
				sb.append(f.getName()+"=?,");
			  }
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	  }
	  sb.deleteCharAt(sb.length()-1);
	   
	  System.out.println(sb.toString());
	return sb.toString();  
  }
  
  public String deleteSql(String id){
	  String sql="delete from "+"x_"+clazz.getSimpleName()+" where id="+id;
	  return sql;
  }
  
//  public String selectSql(Map<String,Object> map){
//	  StringBuffer sb=new StringBuffer();
//	  Field[] field = clazz.getDeclaredFields();
//	  sb.append("select * from x_"+clazz.getSimpleName()+" where 1=1");
//	  if(map!=null&&map.size()>0){
//		  int size=map.size(); 
//		  for(int i=0;i<field.length;i++){
//			  if(map.get(field[i].getName())!=null){
//					if(size==map.size()){
//						sb.append(" and "+field[i].getName()+"=?");
//					}else{
//						sb.append(" or "+field[i].getName()+"=?");
//					}
//					size--;
//				}
//		  }
//	  } 
//	return sb.toString(); 
//  }
  
  
  public String selectSql(T entity,String flag){
	  StringBuffer sb=new StringBuffer();
	  Field[] field = clazz.getDeclaredFields();
	  Object[] temp=new Object[field.length];
	  sb.append("select * from x_"+clazz.getSimpleName()+" where 1=1");
	  if(entity!=null){
		  try {
			  int j=0;
		 	for(int i=0;i<field.length;i++){
			  field[i].setAccessible(true);
			  temp[i]=field[i].get(entity);
			  if(temp[i]!=null){
				  if("1".equals(flag)){
					  if(j==0){
						  sb.append(" and "+field[i].getName()+"=?");
					  }else{
						  sb.append(" or "+field[i].getName()+"=?");
					  }
				  }else{
					  sb.append(" and "+field[i].getName()+"=?");
				  }				  
				 j++;
			  }
			} System.out.println(sb.toString()+"搜索的sql");
		  }catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	  }  
	  System.out.println(sb.toString());
	return sb.toString();  
}
  /**
   * 设置占位符数据
   */
  public Object[] getObject(T entity,String status){
	  Field[] field = clazz.getDeclaredFields();
	  Object[] temp=new Object[field.length];
	  
	  if(entity!=null){
		  try {	
			  if("insert".equals(status)){//插入带序列的数据
				  for(int i=0;i<field.length;i++){
					  field[i].setAccessible(true);
					 // if(field[i].get(entity)==null) continue;//将空元素排在最后
					  temp[i]=field[i].get(entity);
					  }
				    Object obj[] = new Object[field.length];
		            System.arraycopy(temp, 1, obj, 0, temp.length - 1);
		            return temp;
			  }else if("update".equals(status)){//更新
				  for(int i=0;i<field.length;i++){
					  field[i].setAccessible(true);
					  
					  temp[i]=field[i].get(entity);
					  }
				     Object obj[] = new Object[field.length];
		             System.arraycopy(temp, 1, obj, 0, temp.length - 1);
		             obj[obj.length - 1] = temp[0];
		             return obj;
			  }else if("find".equals(status)){
				  List list=new ArrayList(10);
				  for(int i=0;i<field.length;i++){
					  field[i].setAccessible(true);
					  //if(field[i].get(entity)==null) continue;
					  if(field[i].get(entity)!=null){
						  list.add(field[i].get(entity));
					  }
				  } 
				  return list.toArray();
			  }else if("delete".equals(status)){
				  int j=0;
				  for(int i=0;i<field.length;i++){
					  field[i].setAccessible(true);
					  if(field[i].get(entity)==null) continue;//将空元素排在最后
					  temp[j]=field[i].get(entity);
					  j++;
					  }
		            return temp;
			  }
			  else{//插入整条语句集数据
				  for(int i=0;i<field.length;i++){
					  field[i].setAccessible(true);
					 
					  temp[i]=field[i].get(entity);
					  }
			  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		  return temp; 
	  }	  
	 return null;
  }
  
  //设置分页
  public Map<String, Object> getCountSql(Map<String, Object> map,String flag){
	    Field[] field = clazz.getDeclaredFields();
	    StringBuffer sf=new StringBuffer();
		sf.append("select * from x_"+clazz.getSimpleName()+" where 1=1 ");
		List<Object> obj=new ArrayList<Object>();
		String[] str=new String[field.length];
		int size=0;
		if(map!=null) size=map.size();
		if(map!=null&&size>0){	
			 for(int i=0;i<field.length;i++){
					str[i]=field[i].getName();
				}

			for(int i=0;i<str.length;i++){
				if(map.get(str[i])!=null){
					if("1".equals(flag)){
						if(size==map.size()){
							sf.append(" and "+str[i]+"=?");
						}else{
							sf.append(" or "+str[i]+"=?");
						}
					}else{
						sf.append(" and "+str[i]+"=?");
					}		
					size--;
					obj.add(map.get(str[i]));
				}
			}
		}
		System.out.println("分页的sql"+sf.toString());
		Map<String, Object> newMap=new HashMap<String, Object>();
		    newMap.put("Query", sf.toString());
		    newMap.put("obj", obj);

		return newMap;	  
  }

}
