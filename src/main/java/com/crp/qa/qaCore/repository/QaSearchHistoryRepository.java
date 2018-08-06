package com.crp.qa.qaCore.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crp.qa.qaCore.domain.pojo.QaSearchHistory;

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
}
