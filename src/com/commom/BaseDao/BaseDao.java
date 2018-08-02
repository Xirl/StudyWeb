package com.commom.BaseDao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BaseDao<T> {
	private String driverClass = "oracle.jdbc.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String userName = "scott";
	private String password = "tiger";

	public Connection conn = null;
	public PreparedStatement sta = null;
	public ResultSet rs = null;

	public Connection getConnection1() {
		try {
			Class.forName(driverClass);
			return DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public void closeAll(Connection conn, PreparedStatement sta, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (sta != null)
				sta.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public Connection getConnection(){
		try {
			//1?????Tomcat????????
			Context ctx=new InitialContext();
			//2?????????
			DataSource ds=(DataSource) ctx.lookup("java:comp/env/jdbc/emp");
			//3????????????
			Connection conn=ds.getConnection();
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}


	public int excutUpdate(String sql, Object... objects) {
		int rows =0;
		try {
			conn = this.getConnection();
			sta = conn.prepareStatement(sql);
			if (objects != null && objects.length > 0) {
				for (int i = 0; i < objects.length; i++) {
					sta.setObject(i + 1, objects[i]);
				}
			}
			rows = sta.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, sta, rs);
		}
		return 0;
	}

	public List<T> excutQuery(String sql, Object... objects) {
		List<T> list = new ArrayList<T>();
		try {
			conn = this.getConnection();
			sta = conn.prepareStatement(sql);
			
			if (objects != null && objects.length > 0) {
				for (int i = 0; i < objects.length; i++) {
					sta.setObject(i + 1, objects[i]);
				}
			}
			rs = sta.executeQuery();
			while (rs.next()) {
				list.add(this.getEntity(rs));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.closeAll(conn, sta, rs);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	
	public int getCount(String sql,Object...objects){
		try {
			conn=this.getConnection();
			sta=conn.prepareStatement("select count(*) c from ("+sql+")");
			if(objects!=null && objects.length>0){
				for(int i=0;i<objects.length;i++){
					sta.setObject(i+1,objects[i]);
				}
			}
			rs=sta.executeQuery();
			if(rs.next()){
				return rs.getInt("c");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
			this.closeAll(conn, sta, rs);
		}
		return 0;
	}
	public String getPageSql(String sql){
		StringBuffer sf=new StringBuffer();
		sf.append("select * from (");
		sf.append("select e.*,rownum rn from (");
		sf.append(sql);
		sf.append(" ) e");
		sf.append(" ) where rn between ?  and ? ");
		return sf.toString();
	}
	/**
	 * 时间类使用DATE类型时可能有问题，如果有就说（TIMEStamp没有问题）
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	public T getEntity(ResultSet rs) throws Exception
	{
		Type[] genType=((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments();
		Class clazz=(Class) genType[0];
		T obj=(T) clazz.newInstance();
		
		Field field[]=clazz.getDeclaredFields();
		for(Field f:field){
			String methodName="set"+f.getName().substring(0,1).toUpperCase()+f.getName().substring(1);
			Method method=clazz.getDeclaredMethod(methodName, f.getType());
			Object value=rs.getObject(f.getName()); 
			if(value!=null){
				if(value.getClass().toString().equals("class java.math.BigDecimal")){
					if(f.getType().toString().equals("class java.lang.Double")
							||f.getType().toString().equals("double")){
						double val=((BigDecimal) value).doubleValue();
						method.invoke(obj, val);
					}else if(f.getType().toString().equals("class java.lang.Integer")
							||f.getType().toString().equals("int")) {
						int val=((BigDecimal) value).intValue();
						method.invoke(obj, val);
					}else{
						throw new RuntimeException("类型不匹配");
					}
				}else{
					method.invoke(obj, value);
				}
			}
		}
		return obj;
	}
	
	public String getType(Object obj){
		return obj.getClass().toString();
	}

}
