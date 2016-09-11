package com.tilisou.viewmodel;

import org.apache.commons.beanutils.Converter;

import com.tilisou.beans.Grade;

/**
 * �꼶ö�ٵ���������ת����
 * 
 * @author Administrator��<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-10-8 ����10:39:16
 * 
 */
public class GradeConverter implements Converter {

	@SuppressWarnings("rawtypes")
	public Object convert(Class clazz, Object value) {
		if (value == null || "".equals((String) value))
			return null;
		if (value instanceof Grade)
			return value;
		try {
			return Grade.valueOf((String) value);
		} catch (Exception e) {
		}
		return null;
	}
}
