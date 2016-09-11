package com.tilisou.viewmodel;

import java.util.Arrays;

import org.apache.struts.upload.FormFile;

import com.tilisou.beans.Grade;
import com.tilisou.beans.Subject;

/**
 * ��ĿFormBean
 * 
 * @author Administrator��<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-10-8 ����10:41:29
 * 
 */
public class TimuForm extends BaseForm {

	private static final long serialVersionUID = -1527623670071823334L;
	private Integer[] ids;
	private Integer tId;	//��ĿId
	private String proSourceDesc;		//��ĿԴ��������ʽ�磺2015��ɽ���߿��Ŀ���ѧ��
	private String type;	//��Ŀ���ͣ���ѡ���⡢������
	private String problemContentText; 	//��Ŀ�����ı������OCRʶ�����������
	private String qPicUrl;	//��ĿͼƬ��ַ
	private String aPicUrl;	//��Ŀ�𰸽���ͼƬ��ַ
	private String pubDate; 	//��������ʱ��
	private Grade grade;	//�꼶
	private Subject subject;	//��Ŀ
	
	private String word;	//��Ŀ

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	private FormFile qPicFile;
	private FormFile aPicFile;
	
	/** ����ʽ **/
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
