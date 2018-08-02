package com.sx.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.commom.Utils.StringUtils;
import com.sx.bean.Reply;
import com.sx.utils.Factory;

public class Test {
public static void main(String[] args) throws SQLException {
//	GeneralDao gener=new EntityImpl();
//	Entity entity=new Entity();
//	entity.setName("张三");
//	entity.setPass("456");
	//List<Entity> list = gener.find(null);
//	if(list!=null){
//		System.out.println(list.get(0).getName());
//	}
//	gener.save(entity);
	//查所有
//	List<Comment> commList=Factory.getCommentImpl().find(null);
//	if(commList!=null && commList.size()>0){
//		System.out.println("list是："+commList.get(0).getCcontent());
//	}
	//分页查
//	Map<String,Object> map=new HashMap<String,Object>();
//	map.put("theme_id", 1);
//	PageBean<Comment> page=Factory.getCommentImpl().findCondition(map, 3, 1);
//	if(page.getList()!=null && page.getList().size()>0){
//		
//		System.out.println("list是："+page.getList().size()+page.getList().get(0).getId()+
//				page.getList().get(1).getId()+page.getList().get(2).getId());
//	}
//	System.out.println(Utils.convertTo("sssAc"));
	
	//测试存储过程  ，插入同时返回主键
//	BaseDao b=new BaseDao();
//	Connection con=b.getConnection();
//	CallableStatement cst = null;
//	con.setAutoCommit(false);
//	String insertSql = "begin insert into x_user values (seq_test.nextval,?,?) returning id into ?;end; ";
//	try {
//	 //执行存储过程
//		
//		cst = con.prepareCall(insertSql);
//		StringBuffer n=new StringBuffer();
//		for(int i=0;i<10000;i++){
//			n.append("a");
//		}
//		Reader ss=Utils.convertReader(n.toString());
//		cst.setCharacterStream(1, ss,n.toString().length());
//		cst.setObject(2, StringUtils.str2DateBySql(null,null));
//	cst.registerOutParameter(3, Types.INTEGER); //为存储过程设定返回值
//	
//	int count = cst.executeUpdate();//得到预编译语句更新记录或删除操作的结果
//	int id = cst.getInt(3);//得到返回值
//
//	System.out.println("成功执行了:" + count + "条数据,其ID值:" + id);
//	} catch (Exception e1) {
//		e1.printStackTrace();
//	con.rollback();
//	con.setAutoCommit(true);
//	}finally{
//	con.commit();
//	con.close();
//	}
	
//	String str="/sx/get";
//	System.out.println(str.substring(1, str.lastIndexOf("/")));
	
	//测试save
//		BaseDao b=new BaseDao();
//		Connection con=b.getConnection();
//		Img img=Factory.getImg();
//		img.setImgCommid(4);
//		img.setImgName("gg");
//		img.setImgPath("ssss");
//		int i=Factory.getImgImpl().save(img);
//		System.out.println(i);
	//测试问号正则
//	String mySql="sd? sdfa(?,?) sdf";
//	int i = mySql.length()-mySql.replaceAll("[?]", "").length();
//	System.out.println(i);
	//测试findBy方法 ，只要加ReplyType字段搜索条件，底层getCount方法查询总数就失效，和前面map添加某个字段查询不出结果类似
//	Reply theReply=Factory.getReply();
//        theReply.setReplyType("1 ");//查询 char(2),注意补空格
//		theReply.setReplyId(2);
//		theReply.setCommentId(2);
//		List<Reply> newReply=Factory.getReplyImpl().findBy(theReply);
//		System.out.println(newReply+"结果");
//	System.out.println(new Date(new java.util.Date().getTime()));
//	System.out.println(System.currentTimeMillis());
//    System.out.println(Calendar.getInstance().getTimeInMillis());
   System.out.println();
   SimpleDateFormat ss= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   String s=ss.format(new java.util.Date().getTime());
//   System.out.println(s);
   
  System.out.println(ss.format(new java.sql.Timestamp(System.currentTimeMillis())));
	//System.out.println(strToDate(s));
 // System.out.println(new Date().getTime());
}

public static java.sql.Date strToDate(String strDate) { 
	 String str = strDate; 
	 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	 java.util.Date d = null; 
	 try { 
	  d = format.parse(str); 
	 } catch (Exception e) { 
	  e.printStackTrace(); 
	 } 
	 java.sql.Date date = new java.sql.Date(d.getTime()); 
	 return date; 
	}
}
