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
			String rootPath=req.getSession().getServletContext().getRealPath("zlpy/doc");
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
			e.printStackTrace();
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
