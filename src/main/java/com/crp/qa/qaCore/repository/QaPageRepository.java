/**
 * huangyue
 * 2018年5月24日
 */
package com.crp.qa.qaCore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crp.qa.qaCore.domain.pojo.QaPage;

/**
 * @author huangyue
 * @date 2018年5月24日 下午3:45:54
 * @ClassName QaPageRepository
 */
public interface QaPageRepository extends JpaRepository<QaPage,Integer>{

	/**
	 * 根据标题精确查找知识页
	 * @author huangyue
	 * @date 2018年5月24日 下午3:51:52
	 * @param title
	 * @return
	 */
	public QaPage findByTitle(String title);
	
	/**
	 * 根据标题模糊查找知识页
	 * @author huangyue
	 * @date 2018年5月24日 下午4:35:01
	 * @param title
	 * @return
	 */
	public List<QaPage> findByTitleContaining(String title);
}
