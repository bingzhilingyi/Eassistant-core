/**
 * huangyue
 * 2018年5月15日
 */
package com.crp.qa.qaCore.util.transfer;

import java.util.List;

import com.crp.qa.qaCore.domain.dto.QaTreeSimpleDto;

/**
 * 返回给请求方的对象，列表，分页
 * @author huangyue
 * @date 2018年5月15日 下午2:07:13
 * @ClassName PagedTransferDto
 */
public class QaPagedTransfer extends QaBaseTransfer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long totalElements; //总数
	private Integer totalPages; //总页数
	//private List<QaTreeSimpleDto> ct;
	
	/**
	 * 构造方法
	 * @param status
	 * @param message
	 */
	public QaPagedTransfer(String status,String message) {
		super(status,message);
	}
	
	public QaPagedTransfer() {
		super();
	}
	
	public Long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

//	public List<QaTreeSimpleDto> getCt() {
//		return ct;
//	}
//
//	public void setCt(List<QaTreeSimpleDto> ct) {
//		this.ct = ct;
//	}
//	
//	
	
}
