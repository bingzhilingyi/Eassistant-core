package com.crp.qa.qaCore.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.crp.qa.qaCore.domain.dto.QaSearchHistoryDto;
import com.crp.qa.qaCore.domain.dto.QaSearchNoResultDto;
import com.crp.qa.qaCore.domain.pojo.QaSearchHistory;
import com.crp.qa.qaCore.domain.pojo.QaSearchNoResult;
import com.crp.qa.qaCore.repository.QaSearchHistoryRepository;
import com.crp.qa.qaCore.repository.QaSearchNoResultRepository;
import com.crp.qa.qaCore.service.inte.QaSearchNoResultService;
import com.crp.qa.qaCore.util.exception.QaSearchHistoryException;
import com.crp.qa.qaCore.util.exception.QaSearchNoResultException;
import com.crp.qa.qaCore.util.transfer.QaPagedDto;

@Service(value="qaSearchNoResultService")
@Transactional
public class QaSearchNoResultServiceImpl extends BaseServiceImpl<QaSearchHistory> implements QaSearchNoResultService{
	
	@Autowired
	private QaSearchHistoryRepository qaSearchHistoryRepository;
	
	@Override
	public QaPagedDto<QaSearchHistoryDto> findPagedAll(Integer page, Integer size) throws QaSearchHistoryException {
		//获取分页
		Pageable pageable = getPageable(page,size);
		//查询
		Page<QaSearchHistory> paged = qaSearchHistoryRepository.findByIsNoResult("Y",pageable);
		//返回
		return getReturn(paged);
	}

	@Override
	public QaPagedDto<QaSearchHistoryDto> findByCreationDateBetween(Date from, Date to, Integer page, Integer size)
			throws QaSearchHistoryException {
		//获取分页
		Pageable pageable = getPageable(page,size);
		//查询信息
		Page<QaSearchHistory> paged;
		if(from!=null && to!=null) {
			paged = qaSearchHistoryRepository.findByIsNoResultAndCreationDateBetween("Y",from, to, pageable);
		}else if(from!=null && to==null) {
			paged = qaSearchHistoryRepository.findByIsNoResultAndCreationDateGreaterThanEqual("Y",from, pageable);
		}else if(from==null && to!=null) {
			paged = qaSearchHistoryRepository.findByIsNoResultAndCreationDateLessThanEqual("Y",to, pageable);
		}else {
			paged = qaSearchHistoryRepository.findByIsNoResult("Y",pageable);
		}
		return getReturn(paged);
	}
	
	/**
	 * 设置格式化日期
	 * @param dList
	 * @Date 2018年8月14日
	 * @author huangyue
	 */
	private void setCreationDateStr(List<QaSearchHistoryDto> dList) {
		for(QaSearchHistoryDto d:dList) {
			if(d.getCreationDate()!=null) {
				d.setCreationDateStr(sdf2.format(d.getCreationDate()));
			}
		}
	}
	
	private QaPagedDto<QaSearchHistoryDto> getReturn(Page<QaSearchHistory> paged) throws QaSearchHistoryException{
		try {
			List<QaSearchHistoryDto> dList = pojoToDto(QaSearchHistoryDto.class,paged.getContent());
			//设置格式化创建日期
			setCreationDateStr(dList);
			//返回信息
			QaPagedDto<QaSearchHistoryDto> returnDto = new QaPagedDto<QaSearchHistoryDto>();
			returnDto.setList(dList); //数据
			returnDto.setTotalElements(paged.getTotalElements()); //总条目
			returnDto.setTotalPages(paged.getTotalPages()); //总页数
			return returnDto;
		} catch (MappingException | IllegalAccessException | InstantiationException e) {
			throw new QaSearchHistoryException("pojo转dto失败",e);
		}
	}
}
