package com.tilisou.viewmodel;

/**
 * ҳ��ָʾ��
 * 
 * @author Administrator��<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-9-18 ����7:38:33 <br>
 *       ������ȷҳ�����ڵĽ���Ŀ�ʼ�ͽ�����¼������λ�á�
 */
public class PageIndex {
	/**
	 * ҳ���Ӧ�Ŀ�ʼ����
	 */
	private long startIndex;
	/**
	 * ҳ���Ӧ�Ľ�������
	 */
	private long endIndex;
	
	public PageIndex(long startIndex, long endIndex) {
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}
	public long getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(long startIndex) {
		this.startIndex = startIndex;
	}
	public long getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(long endIndex) {
		this.endIndex = endIndex;
	}
	
	/**
	 * ��̬�෽������ȡ��Ӧ��ҳ��ָʾ��
	 * @param viewpagecount �ɼ�ҳ����
	 * @param currentPage ��ǰҳ��
	 * @param totalpage ��ҳ��
	 * @return һ��ȷ�еİ���ָ��ҳ������ҳ��ָʾ��
	 */
	public static PageIndex getPageIndex(long viewpagecount, int currentPage, long totalpage){
			//���㿪ʼҳ��			
			long startpage = currentPage-(viewpagecount%2==0? viewpagecount/2-1 : viewpagecount/2);
			//�������ҳ��
			long endpage = currentPage+viewpagecount/2;
			if(startpage<1){
				startpage = 1;
				if(totalpage>=viewpagecount) endpage = viewpagecount;
				else endpage = totalpage;
			}
			if(endpage>totalpage){
				endpage = totalpage;
				if((endpage-viewpagecount)>0) startpage = endpage-viewpagecount+1;
				else startpage = 1;
			}
			//����ҳ�뿪ʼ�ͽ���λ�ã�����ҳ��ָʾ��
			return new PageIndex(startpage, endpage);		
	}
}
