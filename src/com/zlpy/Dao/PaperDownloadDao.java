package com.zlpy.Dao;

import java.util.Map;

import com.zlpy.Bean.PaperDownload;

public interface PaperDownloadDao {
	/**
	 * 添加doc名称
	 * @param paper
	 * @return
	 */
	public int inserPaper(PaperDownload paper);
	/**
	 * 根据条件查询
	 * @return
	 */
	public PaperDownload findPaper(Map<String, Object> map);
}
