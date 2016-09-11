package com.tilisou.beans;
/**
 * 年级枚举
 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-10-8 下午10:12:15
 *
 */
public enum Grade {
	G_1{
		public String getName(){return "高一";}
	},G_2{
		public String getName(){return "高二";}
	},G_3{
		public String getName(){return "高三";}
	};
	public abstract String getName();
}