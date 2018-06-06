/**
 * huangyue
 * 2018年5月24日
 */
package com.crp.qa.qaCore.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crp.qa.qaCore.domain.dto.QaPageDto;
import com.crp.qa.qaCore.service.inte.QaPageService;
import com.crp.qa.qaCore.util.exception.QaPageException;
import com.crp.qa.qaCore.util.transfer.QaBaseTransfer;

/**
 * @author huangyue
 * @date 2018年5月24日 下午4:08:50
 * @ClassName QaPageController
 */
@RestController
@RequestMapping(path="/page")
public class QaPageController extends BaseController{
	
	@Resource(name="qaPageService")
	QaPageService qaPageService;
	
	/**
	 * 通过id获取知识页
	 * @author huangyue
	 * @date 2018年5月24日 下午4:30:20
	 * @param token
	 * @param id
	 * @return
	 */
	@GetMapping(path="/getById/{id}")
	public QaBaseTransfer findById(@PathVariable(value="id")Integer id) {
		QaBaseTransfer dto = new QaBaseTransfer("success","查询成功");
		try {
			QaPageDto d = qaPageService.findById(id);
			dto.setContent(d);
		} catch (QaPageException e) {
			returnError(e,dto);
		}
		return dto;
	}

	/**
	 * 通过title精确获取知识页
	 * @author huangyue
	 * @date 2018年5月24日 下午4:34:01
	 * @param token
	 * @param title
	 * @return
	 */
	@GetMapping(path="/getByTitle/{title}")
	public QaBaseTransfer findByTitle(@PathVariable(value="title")String title) {
		QaBaseTransfer dto = new QaBaseTransfer("success","查询成功");
		try {
			QaPageDto d = qaPageService.findByTitle(title);
			dto.setContent(d);
		} catch (QaPageException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	/**
	 * 通过title模糊获取知识页
	 * @author huangyue
	 * @date 2018年5月24日 下午4:34:01
	 * @param token
	 * @param title
	 * @return
	 */
	@GetMapping(path="/getByTitleLike/{title}")
	public QaBaseTransfer findByTitleLike(
			@RequestParam(value="token") String token,
			@PathVariable(value="title")String title) {
		QaBaseTransfer dto = new QaBaseTransfer("success","查询成功");
		try {
			List<QaPageDto> d = qaPageService.findByTitleLike(title);
			dto.setContent(d);
		} catch (QaPageException e) {
			returnError(e,dto);
		}
		return dto;
	}
}
