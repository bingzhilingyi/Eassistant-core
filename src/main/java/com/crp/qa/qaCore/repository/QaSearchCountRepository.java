package com.crp.qa.qaCore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crp.qa.qaCore.domain.pojo.QaSearchCount;

public interface QaSearchCountRepository extends JpaRepository<QaSearchCount,Integer> {

	public QaSearchCount findByCountDate(String date);
	
	public List<QaSearchCount> findByCountDateGreaterThanEqualAndCountDateLessThanEqual(String from,String to);
	
	public boolean existsByCountDate(String date);
	
	public boolean existsByCountDateAndCountIdNot(String date,Integer id);
}
