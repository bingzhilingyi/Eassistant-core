package com.crp.qa.qaCore.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.crp.qa.qaCore.domain.pojo.QaSearchNoResult;

/**
 * 查询无结果记录
 * @author huangyue
 * @Date 2018年8月14日
 * @author huangyue
 */
public interface QaSearchNoResultRepository extends JpaRepository<QaSearchNoResult,Integer>{

	public Page<QaSearchNoResult> findByCreationDateBetween(Date from,Date to,Pageable pageable);
	
	public Page<QaSearchNoResult> findByCreationDateLessThanEqual(Date to,Pageable pageable);
	
	public Page<QaSearchNoResult> findByCreationDateGreaterThanEqual(Date from,Pageable pageable);
}
