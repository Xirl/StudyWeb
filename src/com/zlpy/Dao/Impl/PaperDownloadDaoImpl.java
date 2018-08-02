package com.zlpy.Dao.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.commom.BaseDao.BaseDao;
import com.zlpy.Bean.PaperDownload;
import com.zlpy.Dao.PaperDownloadDao;

public class PaperDownloadDaoImpl extends BaseDao<PaperDownload> implements
		PaperDownloadDao {

	@Override
	public int inserPaper(PaperDownload paper) {
		StringBuffer sf=new StringBuffer();
		List<Object> list=new ArrayList<Object>();
		sf.append("insert into y_paper_download values(seq.nextval,?,?)");
		list.add(paper.getPaperName());
		list.add(paper.getQueId());
		return this.excutUpdate(sf.toString(), list.toArray());
	}

	@Override
	public PaperDownload findPaper(Map<String, Object> map) {
		StringBuffer sf=new StringBuffer();
		List<Object> list=new ArrayList<Object>();
		sf.append("select * from y_paper_download where 1=1");
		if(map!=null && map.size()>0){
			if(map.get("queId")!=null){
				sf.append(" and queId=?");
				list.add(map.get("queId"));
			}
		}
		List<PaperDownload> paperList=new ArrayList<PaperDownload>();
		paperList=this.excutQuery(sf.toString(), list.toArray());
		if(paperList!=null && paperList.size()>0){
			return paperList.get(0);
		}
		return null;
	}

}
