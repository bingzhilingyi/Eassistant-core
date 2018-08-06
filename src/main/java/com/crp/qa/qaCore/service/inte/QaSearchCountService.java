package com.crp.qa.qaCore.service.inte;

import java.util.Date;
import java.util.List;

import com.crp.qa.qaCore.domain.dto.QaSearchCountDto;
import com.crp.qa.qaCore.domain.pojo.QaSearchCount;
import com.crp.qa.qaCore.util.exception.QaSearchCountException;

public interface QaSearchCountService extends BaseService<QaSearchCount>{
	
	/**
	 * 根据日期精确查找当日记录
	 * @param date
	 * @return
	 * @throws QaSearchCountException
	 * @Date 2018年8月2日
	 * @author huangyue
	 */
	public QaSearchCountDto findByDate(Date date) throws QaSearchCountException,NullPointerException;
	
	
	/**
	 * 根据日期段查询一段时间内的记录
	 * @param from
	 * @param to
	 * @return
	 * @throws QaSearchCountException
	 * @Date 2018年8月2日
	 * @author huangyue
	 */
	public List<QaSearchCountDto> findByDateDuring(Date from,Date to) throws QaSearchCountException,NullPointerException;

	/**
	 * 保存
	 * @param dto
	 * @return
	 * @throws QaSearchCountException
	 * @Date 2018年8月2日
	 * @author huangyue
	 */
	public QaSearchCountDto save(QaSearchCountDto dto) throws QaSearchCountException,NullPointerException;
	
	/**
	 * 更新
	 * @param dto
	 * @return
	 * @throws QaSearchCountException
	 * @Date 2018年8月2日
	 * @author huangyue
	 */
	public QaSearchCountDto update(QaSearchCountDto dto) throws QaSearchCountException,NullPointerException;
	
	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws QaSearchCountException
	 * @Date 2018年8月2日
	 * @author huangyue
	 */
	public void delete(Integer id) throws QaSearchCountException,NullPointerException;

}
