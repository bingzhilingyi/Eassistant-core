/**
 * huangyue
 * 2018年5月31日
 */
package com.crp.qa.qaCore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crp.qa.qaCore.util.transfer.QaBaseTransfer;



/**
 * 专门返回错误的controller
 * @author huangyue
 * @date 2018年5月31日 下午7:23:25
 * @ClassName QaErrorController
 */
@RestController
@RequestMapping(path="/error")
public class QaErrorController {

	/**
	 * 当token验证错误时的处理方法
	 * @author huangyue
	 * @date 2018年5月29日 上午10:26:47
	 * @return
	 */
	@RequestMapping(path="/notoken")
	public QaBaseTransfer noToken() {
		QaBaseTransfer dto = new QaBaseTransfer("failed","token错误或已过期！");
		return dto;
	}
}
