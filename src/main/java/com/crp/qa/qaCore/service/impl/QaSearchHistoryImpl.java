package com.crp.qa.qaCore.service.impl;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crp.qa.qaCore.domain.domain.QaSearchHistory;
import com.crp.qa.qaCore.repository.QaSearchHistoryRepository;
import com.crp.qa.qaCore.service.inte.QaSearchHistoryService;
import com.crp.qa.qaCore.util.exception.QaSearchHistoryException;

import io.netty.util.internal.StringUtil;

/**
 * 查询历史服务
 * @author huangyue
 * @Date 2018年6月27日
 * @author huangyue
 */
@Service(value="qaSearchHistoryService")
@Transactional
public class QaSearchHistoryImpl implements QaSearchHistoryService {
	
	@Autowired
	private QaSearchHistoryRepository qaSearchHistoryRepository;

	@Override
	public void setHistory(String title) throws QaSearchHistoryException {
		String title2=  title.trim();
		if(StringUtil.isNullOrEmpty(title2)) {
			throw new QaSearchHistoryException("传入title为空！");
		}
		//先查询一次该title
		QaSearchHistory history = qaSearchHistoryRepository.findByHistoryContent(title2);
		//如果title不为空，则把该title的rank+1
		if(history!=null) {
			history.setRank(history.getRank()+1);
			qaSearchHistoryRepository.saveAndFlush(history);
		}else {
			//如果title查不到，那么就添加一条记录
			QaSearchHistory d = new QaSearchHistory();
			d.setHistoryContent(title2);
			d.setRank(1);
			d.setCreationDate(new Date());
			qaSearchHistoryRepository.save(d);
		}
	}

}
