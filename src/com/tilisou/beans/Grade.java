package com.tilisou.beans;
/**
 * �꼶ö��
 * @author Administrator��<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-10-8 ����10:12:15
 *
 */
public enum Grade {
	G_1{
		public String getName(){return "��һ";}
	},G_2{
		public String getName(){return "�߶�";}
	},G_3{
		public String getName(){return "����";}
	};
	public abstract String getName();
}