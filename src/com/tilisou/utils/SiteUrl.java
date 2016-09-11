package com.tilisou.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * 站内URL 配置工具
 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-9-9 下午8:04:25
 *
 */
public class SiteUrl {
	private static Properties properties = new Properties();
	static{
		try {
			//加载配置文件：siteurl.properties
			properties.load(SiteUrl.class.getClassLoader().getResourceAsStream("siteurl.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取siteurl.properties配置文件中的站点Url
	 * 
	 * @param key
	 *            url对应的键值
	 * @return 匹配的url
	 */
	public static String readUrl(String key){		
		return (String)properties.get(key);
	}
}
