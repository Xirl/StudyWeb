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
//	entity.setName("����");
//	entity.setPass("456");
	//List<Entity> list = gener.find(null);
//	if(list!=null){
//		System.out.println(list.get(0).getName());
//	}
//	gener.save(entity);
	//������
//	List<Comment> commList=Factory.getCommentImpl().find(null);
//	if(commList!=null && commList.size()>0){
//		System.out.println("list�ǣ�"+commList.get(0).getCcontent());
//	}
	//��ҳ��
//	Map<String,Object> map=new HashMap<String,Object>();
//	map.put("theme_id", 1);
//	PageBean<Comment> page=Factory.getCommentImpl().findCondition(map, 3, 1);
//	if(page.getList()!=null && page.getList().size()>0){
//		
//		System.out.println("list�ǣ�"+page.getList().size()+page.getList().get(0).getId()+
//				page.getList().get(1).getId()+page.getList().get(2).getId());
//	}
//	System.out.println(Utils.convertTo("sssAc"));
	
	//���Դ洢����  ������ͬʱ��������
//	BaseDao b=new BaseDao();
//	Connection con=b.getConnection();
//	CallableStatement cst = null;
//	con.setAutoCommit(false);
//	String insertSql = "begin insert into x_user values (seq_test.nextval,?,?) returning id into ?;end; ";
//	try {
//	 //ִ�д洢����
//		
//		cst = con.prepareCall(insertSql);
//		StringBuffer n=new StringBuffer();
//		for(int i=0;i<10000;i++){
//			n.append("a");
//		}
//		Reader ss=Utils.convertReader(n.toString());
//		cst.setCharacterStream(1, ss,n.toString().length());
//		cst.setObject(2, StringUtils.str2DateBySql(null,null));
//	cst.registerOutParameter(3, Types.INTEGER); //Ϊ�洢�����趨����ֵ
//	
//	int count = cst.executeUpdate();//�õ�Ԥ���������¼�¼��ɾ�������Ľ��
//	int id = cst.getInt(3);//�õ�����ֵ
//
//	System.out.println("�ɹ�ִ����:" + count + "������,��IDֵ:" + id);
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
	
	//����save
//		BaseDao b=new BaseDao();
//		Connection con=b.getConnection();
//		Img img=Factory.getImg();
//		img.setImgCommid(4);
//		img.setImgName("gg");
//		img.setImgPath("ssss");
//		int i=Factory.getImgImpl().save(img);
//		System.out.println(i);
	//�����ʺ�����
//	String mySql="sd? sdfa(?,?) sdf";
//	int i = mySql.length()-mySql.replaceAll("[?]", "").length();
//	System.out.println(i);
	//����findBy���� ��ֻҪ��ReplyType�ֶ������������ײ�getCount������ѯ������ʧЧ����ǰ��map���ĳ���ֶβ�ѯ�����������
//	Reply theReply=Factory.getReply();
//        theReply.setReplyType("1 ");//��ѯ char(2),ע�ⲹ�ո�
//		theReply.setReplyId(2);
//		theReply.setCommentId(2);
//		List<Reply> newReply=Factory.getReplyImpl().findBy(theReply);
//		System.out.println(newReply+"���");
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
