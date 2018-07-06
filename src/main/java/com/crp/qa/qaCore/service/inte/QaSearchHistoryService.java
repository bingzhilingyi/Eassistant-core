package com.crp.qa.qaCore.service.inte;

import com.crp.qa.qaCore.util.exception.QaSearchHistoryException;

/**
 * 查询历史服务
 * @author huangyue
 * @Date 2018年6月27日
 * @author huangyue
 */
public interface QaSearchHistoryService {
	/**
	 *  记录查询历史
	 * @param title
	 * @throws QaSearchHistoryException
	 * @Date 2018年6月27日
	 * @author huangyue
	 */
	public void setHistory(String title) throws QaSearchHistoryException;
}
