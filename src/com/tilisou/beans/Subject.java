package com.tilisou.beans;
/**
 * ��Ŀö��
 * @author Administrator��<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-10-8 ����10:12:48
 *
 */
public enum Subject {
	YW{
		public String getName(){return "����";}
	},SX{
		public String getName(){return "��ѧ";}
	},YY{
		public String getName(){return "Ӣ��";}
	},WL{
		public String getName(){return "����";}
	},HX{
		public String getName(){return "��ѧ";}
	},SW{
		public String getName(){return "����";}
	},LS{
		public String getName(){return "��ʷ";}
	},DL{
		public String getName(){return "����";}
	},ZZ{
		public String getName(){return "����";}
	};
	public abstract String getName();
}