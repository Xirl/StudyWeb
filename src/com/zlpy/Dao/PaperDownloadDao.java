package com.zlpy.Dao;

import java.util.Map;

import com.zlpy.Bean.PaperDownload;

public interface PaperDownloadDao {
	/**
	 * ���doc����
	 * @param paper
	 * @return
	 */
	public int inserPaper(PaperDownload paper);
	/**
	 * ����������ѯ
	 * @return
	 */
	public PaperDownload findPaper(Map<String, Object> map);
}
