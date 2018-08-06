package com.crp.qa.qaCore.service.inte;

import java.util.List;

import com.crp.qa.qaCore.domain.dto.QaSearchHistoryDto;
import com.crp.qa.qaCore.util.exception.QaSearchHistoryException;
import com.crp.qa.qaCore.util.exception.QaTreeException;

/**
 * 查询历史服务
 * @author huangyue
 * @Date 2018年6月27日
 * @author huangyue
 */
public interface QaSearchHistoryService {
	
	/**
	 * find by title
	 * @param title
	 * @return
	 * @throws QaSearchHistoryException
	 * @Date 2018年7月18日
	 * @author huangyue
	 */
	public QaSearchHistoryDto findByHistoryContent(String historyContent) throws QaSearchHistoryException;
	/**
	 *  记录查询历史
	 * @param title
	 * @throws QaSearchHistoryException
	 * @Date 2018年6月27日
	 * @author huangyue
	 */
	public QaSearchHistoryDto setHistory(String title) throws QaSearchHistoryException;
	
	/**
	 * delete history by id
	 * @param id
	 * @throws QaSearchHistoryException
	 * @Date 2018年7月18日
	 * @author huangyue
	 */
	public void deleteById(Integer id) throws QaSearchHistoryException;
	
	/**
	 * search top n history
	 * @param rank
	 * @return
	 * @throws QaSearchHistoryException
	 * @Date 2018年7月24日
	 * @author huangyue
	 */
	public List<QaSearchHistoryDto> findTopHistory(Integer rank) throws QaSearchHistoryException;
	
	/**
	 * 记录用户查询情况
	 * @param title
	 * @throws QaTreeException
	 */
	public void searchRecord(String title)throws QaTreeException;
}
