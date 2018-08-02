package com.ljc.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.commom.BaseAction.BaseAction;
import com.commom.struts.action.Action;

public class DownLoadAction extends BaseAction implements Action {
	public String downvedio(HttpServletRequest req,HttpServletResponse resp) {
		InputStream is=null;
		OutputStream os=null;
		String msg=null;
		try{
			System.out.println("come in?");
			req.setCharacterEncoding("utf-8");
			//2、获得下载的文件名称
			String fileName=req.getParameter("fileName");
			//设置头部参数
			//设置下载参数
			//content-disposition：设置下载文件的打开方式以及显示的下载文件名称
			//  attachment：以附件的形式打开
			//  online:在线打开
			String fileNameDisp=new String(fileName.getBytes("utf-8"),"iso-8859-1");
			resp.addHeader("content-disposition", "attachment;filename="+fileNameDisp);
			//设置下载的类型：MIME类型
			resp.setContentType("application/x-download");
			
			//3、获得指定文件名的文件对象
			//获得文件的根路径（实际路径）
			String rootPath=req.getServletContext().getRealPath("ljc/vedio");
			System.out.println("rootPath:"+rootPath);
			File downFile=new File(rootPath,fileName);
			if(!downFile.exists()) throw new RuntimeException(fileName+"不存在");
			
			//4、读文件写入到响应流中
			is=new FileInputStream(downFile);
			os=resp.getOutputStream();//响应流
			//4.1、边读边写
			byte[] bytes=new byte[1024];
			while(is.read(bytes)!=-1){
				os.write(bytes);
			}
			os.flush();//刷新缓存
		}catch(Exception e){
			msg=e.getMessage();
			return null;
		}finally{
			//从内到外的关闭资源
			try{
				if(os!=null) os.close();//不能关闭
				if(is!=null) is.close();
				return null;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return null;
	}
	public String uploadhead(HttpServletRequest req,HttpServletResponse resp){
		String msg=null;
		//如果上传成功就跳转到成功页面，并用链接显示上传文件名称
		//如果失败跳回到upload.jsp页面
		List<String> fileNameList=new ArrayList<String>();
		//1、设置编码
		//注意：如果上传的form表单的enctype="multipart/form-data"，
		//   那么直接通过request.getParameter()是取不到值的
		System.out.println("come in?");
		try{
			
			req.setCharacterEncoding("utf-8");

			//String fileTitle1=request.getParameter("fileTitle");
			//System.out.println("fileTitle1:"+fileTitle1);
			//2、判断当前form表单是普通表单还是上传表单
			boolean isMultipart=ServletFileUpload.isMultipartContent(req);
			System.out.println("isMultipart:"+isMultipart);
			if(isMultipart){//如果为true就是上传表单
				//2.1、创建FileItemFactory实例
				FileItemFactory factory=new DiskFileItemFactory();
				//2.2、创建ServletFileUpload实例 
				ServletFileUpload upload=new ServletFileUpload(factory);
				//2.3、解析request并转换为单个对象是FileItem实例的集合
				List<FileItem> list=upload.parseRequest(req);
				//2.4、循环遍历集合
				System.out.println("list.size():"+list.size());
				if(list!=null && list.size()>0){
					for(FileItem item:list){
						if(!item.isFormField()){
							//上传表单
							//将文件保存在服务上的
							//2.4.1、要获得保存文件的根路径
							//下面获得upload文件夹在tomcat下的实际路径
							String rootPath=req.getServletContext().getRealPath("ljc/images");
							System.out.println("rootPath:"+rootPath);
							//2.4.2、获得上传文件的文件名
							String fileName=item.getName();
							System.out.println("fileName："+fileName);
							if(fileName==null || "".equals(fileName)) throw new RuntimeException("请选择上传文件");
							//2.4.3、获得要保存在服务上的新的文件对象
							File newFile=new File(rootPath,fileName);
							//2.4.4、开始写文件
							item.write(newFile);
							fileNameList.add(fileName);
							msg="上传成功";
							this.getJson(req, resp, msg);
							return null;
						}
					}
				}
			}
			//3、将文件列表存入并跳转到成功页面
		}catch(Exception e){
			e.printStackTrace();
			msg=e.getMessage();
			System.out.println("msg:"+msg);
			this.getJson(req, resp, msg);
			return null;
		}
		msg="请选择上传文件";
		this.getJson(req, resp, msg);
		return null;
	}
}
