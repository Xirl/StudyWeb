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
			//2��������ص��ļ�����
			String fileName=req.getParameter("fileName");
			//����ͷ������
			//�������ز���
			//content-disposition�����������ļ��Ĵ򿪷�ʽ�Լ���ʾ�������ļ�����
			//  attachment���Ը�������ʽ��
			//  online:���ߴ�
			String fileNameDisp=new String(fileName.getBytes("utf-8"),"iso-8859-1");
			resp.addHeader("content-disposition", "attachment;filename="+fileNameDisp);
			//�������ص����ͣ�MIME����
			resp.setContentType("application/x-download");
			
			//3�����ָ���ļ������ļ�����
			//����ļ��ĸ�·����ʵ��·����
			String rootPath=req.getServletContext().getRealPath("ljc/vedio");
			System.out.println("rootPath:"+rootPath);
			File downFile=new File(rootPath,fileName);
			if(!downFile.exists()) throw new RuntimeException(fileName+"������");
			
			//4�����ļ�д�뵽��Ӧ����
			is=new FileInputStream(downFile);
			os=resp.getOutputStream();//��Ӧ��
			//4.1���߶���д
			byte[] bytes=new byte[1024];
			while(is.read(bytes)!=-1){
				os.write(bytes);
			}
			os.flush();//ˢ�»���
		}catch(Exception e){
			msg=e.getMessage();
			return null;
		}finally{
			//���ڵ���Ĺر���Դ
			try{
				if(os!=null) os.close();//���ܹر�
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
		//����ϴ��ɹ�����ת���ɹ�ҳ�棬����������ʾ�ϴ��ļ�����
		//���ʧ�����ص�upload.jspҳ��
		List<String> fileNameList=new ArrayList<String>();
		//1�����ñ���
		//ע�⣺����ϴ���form����enctype="multipart/form-data"��
		//   ��ôֱ��ͨ��request.getParameter()��ȡ����ֵ��
		System.out.println("come in?");
		try{
			
			req.setCharacterEncoding("utf-8");

			//String fileTitle1=request.getParameter("fileTitle");
			//System.out.println("fileTitle1:"+fileTitle1);
			//2���жϵ�ǰform������ͨ�������ϴ���
			boolean isMultipart=ServletFileUpload.isMultipartContent(req);
			System.out.println("isMultipart:"+isMultipart);
			if(isMultipart){//���Ϊtrue�����ϴ���
				//2.1������FileItemFactoryʵ��
				FileItemFactory factory=new DiskFileItemFactory();
				//2.2������ServletFileUploadʵ�� 
				ServletFileUpload upload=new ServletFileUpload(factory);
				//2.3������request��ת��Ϊ����������FileItemʵ���ļ���
				List<FileItem> list=upload.parseRequest(req);
				//2.4��ѭ����������
				System.out.println("list.size():"+list.size());
				if(list!=null && list.size()>0){
					for(FileItem item:list){
						if(!item.isFormField()){
							//�ϴ���
							//���ļ������ڷ����ϵ�
							//2.4.1��Ҫ��ñ����ļ��ĸ�·��
							//������upload�ļ�����tomcat�µ�ʵ��·��
							String rootPath=req.getServletContext().getRealPath("ljc/images");
							System.out.println("rootPath:"+rootPath);
							//2.4.2������ϴ��ļ����ļ���
							String fileName=item.getName();
							System.out.println("fileName��"+fileName);
							if(fileName==null || "".equals(fileName)) throw new RuntimeException("��ѡ���ϴ��ļ�");
							//2.4.3�����Ҫ�����ڷ����ϵ��µ��ļ�����
							File newFile=new File(rootPath,fileName);
							//2.4.4����ʼд�ļ�
							item.write(newFile);
							fileNameList.add(fileName);
							msg="�ϴ��ɹ�";
							this.getJson(req, resp, msg);
							return null;
						}
					}
				}
			}
			//3�����ļ��б���벢��ת���ɹ�ҳ��
		}catch(Exception e){
			e.printStackTrace();
			msg=e.getMessage();
			System.out.println("msg:"+msg);
			this.getJson(req, resp, msg);
			return null;
		}
		msg="��ѡ���ϴ��ļ�";
		this.getJson(req, resp, msg);
		return null;
	}
}
