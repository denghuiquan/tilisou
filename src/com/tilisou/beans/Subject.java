package com.tilisou.beans;
/**
 * 科目枚举
 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-10-8 下午10:12:48
 *
 */
public enum Subject {
	YW{
		public String getName(){return "语文";}
	},SX{
		public String getName(){return "数学";}
	},YY{
		public String getName(){return "英语";}
	},WL{
		public String getName(){return "物理";}
	},HX{
		public String getName(){return "化学";}
	},SW{
		public String getName(){return "生物";}
	},LS{
		public String getName(){return "历史";}
	},DL{
		public String getName(){return "地理";}
	},ZZ{
		public String getName(){return "政治";}
	};
	public abstract String getName();
}