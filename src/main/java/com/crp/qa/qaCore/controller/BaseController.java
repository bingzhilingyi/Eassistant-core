/**
 * huangyue
 * 2018年5月24日
 */
package com.crp.qa.qaCore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.crp.qa.qaCore.util.transfer.QaBaseTransfer;


/**
 * @author huangyue
 * @date 2018年5月24日 下午4:20:49
 * @ClassName BaseController
 */
@Component
public class BaseController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(QaTreeController.class);

	/**
	 * 返回错误信息并打印日志的通用方法
	 * @author huangyue
	 * @date 2018年5月15日 下午1:59:26
	 * @param e 异常
	 * @param dto 传输对象
	 */
	protected void returnError(Exception e,QaBaseTransfer dto) {
		LOGGER.error(e.getMessage(),e);
		dto.setStatus("failed");
		dto.setMessage(e.getMessage());
	}
}
