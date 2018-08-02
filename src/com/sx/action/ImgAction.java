package com.sx.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.commom.BaseAction.BaseAction;
import com.commom.Utils.StringUtils;
import com.sx.bean.Comment;
import com.sx.bean.Img;
import com.sx.impl.CommentImpl;
import com.sx.utils.Factory;

public class ImgAction extends BaseAction {
	
	
	
public String getImg(HttpServletRequest request,HttpServletResponse resp) throws Exception{
	
	String flag=request.getParameter("flag");
	if("1".equals(flag)){
		deleteImg(request, resp);
	}else{
		return saveImg(request, resp);
	}
	return null;
 }

private String deleteImg(HttpServletRequest request, HttpServletResponse resp) {
	BaseServlet base=new BaseServlet();System.out.println("进来没有");
	Map<String, Object> map = new HashMap<String, Object>();
	String imgSrc=request.getParameter("imgSrc");
	String rootPath=request.getServletContext().getRealPath("/");
	String servletPath=request.getServletPath();
	String realPath=rootPath+(servletPath.substring(1,servletPath.lastIndexOf("/"))+"/"+imgSrc).replaceAll("/", "\\\\");
	String deletePath=realPath.substring(0, realPath.lastIndexOf("\\")+1);
	String msg="删除图片失败";
	System.out.println(deletePath);
	File dir=new File(realPath);
	if(dir.exists()){
		dir.delete();
		map.put("status", msg);
		Img img=Factory.getImg();
		img.setImgPath(deletePath);
		int row=Factory.getImgImpl().deleteBy(img);		
		if(row!=0) msg="删除成功";	
	}
	System.out.println(msg);
	base.toJson(map, resp);
	return null;
}

private String saveImg(HttpServletRequest request, HttpServletResponse resp) {
	Map<String, Object> map = new HashMap<String, Object>();System.out.println("进来保存");
	BaseServlet base=new BaseServlet();
	try{
		//String fileTitle1=request.getParameter("fileTitle");
		//System.out.println("fileTitle1:"+fileTitle1);
		boolean isMultipart=ServletFileUpload.isMultipartContent(request);
		if(isMultipart){//如果为true就是上传表单
			//2.1、创建FileItemFactory实例
			FileItemFactory factory=new DiskFileItemFactory();
			//2.2、创建ServletFileUpload实例 
			ServletFileUpload upload=new ServletFileUpload(factory);
			
			//2.3、解析request并转换为单个对象是FileItem实例的集合
			List<FileItem> list=upload.parseRequest(request);
			//2.4、循环遍历集合
			int imgId=0;
			String newfileName=null;
			String rootPath=null;
			String commId=null;
			if(list!=null && list.size()>0){
				for(FileItem item:list){
					if("commId".equals(item.getFieldName())){//表单元素
						commId=item.getString("UTF-8");System.out.println("commId"+commId);
					}
					if(!item.isFormField()){	
						String realPath="/upload";
						String servletPath=request.getServletPath();//   /sx/getImg.action
						String requestURI=request.getRequestURI();//  /StudyWeb/sx/getImg.action
						String date=new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());
						String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+requestURI.substring(0,requestURI.lastIndexOf("/"))+realPath+date;
						//basePath   http://localhost:8080/StudyWeb/sx/upload/2018/06/20/
						
						//下面获得upload文件夹在tomcat下的实际路径
					 rootPath=request.getServletContext().getRealPath("/")+(servletPath.substring(1,servletPath.lastIndexOf("/"))+realPath+date).replaceAll("/", "\\\\");
						//rootPath  E:\MyEclipse Professional 2014\.metadata\.me_tcat7\webapps\StudyWeb\ + sx +\ upload + \2018\06\20\
					
					System.out.println(rootPath);
						//文件夹不存在则创建
						File dir=new File(rootPath);  
				           if(!dir.exists()){  
				               dir.mkdirs();  
				           } 
				       	//2.4.2、获得上传文件的文件名
//							String fileName=item.getName();
//							System.out.println(fileName); glod.png
							String contentType = item.getContentType();
//							System.out.println("contetType"+contentType); image/png
						    String type = contentType.substring(contentType.indexOf("/") + 1);
						    newfileName = new Random().nextInt(1000) + "." + type;
						   // System.out.println("newName"+newfileName);
						//2.4.3、获得要保存在服务上的新的文件对象
						File newFile=new File(rootPath,newfileName);  
						//2.4.4、开始写文件
						item.write(newFile);
						map.put("path",basePath+newfileName);
					
						System.out.println(commId);					
						
						//创建图片记录		
					}	
					if(commId!=null){
						Img img=Factory.getImg();
						img.setImgCommid(StringUtils.str2Integer(item.getString(), 3));
						img.setImgName(newfileName);
						img.setImgPath(rootPath);
						imgId=Factory.getImgImpl().save(img);
					}
				}map.put("imgId", imgId);	
			}
		}
		
	}catch(Exception e){
		e.printStackTrace();
		return SUCCESS;
	}
	base.toJson(map, resp);
	return null;
}
}
