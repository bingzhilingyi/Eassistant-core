package com.crp.qa.qaCore.service.impl;

import java.util.Date;

import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crp.qa.qaCore.domain.dto.QaSearchHistoryDto;
import com.crp.qa.qaCore.domain.pojo.QaSearchHistory;
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
public class QaSearchHistoryServiceImpl extends BaseServiceImpl implements QaSearchHistoryService {
	
	@Autowired
	private QaSearchHistoryRepository qaSearchHistoryRepository;
	
	@Override
	public QaSearchHistoryDto findByHistoryContent(String historyContent) throws QaSearchHistoryException {	
		if(historyContent==null || historyContent.trim().equals("")) {
			throw new QaSearchHistoryException("historyContent is null");
		}
		historyContent = historyContent.trim();
		QaSearchHistory t = qaSearchHistoryRepository.findByHistoryContent(historyContent);
		if(t!=null) {
			try {
				QaSearchHistoryDto d = new QaSearchHistoryDto();
				mapper.map(t, d);
				return d;
			}catch(MappingException e) {
				throw new QaSearchHistoryException("exception occured when mapping pojo to dto");
			}		
		}
		return null;
	}

	@Override
	public QaSearchHistoryDto setHistory(String title) throws QaSearchHistoryException {	
		if(StringUtil.isNullOrEmpty(title)) {
			throw new QaSearchHistoryException("传入title为空！");
		}
		String title2 =  title.trim();
		//定义返回值
		QaSearchHistoryDto rt = new QaSearchHistoryDto();
		//先查询一次该title
		QaSearchHistory history = qaSearchHistoryRepository.findByHistoryContent(title2);
		//如果title不为空，则把该title的rank+1
		if(history!=null) {
			history.setRank(history.getRank()+1);
			history = qaSearchHistoryRepository.saveAndFlush(history);
			mapper.map(history, rt);
		}else {
			//如果title查不到，那么就添加一条记录
			QaSearchHistory d = new QaSearchHistory();
			d.setHistoryContent(title2);
			d.setRank(1);
			d.setCreationDate(new Date());
			d = qaSearchHistoryRepository.save(d);
			mapper.map(d, rt);
		}
		return rt;
	}

	@Override
	public void deleteById(Integer id) throws QaSearchHistoryException {
		if(id==null) {
			throw new QaSearchHistoryException("historyId is null");
		}else if(!qaSearchHistoryRepository.existsById(id)) {
			throw new QaSearchHistoryException("historyId not exist");
		}
		qaSearchHistoryRepository.deleteById(id);
	}

	
}
