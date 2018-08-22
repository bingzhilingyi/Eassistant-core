package com.crp.qa.qaCore.domain.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="qa_search_count",schema="qa")
public class QaSearchCount {

	private Integer countId;
	private String countDate;
	private Integer countNumber;
	private Date lastUpdateDate;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="count_id")
	public Integer getCountId() {
		return countId;
	}
	public void setCountId(Integer countId) {
		this.countId = countId;
	}
	@Column(name="count_date")
	public String getCountDate() {
		return countDate;
	}
	public void setCountDate(String countDate) {
		this.countDate = countDate;
	}
	@Column(name="count_number")
	public Integer getCountNumber() {
		return countNumber;
	}
	public void setCountNumber(Integer countNumber) {
		this.countNumber = countNumber;
	}
	@Column(name="last_update_date")
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
	
}
