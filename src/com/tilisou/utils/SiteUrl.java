package com.tilisou.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * վ��URL ���ù���
 * @author Administrator��<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-9-9 ����8:04:25
 *
 */
public class SiteUrl {
	private static Properties properties = new Properties();
	static{
		try {
			//���������ļ���siteurl.properties
			properties.load(SiteUrl.class.getClassLoader().getResourceAsStream("siteurl.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡsiteurl.properties�����ļ��е�վ��Url
	 * 
	 * @param key
	 *            url��Ӧ�ļ�ֵ
	 * @return ƥ���url
	 */
	public static String readUrl(String key){		
		return (String)properties.get(key);
	}
}
