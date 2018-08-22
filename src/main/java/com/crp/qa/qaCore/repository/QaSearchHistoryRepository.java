package com.crp.qa.qaCore.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crp.qa.qaCore.domain.pojo.QaSearchHistory;
import com.crp.qa.qaCore.domain.pojo.QaSearchNoResult;

/**
 * 
 * @author huangyue
 * @Date 2018年6月27日
 * @author huangyue
 */
public interface QaSearchHistoryRepository extends JpaRepository<QaSearchHistory,Integer>{

	public QaSearchHistory findByHistoryContent(String title);
	
	@Query(value = "SELECT  qt FROM QaSearchHistory qt ORDER BY  qt.rank desc,qt.historyId")
	public List<QaSearchHistory> findTopNByOrderByRank(Integer rank,Pageable pageable);
	
	public Page<QaSearchHistory> findByIsNoResult(String isNoResult,Pageable pageable);
	
	public Page<QaSearchHistory> findByIsNoResultAndCreationDateBetween(String isNoResult,Date from,Date to,Pageable pageable);
	
	public Page<QaSearchHistory> findByIsNoResultAndCreationDateLessThanEqual(String isNoResult,Date to,Pageable pageable);
	
	public Page<QaSearchHistory> findByIsNoResultAndCreationDateGreaterThanEqual(String isNoResult,Date from,Pageable pageable);
}
