/**
 * huangyue
 * 2018年5月23日
 */
package com.crp.qa.qaCore.domain.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 知识点层级表
 * @author huangyue
 * @date 2018年5月23日 上午9:01:48
 * @ClassName QaTree
 */
@Entity
@Table(name="qa_tree",schema="qa")
public class QaTree implements Serializable{

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
	private Integer like;
	private Integer dislike;
	
	
	private QaPage qaPage;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tree_id")
	public Integer getTreeId() {
		return treeId;
	}
	public void setTreeId(Integer treeId) {
		this.treeId = treeId;
	}
	
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name="ispage")
	public String getIsPage() {
		return isPage;
	}
	public void setIsPage(String isPage) {
		this.isPage = isPage;
	}
	
	@Column(name="parent_id")
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	@Column(name="created_by")
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name="creation_date")
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@Column(name="last_updated_by")
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	
	@Column(name="last_update_date")
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id")
	public QaPage getQaPage() {
		return qaPage;
	}
	public void setQaPage(QaPage qaPage) {
		this.qaPage = qaPage;
	}
	
	@Column(name="rank")
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
	@Column(name="domain")
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
//	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
//	@JoinColumn(name="tree_id",referencedColumnName="tree_id")
//	public Set<QaTreeKeyword> getQaTreeKeyword() {
//		return qaTreeKeyword;
//	}
//	public void setQaTreeKeyword(Set<QaTreeKeyword> qaTreeKeyword) {
//		this.qaTreeKeyword = qaTreeKeyword;
//	}
//	
	@Column(name="page_id",insertable=false, updatable=false)
	public Integer getPageId() {
		return pageId;
	}
	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}
	
	@Column(name="label_1",length=200)
	public String getLabel1() {
		return label1;
	}
	public void setLabel1(String label1) {
		this.label1 = label1;
	}
	@Column(name="label_2",length=200)
	public String getLabel2() {
		return label2;
	}
	public void setLabel2(String label2) {
		this.label2 = label2;
	}
	@Column(name="label_3",length=200)
	public String getLabel3() {
		return label3;
	}
	public void setLabel3(String label3) {
		this.label3 = label3;
	}
	@Column(name="label_4",length=200)
	public String getLabel4() {
		return label4;
	}
	public void setLabel4(String label4) {
		this.label4 = label4;
	}
	@Column(name="label_5",length=200)
	public String getLabel5() {
		return label5;
	}
	public void setLabel5(String label5) {
		this.label5 = label5;
	}
	@Column(name="_like")
	public Integer getLike() {
		return like;
	}
	public void setLike(Integer like) {
		this.like = like;
	}
	@Column(name="dislike")
	public Integer getDislike() {
		return dislike;
	}
	public void setDislike(Integer dislike) {
		this.dislike = dislike;
	}
	
	
}
