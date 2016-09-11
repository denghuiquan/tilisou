package com.tilisou.viewmodel;

import org.apache.struts.upload.FormFile;

/**
 * 题目检索的FormBean
 * 
 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-10-8 下午10:42:17
 * 
 */
public class TimuQueryForm extends BaseForm {
	
	private static final long serialVersionUID = 8907683979617682299L;
	
	private String word; // 关键词
	
	private FormFile imagefile; // 要检索题目的图片

	public FormFile getImagefile() {
		return imagefile;
	}

	public void setImagefile(FormFile imagefile) {
		this.imagefile = imagefile;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
}
