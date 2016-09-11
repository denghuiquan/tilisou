package com.tilisou.viewmodel;

import org.apache.commons.beanutils.Converter;

import com.tilisou.beans.Subject;
/**
 * 科目枚举的类型数据转换器
 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-10-8 下午10:40:39
 *
 */
public class SubjectConverter implements Converter {

	@SuppressWarnings("rawtypes")
	public Object convert(Class clazz, Object value) {
		if (value == null || "".equals((String) value))
			return null;
		if (value instanceof Subject)
			return value;
		try {
			return Subject.valueOf((String) value);
		} catch (Exception e) {
		}
		return null;
	}
}
