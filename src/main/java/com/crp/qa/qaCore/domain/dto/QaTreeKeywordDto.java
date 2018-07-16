package com.crp.qa.qaCore.domain.dto;

import java.io.Serializable;

public class QaTreeKeywordDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer keywordId;
	private Integer treeId;
	private String keywordName;
	
	public Integer getKeywordId() {
		return keywordId;
	}
	public void setKeywordId(Integer keywordId) {
		this.keywordId = keywordId;
	}
	public Integer getTreeId() {
		return treeId;
	}
	public void setTreeId(Integer treeId) {
		this.treeId = treeId;
	}
	public String getKeywordName() {
		return keywordName;
	}
	public void setKeywordName(String keywordName) {
		this.keywordName = keywordName;
	}
	
	

}
