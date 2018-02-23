/**
 * ������
 * Copyright��Copyright ��c�� 2017
 * Company���Ͼ�����
 * @author jxgu 
 * @version 1.0 2017��10��8��
 * @see history
 * 2017��10��8��
 */
package com.jxgu.customexception;

/**
 * @author jxgu
 *
 *         �๦��������
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
