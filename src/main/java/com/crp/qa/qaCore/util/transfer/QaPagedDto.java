/**
 * huangyue
 * 2018年5月15日
 */
package com.crp.qa.qaCore.util.transfer;

import java.util.List;

/**
 * 用于传输分页的数据对象
 * @author huangyue
 * @date 2018年5月15日 下午1:46:27
 * @ClassName QaPageDto
 */
public class QaPagedDto<T> {

	private List<T> list;
	private Long totalElements;
	private Integer totalPages;
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
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
	
	
	
}
