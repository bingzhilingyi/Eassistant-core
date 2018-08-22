package com.crp.qa.qaCore.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crp.qa.qaCore.domain.dto.QaSearchHistoryDto;
import com.crp.qa.qaCore.service.inte.QaSearchHistoryService;
import com.crp.qa.qaCore.service.inte.QaTreeService;
import com.crp.qa.qaCore.util.exception.QaSearchHistoryException;
import com.crp.qa.qaCore.util.transfer.QaBaseTransfer;

@RestController
@RequestMapping(path="/history")
public class QaSearchHistoryController extends BaseController{
	
	@Resource(name="qaSearchHistoryService")
	QaSearchHistoryService qaSearchHistoryService;

	/**
	 * 查找查询历史中热度最高的
	 * @param rank
	 * @return
	 * @Date 2018年8月2日
	 * @author huangyue
	 */
	@GetMapping(path="/findTopRank")
	public QaBaseTransfer findTopRank(@RequestParam(name="rank")Integer rank) {
		QaBaseTransfer dto = new QaBaseTransfer("success","查询成功！");
		try {
			List<QaSearchHistoryDto> l = qaSearchHistoryService.findTopHistory(rank);
			dto.setContent(l);
		}catch(QaSearchHistoryException e) {
			returnError(e, dto);
		}
		return dto;
	}
	
	/**
	 * 记录传入的查询条件
	 * @param title
	 * @return
	 * @Date 2018年6月15日
	 * @author huangyue
	 */
	@PostMapping(path="/searchRecord")
	public QaBaseTransfer searchRecord(
			@RequestParam(value="title") String title,
			@RequestParam(value="noResult") Boolean noResult) {
		QaBaseTransfer dto = new QaBaseTransfer("success","记录成功！");
		try {
			qaSearchHistoryService.searchRecord(title,noResult);
		}catch (Exception e) {
			returnError(e,dto);
		}
		return dto;
	}
}
