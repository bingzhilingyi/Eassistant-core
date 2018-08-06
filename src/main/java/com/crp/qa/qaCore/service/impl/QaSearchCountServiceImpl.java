package com.crp.qa.qaCore.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crp.qa.qaCore.domain.dto.QaSearchCountDto;
import com.crp.qa.qaCore.domain.pojo.QaSearchCount;
import com.crp.qa.qaCore.repository.QaSearchCountRepository;
import com.crp.qa.qaCore.service.inte.QaSearchCountService;
import com.crp.qa.qaCore.util.exception.QaSearchCountException;

@Service(value="qaSearchCountService")
@Transactional
public class QaSearchCountServiceImpl extends BaseServiceImpl<QaSearchCount> implements QaSearchCountService {
	
	@Autowired
	private QaSearchCountRepository qaSearchCountRepository;
	
	

	@Override
	public QaSearchCountDto findByDate(Date date) throws QaSearchCountException,NullPointerException {
		checkNull(date,"传入日期为null");
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(date);
		QaSearchCount t = qaSearchCountRepository.findByCountDate(dateStr);
		if(t != null ) {		
			try {
				QaSearchCountDto dto = new QaSearchCountDto();
				mapper.map(t, dto);
				return dto;
			}catch(MappingException e) {
				throw new QaSearchCountException("pojo 转 dto 失败",e);
			}
		}			
		return null;
	}

	@Override
	public List<QaSearchCountDto> findByDateDuring(Date from, Date to) throws QaSearchCountException,NullPointerException {
		checkNull(from,"传入开始日期为null");
		checkNull(to,"传入结束日期为null");
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fromStr = sdf.format(from);
		String toStr = sdf.format(to);
		List<QaSearchCount> tList = qaSearchCountRepository.findByCountDateGreaterThanEqualAndCountDateLessThanEqual(fromStr, toStr);
		List<QaSearchCountDto> dList = new ArrayList<QaSearchCountDto>();
		if(tList!=null && tList.size()>0) {
			try {
				dList = pojoToDto(QaSearchCountDto.class,tList);
			} catch (MappingException | InstantiationException | IllegalAccessException e) {
				throw new QaSearchCountException("pojo 转 dto 失败",e);
			}
		}
		return dList;
	}	

	@Override
	public QaSearchCountDto save(QaSearchCountDto dto) throws QaSearchCountException,NullPointerException {
		checkNull(dto,"传入对象为null");
		checkNull(dto.getCountDate(),"日期为空");
		checkNull(dto.getCountNumber(),"计数为空");
		if(dto.getCountId() != null) {
			throw new QaSearchCountException("传入对象已有主键");
		}else if(qaSearchCountRepository.existsByCountDate(dto.getCountDate())) {
			throw new QaSearchCountException("日期已存在");
		}
		
		try {
			QaSearchCount t = new QaSearchCount();
			mapper.map(dto, t);
			t = qaSearchCountRepository.saveAndFlush(t);
			mapper.map(t,dto);
		}catch(MappingException e) {
			throw new QaSearchCountException("pojo 转 dto 失败",e);
		}
		return dto;
	}

	@Override
	public QaSearchCountDto update(QaSearchCountDto dto) throws QaSearchCountException,NullPointerException {
		checkNull(dto,"传入对象对null");
		checkNull(dto.getCountId(),"主键为空");
		checkNull(dto.getCountDate(),"日期为空");
		checkNull(dto.getCountNumber(),"计数为空");
		
		if(!qaSearchCountRepository.existsById(dto.getCountId())) {
			throw new QaSearchCountException("传入对象主键在数据库中不存在");
		}else if(qaSearchCountRepository.existsByCountDateAndCountIdNot(dto.getCountDate(), dto.getCountId())) {
			throw new QaSearchCountException("要更新的日期已存在");
		}
		
		try {
			QaSearchCount t = new QaSearchCount();
			mapper.map(dto, t);
			t = qaSearchCountRepository.saveAndFlush(t);
			mapper.map(t,dto);
		}catch(MappingException e) {
			throw new QaSearchCountException("pojo 转 dto 失败",e);
		}
		return dto;
	}

	@Override
	public void delete(Integer id) throws QaSearchCountException,NullPointerException {
		checkNull(id,"id为空");
		qaSearchCountRepository.deleteById(id);
	}
}
