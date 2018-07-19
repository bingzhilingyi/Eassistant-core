package com.crp.qa.qaCore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crp.qa.qaCore.domain.pojo.QaSearchHistory;

/**
 * 
 * @author huangyue
 * @Date 2018年6月27日
 * @author huangyue
 */
public interface QaSearchHistoryRepository extends JpaRepository<QaSearchHistory,Integer>{

	public QaSearchHistory findByHistoryContent(String title);
}
