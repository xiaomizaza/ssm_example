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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jxgu
 *
 *         �๦��������
 *
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
	    HttpServletResponse response, Object handler, Exception ex) {

	CustomException customException = null;

	if (ex instanceof CustomException) {
	    customException = (CustomException) ex;
	} else {
	    customException = new CustomException("ϵͳ��������ϵϵͳ����Ա");
	}

	ModelAndView modelAndView = new ModelAndView();
	modelAndView.addObject("message", customException.getMessage());
	modelAndView.setViewName("error");

	return modelAndView;
    }

}
