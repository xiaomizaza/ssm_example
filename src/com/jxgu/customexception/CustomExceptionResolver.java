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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jxgu
 *
 *         类功能描述：
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
	    customException = new CustomException("系统错误，请联系系统管理员");
	}

	ModelAndView modelAndView = new ModelAndView();
	modelAndView.addObject("message", customException.getMessage());
	modelAndView.setViewName("error");

	return modelAndView;
    }

}
