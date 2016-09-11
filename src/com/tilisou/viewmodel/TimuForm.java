package com.tilisou.viewmodel;

import java.util.Arrays;

import org.apache.struts.upload.FormFile;

import com.tilisou.beans.Grade;
import com.tilisou.beans.Subject;

/**
 * 题目FormBean
 * 
 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-10-8 下午10:41:29
 * 
 */
public class TimuForm extends BaseForm {

	private static final long serialVersionUID = -1527623670071823334L;
	private Integer[] ids;
	private Integer tId;	//题目Id
	private String proSourceDesc;		//题目源声明：格式如：2015年山东高考文科数学题
	private String type;	//题目类型：如选择题、填空题等
	private String problemContentText; 	//题目内容文本：存放OCR识别的文字内容
	private String qPicUrl;	//题目图片地址
	private String aPicUrl;	//题目答案解析图片地址
	private String pubDate; 	//发布更新时间
	private Grade grade;	//年级
	private Subject subject;	//科目
	
	private String word;	//科目

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	private FormFile qPicFile;
	private FormFile aPicFile;
	
	/** 排序方式 **/
	private String sort;
	
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public FormFile getqPicFile() {
		return qPicFile;
	}

	public void setqPicFile(FormFile qPicFile) {
		this.qPicFile = qPicFile;
	}

	public FormFile getaPicFile() {
		return aPicFile;
	}

	public void setaPicFile(FormFile aPicFile) {
		this.aPicFile = aPicFile;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	

	public Integer gettId() {
		return tId;
	}

	public void settId(Integer tId) {
		this.tId = tId;
	}

	public String getProSourceDesc() {
		return proSourceDesc;
	}

	public void setProSourceDesc(String proSourceDesc) {
		this.proSourceDesc = proSourceDesc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProblemContentText() {
		return problemContentText;
	}

	public void setProblemContentText(String problemContentText) {
		this.problemContentText = problemContentText;
	}

	public String getqPicUrl() {
		return qPicUrl;
	}

	public void setqPicUrl(String qPicUrl) {
		this.qPicUrl = qPicUrl;
	}

	public String getaPicUrl() {
		return aPicUrl;
	}

	public void setaPicUrl(String aPicUrl) {
		this.aPicUrl = aPicUrl;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade ;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "TimuForm [ids=" + Arrays.toString(ids) + ", tId=" + tId
				+ ", proSourceDesc=" + proSourceDesc + ", type=" + type
				+ ", problemContentText=" + problemContentText + ", qPicUrl="
				+ qPicUrl + ", aPicUrl=" + aPicUrl + ", pubDate=" + pubDate
				+ ", grade=" + grade + ", subject=" + subject + ", qPicFile="
				+ qPicFile + ", aPicFile=" + aPicFile + ", sort=" + sort + "]";
	}
	
	
}
