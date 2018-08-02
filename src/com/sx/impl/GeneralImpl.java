package com.sx.impl;

import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.commom.BaseDao.BaseDao;
import com.sx.bean.PageBean;
import com.sx.dao.GeneralDao;
import com.sx.utils.SqlUtil;
import com.sx.utils.Utils;


public class GeneralImpl<T> implements GeneralDao<T> {
	
    private T entity;
    private Class<T> clazz;
    private BaseDao base;
    private SqlUtil<T> sql;
    
    public Connection conn;
    public PreparedStatement pra;
    public ResultSet rs;
    public CallableStatement cst;
    public GeneralImpl(){
	    	ParameterizedType parameter=(ParameterizedType)getClass().getGenericSuperclass();
	    	clazz=(Class<T>) parameter.getActualTypeArguments()[0];
    	try {
			entity=clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    	base=new BaseDao();
	    	sql=new SqlUtil<T>(entity);
    }

	
	@Override
	public List<T> find(T entity) {
		List<T> list = new ArrayList<T>();
		try {
			conn=base.getConnection1();
			pra=conn.prepareStatement(sql.selectSql(entity,"1"));
			Object[] obj=sql.getObject(entity, "find");
			if(obj!=null&&obj.length>0){
				for(int i=0;i<obj.length;i++){
					System.out.println(i+" "+obj[i]+" ");
					pra.setObject(i+1, obj[i]);
				}
			}
			rs=pra.executeQuery();
			while(rs.next()){
				list.add(getEntity(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//base.closeAll(conn, pra, rs);
		}
		return list;
	}	
@Override
public List<T> findBy(T entity) {
	List<T> list = new ArrayList<T>();
	try {
		conn=base.getConnection1();
		pra=conn.prepareStatement(sql.selectSql(entity,"0"));
		Object[] obj=sql.getObject(entity, "find");
		if(obj!=null&&obj.length>0){
			for(int i=0;i<obj.length;i++){
				System.out.println(i+" "+obj[i]+" ");
				pra.setObject(i+1, obj[i]);
			}
		}
		rs=pra.executeQuery();
		while(rs.next()){
			list.add(getEntity(rs));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		//base.closeAll(conn, pra, rs);
	}
	return list;
	
}

    
	@Override
	public int update(T entity) {
		try {
			conn=base.getConnection1();
			pra=conn.prepareStatement(sql.updateSql()); 
			Object[] obj=sql.getObject(entity, "update");
			
			boolean isflag=false;
			int j=0;
			Reader reader=null;
	       Field[] fields = clazz.getDeclaredFields();
			for(int k=0;k<fields.length;k++){
           j=k-1;//同步后台赋值的数组的下标
				String type=fields[k].getType().toString();
				if(type.contains("Clob")){
					fields[k].setAccessible(true);
					  Clob clobField=(Clob) fields[k].get(entity);
					  if(clobField!=null){
						  reader=new StringReader(Utils.convertClob(clobField, null));//设置clob数据类型的数据
						  pra.setClob(k,reader,Utils.convertClob(clobField, null).length());
					  }else{
						  pra.setClob(k, reader,0);
					  }   
					  isflag=true;
					  break;
				  }	
			}
			if(obj!=null&&obj.length>0){
				for(int i=0;i<obj.length;i++){
					System.out.print("评论更新:"+i+1+" "+obj[i]+" ");
					if(isflag&&i==j){
						continue;
					}
					pra.setObject(i+1, obj[i]);
				}
			}
			return pra.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			base.closeAll(conn, pra, rs);
		}
		return 0;
	}

	@Override
	public int save(T entity) {
		int id = 0;
		try {
			conn=base.getConnection1();
			conn.setAutoCommit(false);
			String mySql=sql.insertSql("insert");
			//执行存储过程
			System.out.println("sql语句"+mySql);
			cst = conn.prepareCall(mySql);
			Object[] obj=sql.getObject(entity,"insert");
			boolean isflag=false;
			int j=0;
	       Field[] fields = clazz.getDeclaredFields();
	       Reader reader=null;
			for(int k=0;k<fields.length;k++){
           j++;//同步后台赋值的数组的下标
				String type=fields[k].getType().toString();
				if(type.contains("Clob")){
					  fields[k].setAccessible(true);
					  String clobField=(String) fields[k].get(entity);
					  System.out.println("clob字段"+fields[k].get(entity));
					  if(clobField!=null)
						  {
						  reader=new StringReader(clobField);//设置clob数据类型的数据
						  cst.setCharacterStream(k+1, reader,clobField.length()); 
						  }else{
							  cst.setCharacterStream(k+1, reader,0); 
						  }
					  isflag=true;
					  break;
				  }	
			}
			//设置占位符值
			if(obj!=null&&obj.length>0){
				for(int i=1;i<obj.length;i++){
					if(isflag&&i==j){
						continue;
					}
				//	if(obj[i]==null) continue;//去除数组空元素，底层处理过来的数组空元素都排在后面
					cst.setObject(i, obj[i]);
				}
			}
			//为最后一个返回值注册数据类型,获取sql中占位符最后的长度
			int legth=mySql.length()-mySql.replaceAll("[?]", "").length();
			System.out.println("长度"+legth);
			cst.registerOutParameter(legth, Types.INTEGER); //为存储过程设定返回值
			int count = cst.executeUpdate();//得到预编译语句更新记录或删除操作的结果
			id = cst.getInt(legth);//得到返回值
		
		} catch (Exception e1) {
			e1.printStackTrace();
				try {
					conn.rollback();
					conn.setAutoCommit(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}finally{
				try {
					conn.commit();
				//	base.closeAll(conn, cst, rs);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return id;	
	}
	

	@Override
	public int delete(String id) {
		try {
			conn=base.getConnection1();
			pra=conn.prepareStatement(sql.deleteSql(id));
			return pra.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			base.closeAll(conn, pra, rs);
		}
		return 0;
	}
	
	@Override
	public int deleteBy(T entity) {
		try {
			conn=base.getConnection1();
			pra=conn.prepareStatement(sql.deleteBySql(entity));
			Object[] obj=sql.getObject(entity,"delete");
			if(obj!=null&&obj.length>0){
				for(int i=0;i<obj.length;i++){
					if(obj[i]==null) continue;//去除数组空元素，底层处理过来的数组空元素都排在后面
					System.out.print(i+1+"   "+obj[i]+ "   ");
					pra.setObject(i+1,obj[i]);
				}
			}
			int row=pra.executeUpdate();
			//System.out.println(row+"删除删除");
			return row;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	
	
	private T getEntity(ResultSet rs) throws Exception
	{
		 Field[] fields=clazz.getDeclaredFields();
		 entity=clazz.newInstance();
       int i=1;
		if (fields != null && fields.length > 0) {
			for (Field f : fields) {
			
				String fname = f.getName().substring(0, 1).toUpperCase()
						+ f.getName().substring(1);
				String methodName = "set" + fname;
				Method m1 = clazz.getDeclaredMethod(methodName, f.getType());
				try {//System.out.print(f.getName()+"  "+f.getType()+" ");
					if (f.getType().toString().contains("Double")&&rs.getObject(i)!=null) {
						m1.invoke(entity, rs.getDouble(i));
					} else if (f.getType().toString().contains("Integer")&&rs.getObject(i)!=null) {
						m1.invoke(entity, rs.getInt(i));
					} else if (f.getType().toString().contains("Date")&&rs.getObject(i)!=null) {
						if("replyDate".equals(f.getName())){
							java.sql.Timestamp date=(Timestamp) rs.getObject(i);
							m1.invoke(entity, date);
						}else{
							java.sql.Date date = new java.sql.Date(
									(((java.util.Date) rs.getObject(i))).getTime());
							m1.invoke(entity, date);
						}
					} else {
						m1.invoke(entity, rs.getObject(i));
					}
				} catch (Exception e) {
					//m1.invoke(entity, "");
					e.printStackTrace();
				}
				i++;
			}
       }
		return entity;	
	}

  //分页
	@Override
	public PageBean<T> findCondition(Map<String, Object> map, int pageSize,
			int pageIndex) {
		
			PageBean<T> page=new PageBean<T>();
			page.setPageIndex(pageIndex);
			page.setPageSize(pageSize);
			
			//设置查询条件
			String Query=sql.getCountSql(map,"0").get("Query").toString();
			List<Object> obj=(List<Object>) sql.getCountSql(map,"0").get("obj");
			int totalPage=base.getCount(Query,obj.size()>0?obj.toArray():null);
			System.out.println(totalPage+"总页数");
			page.setPageTotal(totalPage);
			if(totalPage!=0){
				String OuerySql="select * from (select tmp.*,rownum rn from ("+Query+")tmp where"
						+ " rownum<=?)where rn>=?";
			
				obj.add(pageIndex*pageSize);
				obj.add(pageSize*(pageIndex-1)+1);
				System.out.println("整条sql"+OuerySql+"  "+"obj的值"+obj.get(0));
				page.setList(findByArray(OuerySql, obj.toArray()));
			}
			return page;
	}
	
	public List<T> findByArray(String OuerySql,Object[] obj){
		List<T> list=new ArrayList<T>();
		try {
			conn=base.getConnection1();
			pra=conn.prepareStatement(OuerySql);
			if(obj!=null&&obj.length>0){
				for(int i=0;i<obj.length;i++){
					pra.setObject(i+1, obj[i]);
				}
			}
			rs=pra.executeQuery();
		
			while(rs.next()){
				list.add(getEntity(rs));
			}	
		} catch (Exception e) {
	     	e.printStackTrace();
		}	
		return list;
	}
	
	//按条件查记录数量
	@Override
	public int findCount(Map<String, Object> map) {
		String Query=sql.getCountSql(map,"0").get("Query").toString();
		List<Object> obj=(List<Object>) sql.getCountSql(map,"0").get("obj");
		int totalPage=base.getCount(Query,obj.size()>0?obj.toArray():null);
	
		System.out.println(totalPage);
		return totalPage;
	}
}
