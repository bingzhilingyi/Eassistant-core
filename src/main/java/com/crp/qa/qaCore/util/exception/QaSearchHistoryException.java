package com.crp.qa.qaCore.util.exception;

public class QaSearchHistoryException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 错误编码
	 */
	private String errorCode;

	/**
	 * 构造函数
	 * @param errorMsg 错误信息
	 */
	public QaSearchHistoryException(String errorMsg) {
		super(errorMsg);
	}
	
	/**
	 * 构造函数
	 * @param errorMsg 错误信息
	 */
	public QaSearchHistoryException(String errorMsg,Throwable cause) {
		super(errorMsg,cause);
	}
	
	/**
	 * 构造函数
	 * @param errorMsg 错误信息
	 * @param errorCode 错误代码
	 */
	public QaSearchHistoryException(String errorMsg,String errorCode) {
		super(errorMsg);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
