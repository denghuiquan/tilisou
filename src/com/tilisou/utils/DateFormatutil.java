package com.tilisou.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期字符串格式化工具
 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-10-8 下午10:21:41
 *
 */
public class DateFormatutil {
	static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	/**
	 * 获得日期格式为：2012-11-22 11:12:23 的字符串
	 * @param date
	 * @return
	 */
	public static String getDate(Date date) {
		return dateFormat.format(date);
	}
}
