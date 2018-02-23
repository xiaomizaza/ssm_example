/**
 * 描述：
 * Copyright：Copyright （c） 2017
 * Company：南京银行
 * @author jxgu 
 * @version 1.0 2017年10月8日
 * @see history
 * 2017年10月8日
 */
package com.jxgu.customexception;

/**
 * @author jxgu
 *
 *         类功能描述：
 *
 */
public class CustomException extends Exception {

    private static final long serialVersionUID = -3155917496820056480L;

    private String message;

    public CustomException(String message) {
	super(message);
	this.message = message;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

}
