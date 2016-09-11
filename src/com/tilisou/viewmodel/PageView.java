package com.tilisou.viewmodel;

import java.util.List;

/**
 * ҳ���б�������ͼ
 * 
 * @author Administrator��<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-9-18 ����7:55:35
 * 
 * @param <T>
 *            �����࣬����ָ������ѯ��ʾ���������
 */
public class PageView<T> {
	/** ��ҳ���� **/
	private List<T> records;
	/** ҳ�뿪ʼ�����ͽ������� **/
	private PageIndex pageIndex;
	/** ��ҳ�� **/
	private long totalpage = 1;
	/** ÿҳ��ʾ��¼�� **/
	private int maxresult = 12;
	/** ��ǰҳ **/
	private int currentpage = 1;
	/** �ܼ�¼�� **/
	private long totalrecord;
	/** ҳ������ **/
	private int pagecode = 10;
	
	/**
	 * Ҫ��ȡ��¼�Ŀ�ʼ����
	 * @return
	 */
	public int getFirstResult() {
		return (this.currentpage-1)*this.maxresult;
	}
	public int getPagecode() {
		return pagecode;
	}

	public void setPagecode(int pagecode) {
		this.pagecode = pagecode;
	}

	public PageView(int maxresult, int currentpage) {
		this.maxresult = maxresult;
		this.currentpage = currentpage;
	}
	
	public void setQueryResult(QueryResult<T> qr){
		setTotalrecord(qr.getTotalrecord());
		setRecords(qr.getResultlist());
	}
	
	public long getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
		setTotalpage(this.totalrecord%this.maxresult==0? this.totalrecord/this.maxresult : this.totalrecord/this.maxresult+1);
	}
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}
	public PageIndex getPageIndex() {
		return pageIndex;
	}
	public long getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(long totalpage) {
		this.totalpage = totalpage;
		this.pageIndex = PageIndex.getPageIndex(pagecode, currentpage, totalpage);
	}
	public int getMaxresult() {
		return maxresult;
	}
	public int getCurrentpage() {
		return currentpage;
	}
}
