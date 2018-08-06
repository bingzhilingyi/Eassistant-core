package com.crp.qa.qaCore.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crp.qa.qaCore.domain.dto.QaSearchCountDto;
import com.crp.qa.qaCore.service.inte.QaSearchCountService;
import com.crp.qa.qaCore.util.transfer.QaBaseTransfer;

@RestController
@RequestMapping(path="/hiscount")
public class QaSearchCountController extends BaseController{

	@Resource(name="qaSearchCountService")
	private QaSearchCountService qaSearchCountService;
	
	@GetMapping(path="/findByDateDuring")
	public QaBaseTransfer findByDateDuring(@RequestParam(name="from")String from,@RequestParam(name="to")String to) {	
		QaBaseTransfer dto = new QaBaseTransfer("success","查询成功");
		try {
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date fromDate = sdf.parse(from);
			Date toDate = sdf.parse(to);
			List<QaSearchCountDto> l = qaSearchCountService.findByDateDuring(fromDate, toDate);
			dto.setContent(l);
		}catch(Exception e) {
			returnError(e,dto);
		}
		return dto;
	}
}
