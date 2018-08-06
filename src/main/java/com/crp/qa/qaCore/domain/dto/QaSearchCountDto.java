package com.crp.qa.qaCore.domain.dto;

import java.io.Serializable;

public class QaSearchCountDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer countId;
	private String countDate;
	private Integer countNumber;
	
	public QaSearchCountDto() {
		
	};
	
	public QaSearchCountDto(String countDate,Integer countNumber) {
		this.countDate = countDate;
		this.countNumber = countNumber;
	}
	
	public Integer getCountId() {
		return countId;
	}
	public void setCountId(Integer countId) {
		this.countId = countId;
	}
	public String getCountDate() {
		return countDate;
	}
	public void setCountDate(String countDate) {
		this.countDate = countDate;
	}
	public Integer getCountNumber() {
		return countNumber;
	}
	public void setCountNumber(Integer countNumber) {
		this.countNumber = countNumber;
	}
	
	
}
