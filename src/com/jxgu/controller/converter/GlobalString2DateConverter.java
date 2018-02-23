/**
 * ������
 * Copyright��Copyright ��c�� 2017
 * Company���Ͼ�����
 * @author jxgu 
 * @version 1.0 2017��10��4��
 * @see history
 * 2017��10��4��
 */
package com.jxgu.controller.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * @author jxgu
 *
 *         �๦��������
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
