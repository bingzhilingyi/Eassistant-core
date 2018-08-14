package com.crp.qa.qaCore.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.crp.qa.qaCore.domain.dto.QaSearchNoResultDto;
import com.crp.qa.qaCore.service.inte.QaSearchNoResultService;
import com.crp.qa.qaCore.util.transfer.QaPagedDto;
import com.crp.qa.qaCore.util.transfer.QaPagedTransfer;

@RestController
@RequestMapping(path="/noresult")
public class QaSearchNoResultController extends BaseController{

	@Resource(name="qaSearchNoResultService")
	QaSearchNoResultService qaSearchNoResultService;
	
	@GetMapping(path="/findPagedAll")
	public QaPagedTransfer findPagedAll(
			@RequestParam(value="page") Integer page,
			@RequestParam(value="size") Integer size) {
		QaPagedTransfer dto = new QaPagedTransfer("success","查询成功");
		try {
			QaPagedDto<QaSearchNoResultDto> l = qaSearchNoResultService.findPagedAll(page, size);
			dto.setContent(l.getList());
			dto.setTotalElements(l.getTotalElements());
			dto.setTotalPages(l.getTotalPages());
		}catch (Exception e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	@GetMapping(path="/findByCreationDateBetween")
	public QaPagedTransfer findByCreationDateBetween(
			@RequestParam(value="from") @Nullable String from,
			@RequestParam(value="to") @Nullable String to,
			@RequestParam(value="page") @Nullable Integer page,
			@RequestParam(value="size") @Nullable Integer size) {
		QaPagedTransfer dto = new QaPagedTransfer("success","查询成功");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fromDate=null,toDate=null;
		try {
			if(from!=null && !from.trim().equals("")) {		
				fromDate = sdf.parse(from);
			}
			if(to!=null && !to.trim().equals("")) {
				toDate = sdf.parse(to);
			}
			QaPagedDto<QaSearchNoResultDto> l = qaSearchNoResultService.findByCreationDateBetween(fromDate,toDate,page, size);
			dto.setContent(l.getList());
			dto.setTotalElements(l.getTotalElements());
			dto.setTotalPages(l.getTotalPages());
		}catch (Exception e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	@PostMapping(path="/save")
	public QaPagedTransfer findPagedAll(@RequestParam(value="record") String record) {
		QaPagedTransfer dto = new QaPagedTransfer("success","查询成功");
		QaSearchNoResultDto d = JSONObject.parseObject(record, QaSearchNoResultDto.class);
		try {
			d = qaSearchNoResultService.save(d);
			dto.setContent(d);
		}catch (Exception e) {
			returnError(e,dto);
		}
		return dto;
	}
}
