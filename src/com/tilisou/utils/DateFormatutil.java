package com.tilisou.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * �����ַ�����ʽ������
 * @author Administrator��<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-10-8 ����10:21:41
 *
 */
public class DateFormatutil {
	static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	/**
	 * ������ڸ�ʽΪ��2012-11-22 11:12:23 ���ַ���
	 * @param date
	 * @return
	 */
	public static String getDate(Date date) {
		return dateFormat.format(date);
	}
}
