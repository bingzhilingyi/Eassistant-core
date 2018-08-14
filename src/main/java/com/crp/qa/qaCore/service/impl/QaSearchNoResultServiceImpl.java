package com.crp.qa.qaCore.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.crp.qa.qaCore.domain.dto.QaSearchNoResultDto;
import com.crp.qa.qaCore.domain.pojo.QaSearchNoResult;
import com.crp.qa.qaCore.repository.QaSearchNoResultRepository;
import com.crp.qa.qaCore.service.inte.QaSearchNoResultService;
import com.crp.qa.qaCore.util.exception.QaSearchNoResultException;
import com.crp.qa.qaCore.util.transfer.QaPagedDto;

@Service(value="qaSearchNoResultService")
@Transactional
public class QaSearchNoResultServiceImpl extends BaseServiceImpl<QaSearchNoResult> implements QaSearchNoResultService{
	
	@Autowired
	QaSearchNoResultRepository qaSearchNoResultRepository;

	@Override
	public QaSearchNoResultDto save(QaSearchNoResultDto dto) throws QaSearchNoResultException,NullPointerException {
		checkNull(dto,"传入对象为空");
		checkNull(dto.getRecordTitle(),"标题为空");
		if(dto.getRecordId()!=null) {
			throw new QaSearchNoResultException("传入对象已有id");
		}
		try {
			QaSearchNoResult t = new QaSearchNoResult();
			mapper.map(dto, t);
			//设置时间
			t.setCreationDate(new Date());
			t = qaSearchNoResultRepository.saveAndFlush(t);
			mapper.map(t, dto);
			return dto;
		}catch(MappingException e) {
			throw new QaSearchNoResultException("pojo 转 dto 失败");
		}
	}

	@Override
	public QaPagedDto<QaSearchNoResultDto> findPagedAll(Integer page, Integer size) throws QaSearchNoResultException {
		//获取分页
		Pageable pageable = getPageable(page,size);
		//查询
		Page<QaSearchNoResult> paged = qaSearchNoResultRepository.findAll(pageable);
		//返回
		return getReturn(paged);
	}

	@Override
	public QaPagedDto<QaSearchNoResultDto> findByCreationDateBetween(Date from, Date to, Integer page, Integer size)
			throws QaSearchNoResultException {
		//获取分页
		Pageable pageable = getPageable(page,size);
		//查询信息
		Page<QaSearchNoResult> paged;
		if(from!=null && to!=null) {
			paged = qaSearchNoResultRepository.findByCreationDateBetween(from, to, pageable);
		}else if(from!=null && to==null) {
			paged = qaSearchNoResultRepository.findByCreationDateGreaterThanEqual(from, pageable);
		}else if(from==null && to!=null) {
			paged = qaSearchNoResultRepository.findByCreationDateLessThanEqual(to, pageable);
		}else {
			paged = qaSearchNoResultRepository.findAll(pageable);
		}
		return getReturn(paged);
	}

	/**
	 * 设置格式化日期
	 * @param dList
	 * @Date 2018年8月14日
	 * @author huangyue
	 */
	private void setCreationDateStr(List<QaSearchNoResultDto> dList) {
		for(QaSearchNoResultDto d:dList) {
			if(d.getCreationDate()!=null) {
				d.setCreationDateStr(sdf2.format(d.getCreationDate()));
			}
		}
	}
	
	private QaPagedDto<QaSearchNoResultDto> getReturn(Page<QaSearchNoResult> paged) throws QaSearchNoResultException{
		try {
			List<QaSearchNoResultDto> dList = pojoToDto(QaSearchNoResultDto.class,paged.getContent());
			//设置格式化创建日期
			setCreationDateStr(dList);
			//返回信息
			QaPagedDto<QaSearchNoResultDto> returnDto = new QaPagedDto<QaSearchNoResultDto>();
			returnDto.setList(dList); //数据
			returnDto.setTotalElements(paged.getTotalElements()); //总条目
			returnDto.setTotalPages(paged.getTotalPages()); //总页数
			return returnDto;
		} catch (MappingException | IllegalAccessException | InstantiationException e) {
			throw new QaSearchNoResultException("pojo转dto失败",e);
		}
	}
}
