/**
 * huangyue
 * 2018年5月24日
 */
package com.crp.qa.qaCore.service.impl;

import java.util.List;
import java.util.Optional;

import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crp.qa.qaCore.domain.domain.QaPage;
import com.crp.qa.qaCore.domain.dto.QaPageDto;
import com.crp.qa.qaCore.repository.QaPageRepository;
import com.crp.qa.qaCore.service.inte.QaPageService;
import com.crp.qa.qaCore.util.exception.QaPageException;

/**
 * 知识页服务
 * @author huangyue
 * @date 2018年5月24日 下午3:53:44
 * @ClassName QaPageServiceImpl
 */
@Service(value="qaPageService")
@Transactional
public class QaPageServiceImpl extends BaseServiceImpl<QaPage> implements QaPageService{
	
	@Autowired
	QaPageRepository qaPageRepository;

	/* (non-Javadoc)
	 * @see com.crp.qa.qaCore.service.inte.QaPageService#findByTitle(java.lang.String)
	 */
	@Override
	public QaPageDto findByTitle(String title) throws QaPageException{
		if(title==null||title.length()==0) {
			throw new QaPageException("传入的title为空！");
		}
		QaPage page = qaPageRepository.findByTitle(title);
		if(page!=null) {
			try {
				QaPageDto pageDto = new QaPageDto();
				mapper.map(page, pageDto);
				return pageDto;
			} catch (MappingException e) {
				throw new QaPageException("pojo转dto失败",e);
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.crp.qa.qaCore.service.inte.QaPageService#findById(java.lang.Integer)
	 */
	@Override
	public QaPageDto findById(Integer id) throws QaPageException {
		if(id==null) {
			throw new QaPageException("传入id为空！");
		}
		Optional<QaPage> page = qaPageRepository.findById(id);
		if(page.isPresent()) {
			try {
				QaPageDto pageDto = new QaPageDto();
				mapper.map(page.get(), pageDto);
				return pageDto;
			} catch (MappingException e) {
				throw new QaPageException("pojo转dto失败",e);
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.crp.qa.qaCore.service.inte.QaPageService#findByTitleLike(java.lang.String)
	 */
	@Override
	public List<QaPageDto> findByTitleLike(String title) throws QaPageException {
		if(title==null||title.length()==0) {
			throw new QaPageException("传入的title为空！");
		}
		List<QaPage> pageList = qaPageRepository.findByTitleContaining(title);
		try {
			return pojoToDto(QaPageDto.class,pageList);
		} catch (IllegalAccessException | InstantiationException | MappingException e) {
			throw new QaPageException("pojo转dto失败",e);
		}
	}

}
