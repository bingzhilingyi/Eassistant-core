package com.crp.qa.qaCore.domain.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="qa_tree_keyword",schema="qa")
public class QaTreeKeyword {
	
	private Integer keywordId;
	private Integer treeId;
	private String keywordName;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="keyword_id")
	public Integer getKeywordId() {
		return keywordId;
	}
	public void setKeywordId(Integer keywordId) {
		this.keywordId = keywordId;
	}
	
	@Column(name="tree_id")
	public Integer getTreeId() {
		return treeId;
	}
	public void setTreeId(Integer treeId) {
		this.treeId = treeId;
	}
	
	@Column(name="keyword_name")
	public String getKeywordName() {
		return keywordName;
	}
	public void setKeywordName(String keywordName) {
		this.keywordName = keywordName;
	}
	
	

}
