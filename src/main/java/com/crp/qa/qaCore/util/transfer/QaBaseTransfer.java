/**
 * huangyue
 * 2018年5月14日
 */
package com.crp.qa.qaCore.util.transfer;

import java.io.Serializable;

/**
 * @author huangyue
 * @date 2018年5月14日 下午2:53:53
 * @ClassName BaseTransferDto
 */
public class QaBaseTransfer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String status; //状态
	private String message; //返回的信息
	private String token; //token
	private Object content; //传输的内容
	
	/**
	 * 构造方法
	 */
	public QaBaseTransfer() {
		
	}
	/**
	 * 构造方法
	 * @param status 返回状态
	 * @param message 返回信息
	 */
	public QaBaseTransfer(String status,String message) {
		this.status = status;
		this.message = message;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	
}
