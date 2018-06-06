/**
 * huangyue
 * 2018年5月23日
 */
package com.crp.qa.qaCore.util.exception;

/**
 * 树异常
 * @author huangyue
 * @date 2018年5月23日 上午10:07:27
 * @ClassName QaTreeException
 */
public class QaTreeException extends Exception{

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
	public QaTreeException(String errorMsg) {
		super(errorMsg);
	}
	
	/**
	 * 构造函数
	 * @param errorMsg 错误信息
	 */
	public QaTreeException(String errorMsg,Throwable cause) {
		super(errorMsg,cause);
	}
	
	/**
	 * 构造函数
	 * @param errorMsg 错误信息
	 * @param errorCode 错误代码
	 */
	public QaTreeException(String errorMsg,String errorCode) {
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
