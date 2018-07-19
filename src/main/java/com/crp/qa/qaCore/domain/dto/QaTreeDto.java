/**
 * huangyue
 * 2018年5月23日
 */
package com.crp.qa.qaCore.domain.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author huangyue
 * @date 2018年5月23日 上午9:13:52
 * @ClassName QaTreeDto
 */
public class QaTreeDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer treeId;
	private String title;
	private String isPage;
	private Integer parentId;
	private Integer rank;
	private String domain;
	private Integer createdBy;
	private Date creationDate;
	private Integer lastUpdatedBy;
	private Date lastUpdateDate;
	private String label1;
	private String label2;
	private String label3;
	private String label4;
	private String label5;
	private Integer pageId;
	private QaPageDto qaPage; //该节点的知识页
	private Set<QaTreeSimpleDto> child; //该节点的子集
	
	public Integer getTreeId() {
		return treeId;
	}
	public void setTreeId(Integer treeId) {
		this.treeId = treeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIsPage() {
		return isPage;
	}
	public void setIsPage(String isPage) {
		this.isPage = isPage;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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
	
	public QaPageDto getQaPage() {
		return qaPage;
	}
	public void setQaPage(QaPageDto qaPage) {
		this.qaPage = qaPage;
	}
	public Set<QaTreeSimpleDto> getChild() {
		return child;
	}
	public void setChild(Set<QaTreeSimpleDto> child) {
		this.child = child;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public Integer getPageId() {
		return pageId;
	}
	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}
	public String getLabel1() {
		return label1;
	}
	public void setLabel1(String label1) {
		this.label1 = label1;
	}
	public String getLabel2() {
		return label2;
	}
	public void setLabel2(String label2) {
		this.label2 = label2;
	}
	public String getLabel3() {
		return label3;
	}
	public void setLabel3(String label3) {
		this.label3 = label3;
	}
	public String getLabel4() {
		return label4;
	}
	public void setLabel4(String label4) {
		this.label4 = label4;
	}
	public String getLabel5() {
		return label5;
	}
	public void setLabel5(String label5) {
		this.label5 = label5;
	}
	
	
}
