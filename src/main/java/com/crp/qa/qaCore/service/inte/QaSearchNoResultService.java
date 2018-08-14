package com.crp.qa.qaCore.service.inte;

import java.util.Date;

import com.crp.qa.qaCore.domain.dto.QaSearchNoResultDto;
import com.crp.qa.qaCore.util.exception.QaSearchNoResultException;
import com.crp.qa.qaCore.util.transfer.QaPagedDto;

public interface QaSearchNoResultService {

	/**
	 * 保存一条记录
	 * @param dto
	 * @return
	 * @throws QaSearchNoResultException
	 * @Date 2018年8月13日
	 * @author huangyue
	 */
	public QaSearchNoResultDto save(QaSearchNoResultDto dto) throws QaSearchNoResultException,NullPointerException;
	
	/**
	 * 查找分页的所有数据
	 * @param page
	 * @param size
	 * @return
	 * @throws QaSearchNoResultException
	 * @Date 2018年8月13日
	 * @author huangyue
	 */
	public QaPagedDto<QaSearchNoResultDto> findPagedAll(Integer page,Integer size)throws QaSearchNoResultException;
	
	/**
	 * 查找指定日期内的记录
	 * @param from
	 * @param to
	 * @param page
	 * @param size
	 * @return
	 * @throws QaSearchNoResultException
	 * @Date 2018年8月14日
	 * @author huangyue
	 */
	public QaPagedDto<QaSearchNoResultDto> findByCreationDateBetween(Date from,Date to,Integer page,Integer size)throws QaSearchNoResultException;
}
