package com.tilisou.viewmodel;

/**
 * 页码指示器
 * 
 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-9-18 下午7:38:33 <br>
 *       用于明确页码所在的结果的开始和结束记录的索引位置。
 */
public class PageIndex {
	/**
	 * 页码对应的开始索引
	 */
	private long startIndex;
	/**
	 * 页码对应的结束索引
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
	 * 静态类方法：获取对应的页码指示器
	 * @param viewpagecount 可见页码数
	 * @param currentPage 当前页码
	 * @param totalpage 总页数
	 * @return 一个确切的包含指定页码数的页码指示器
	 */
	public static PageIndex getPageIndex(long viewpagecount, int currentPage, long totalpage){
			//计算开始页码			
			long startpage = currentPage-(viewpagecount%2==0? viewpagecount/2-1 : viewpagecount/2);
			//计算结束页码
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
			//根据页码开始和结束位置，构造页码指示器
			return new PageIndex(startpage, endpage);		
	}
}
