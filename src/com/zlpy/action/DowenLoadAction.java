package com.zlpy.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.commom.BaseAction.BaseAction;
import com.commom.Utils.StringUtils;
import com.zlpy.Bean.PaperDownload;
import com.zlpy.Dao.PaperDownloadDao;
import com.zlpy.Dao.Impl.PaperDownloadDaoImpl;

public class DowenLoadAction extends BaseAction{
	public String down(HttpServletRequest req,HttpServletResponse resp) {
		InputStream is=null;
		OutputStream os=null;
		String msg=null;
		try{
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
			String rootPath=req.getSession().getServletContext().getRealPath("zlpy/doc");
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
			e.printStackTrace();
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
	
	public String downpaper(HttpServletRequest req,HttpServletResponse resp) {
		int queId=StringUtils.str2Integer(req.getParameter("queId"), 0);
		if(queId>0){
			PaperDownloadDao paperDao=new PaperDownloadDaoImpl();
			PaperDownload paper=new PaperDownload();
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("queId", queId);
			paper=paperDao.findPaper(map);
			getJson(req, resp, paper);
		}
		return null;
	}
}
