package com.crp.qa.qaCore.domain.dto;

import java.io.Serializable;
import java.util.Date;

public class QaSearchHistoryDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer historyId;
	private String historyContent;
	private Integer rank;
	private Integer createdBy;
	private Date creationDate;
	private Integer lastUpdatedBy;
	private Date lastUpdateDate;
	private String attribute1;
	private String attribute2;
	private String attribute3;
	private String attribute4;
	private String attribute5;
	private String attribute6;
	private String attribute7;
	private String attribute8;
	private String attribute9;
	private String attribute10;
	private String isNoResult;
	private String isPageTitle;
	private String creationDateStr;
	
	public QaSearchHistoryDto() {}
	
	public QaSearchHistoryDto(String historyContent,String isNoResult,String isPageTitle) {
		this.historyContent = historyContent;
		this.isNoResult = isNoResult;
		this.isPageTitle = isPageTitle;
	}
	
	public Integer getHistoryId() {
		return historyId;
	}
	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}
	public String getHistoryContent() {
		return historyContent;
	}
	public void setHistoryContent(String historyContent) {
		this.historyContent = historyContent;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public String getAttribute1() {
		return attribute1;
	}
	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}
	public String getAttribute2() {
		return attribute2;
	}
	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}
	public String getAttribute3() {
		return attribute3;
	}
	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}
	public String getAttribute4() {
		return attribute4;
	}
	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}
	public String getAttribute5() {
		return attribute5;
	}
	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}
	public String getAttribute6() {
		return attribute6;
	}
	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}
	public String getAttribute7() {
		return attribute7;
	}
	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}
	public String getAttribute8() {
		return attribute8;
	}
	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}
	public String getAttribute9() {
		return attribute9;
	}
	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}
	public String getAttribute10() {
		return attribute10;
	}
	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}
	public String getIsNoResult() {
		return isNoResult;
	}
	public void setIsNoResult(String isNoResult) {
		this.isNoResult = isNoResult;
	}
	public String getIsPageTitle() {
		return isPageTitle;
	}
	public void setIsPageTitle(String isPageTitle) {
		this.isPageTitle = isPageTitle;
	}

	public String getCreationDateStr() {
		return creationDateStr;
	}

	public void setCreationDateStr(String creationDateStr) {
		this.creationDateStr = creationDateStr;
	}
	
	
}
