/**
 * 描述：
 * Copyright：Copyright （c） 2017
 * Company：南京银行
 * @author jxgu 
 * @version 1.0 2017年10月4日
 * @see history
 * 2017年10月4日
 */
package com.jxgu.controller.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * @author jxgu
 *
 *         类功能描述：
 *
 */
public class GlobalString2DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {

	try {
	    Date date = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss")
		    .parse(source);
	    return date;
	} catch (ParseException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

}
