/**
 * huangyue
 * 2018年5月24日
 */
package com.crp.qa.qaCore.service.inte;

import java.util.List;

import com.crp.qa.qaCore.domain.domain.QaPage;
import com.crp.qa.qaCore.domain.dto.QaPageDto;
import com.crp.qa.qaCore.util.exception.QaPageException;

/**
 * @author huangyue
 * @date 2018年5月24日 下午3:52:57
 * @ClassName QaPageService
 */
public interface QaPageService extends BaseService<QaPage>{

	/**
	 * 根据title精确查询知识页
	 * @author huangyue
	 * @date 2018年5月24日 下午3:56:12
	 * @param title
	 * @return
	 */
	public QaPageDto findByTitle(String title) throws QaPageException;
	
	/**
	 * 根据title模糊查询知识页
	 * @author huangyue
	 * @date 2018年5月24日 下午4:36:24
	 * @param title
	 * @return
	 * @throws QaPageException
	 */
	public List<QaPageDto> findByTitleLike(String title) throws QaPageException;
	
	/**
	 * 根据id查询知识页
	 * @author huangyue
	 * @date 2018年5月24日 下午4:11:29
	 * @param id
	 * @return
	 * @throws QaPageException
	 */
	public QaPageDto findById(Integer id) throws QaPageException;
	
}
