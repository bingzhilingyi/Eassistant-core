package com.crp.qa.qaCore.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crp.qa.qaCore.domain.dto.QaSearchHistoryDto;
import com.crp.qa.qaCore.domain.pojo.QaSearchHistory;
import com.crp.qa.qaCore.domain.pojo.QaTree;
import com.crp.qa.qaCore.repository.QaSearchHistoryRepository;
import com.crp.qa.qaCore.repository.QaTreeRepository;
import com.crp.qa.qaCore.service.inte.QaSearchHistoryService;
import com.crp.qa.qaCore.service.inte.QaTreeService;
import com.crp.qa.qaCore.util.counter.QaCounter;
import com.crp.qa.qaCore.util.exception.QaSearchHistoryException;
import com.crp.qa.qaCore.util.exception.QaTreeException;

import io.netty.util.internal.StringUtil;

/**
 * 查询历史服务
 * @author huangyue
 * @Date 2018年6月27日
 * @author huangyue
 */
@Service(value="qaSearchHistoryService")
@Transactional
public class QaSearchHistoryServiceImpl extends BaseServiceImpl<QaSearchHistory> implements QaSearchHistoryService {
	
	@Autowired
	public QaTreeRepository qaTreeRepository;
	
	@Autowired
	private QaSearchHistoryRepository qaSearchHistoryRepository;
	
	@Autowired
    StringRedisTemplate stringRedisTemplate;
	
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
			history.setLastUpdateDate(new Date());
			history = qaSearchHistoryRepository.saveAndFlush(history);
			mapper.map(history, rt);
		}else {
			//如果title查不到，那么就添加一条记录
			QaSearchHistory d = new QaSearchHistory();
			d.setHistoryContent(title2);
			d.setRank(1);
			d.setCreationDate(new Date());
			d.setLastUpdateDate(new Date());
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

	@SuppressWarnings("unchecked")
	@Override
	public List<QaSearchHistoryDto> findTopHistory(Integer rank) throws QaSearchHistoryException {
		rank = rank==null?10:rank;
		if(rank<1) {
			throw new QaSearchHistoryException("输入数字不能小于1!");
		}
		//设置分页信息
		Pageable pageable = PageRequest.of(0,rank);
		
		List<QaSearchHistory> pojoList = qaSearchHistoryRepository.findTopNByOrderByRank(rank,pageable);
		List<QaSearchHistoryDto> dtoList = new ArrayList<QaSearchHistoryDto>();
		if(pojoList.size()>0) {
			try {
				dtoList = pojoToDto(QaSearchHistoryDto.class, pojoList);
			}catch(MappingException | InstantiationException | IllegalAccessException e) {
				throw new QaSearchHistoryException("pojo转dto失败！",e);
			}
		}
		return dtoList;
	}

	@Override
	@Async
	public  void searchRecord(String title) throws QaTreeException {
		title = title == null?"":title.trim();
		//判断标题是否为空
		if(title.equals("")) {
			return;
		}
		//查询计数+1
		QaCounter counter = QaCounter.getInstance();
		counter.count();
		//去层级树里查该关键字，如果存在就给rank+1
		synchronized("searchRecord") {
			QaTree t = qaTreeRepository.findByTitle(title);
			if(t!=null) {
				Integer nowCount = t.getRank()==null?0:t.getRank();
				t.setRank(nowCount+1);
				qaTreeRepository.saveAndFlush(t);
			}else {
				//如果层级树里没有该关键字，就查记录表，如果有，给记录表+1，没有，则往记录表里加一条记录		
				try {
					setHistory(title);
				} catch (QaSearchHistoryException e) {
					throw new QaTreeException("记录查询历史报错了",e);
				}
			}
		}	
	}
}
