package com.tilisou.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.compass.annotations.Index;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.Store;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * ��Ŀʵ����
 * 
 * @author Administrator��<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-9-28 ����5:04:27 
 * <br/>���ڱ�����Ŀ����ģ�͵�ʵ����
 * <br/>ע��Ϊ@Searchable�ɼ�����@Cache�軺�����
 */
@Entity
@Searchable
@Cache(region="com.tilisou.beans.Timu",usage=CacheConcurrencyStrategy.READ_WRITE)
public class Timu implements Serializable {

	private static final long serialVersionUID = -8874162806254525542L;
	private Integer tId; // ��ĿId
	private String proSourceDesc; // ��ĿԴ��������ʽ�磺2015��ɽ���߿��Ŀ���ѧ��
	private String type; // ��Ŀ���ͣ���ѡ���⡢������
	private String problemContentText; // ��Ŀ�����ı������OCRʶ�����������
	private String qPicUrl; // ��ĿͼƬ��ַ
	private String aPicUrl; // ��Ŀ�𰸽���ͼƬ��ַ
	private String pubDate; // ��������ʱ��
	private Grade grade; // �꼶
	private Subject subject; // ��Ŀ

	public Timu() {
		super();
	}

	/**
	 * @param proSourceDesc
	 *            ��ĿԴ��������ʽ�磺2015��ɽ���߿��Ŀ���ѧ��
	 * @param type
	 *            ��Ŀ���ͣ���ѡ���⡢������
	 * @param problemContentText
	 *            ��Ŀ�����ı������OCRʶ�����������
	 * @param qPicUrl
	 *            ��ĿͼƬ��ַ
	 * @param aPicUrl
	 *            ��Ŀ�𰸽���ͼƬ��ַ
	 * @param pubDate
	 *            ��������ʱ��
	 * @param grade
	 *            �꼶
	 * @param subject
	 *            ��Ŀ
	 */
	public Timu(String proSourceDesc, String type, String problemContentText,
			String qPicUrl, String aPicUrl, String pubDate, Grade grade,
			Subject subject) {
		super();
		this.proSourceDesc = proSourceDesc;
		this.type = type;
		this.problemContentText = problemContentText;
		this.qPicUrl = qPicUrl;
		this.aPicUrl = aPicUrl;
		this.pubDate = pubDate;
		this.grade = grade;
		this.subject = subject;
	}

	@Id
	@GeneratedValue
	@SearchableId
	public Integer gettId() {
		return tId;
	}

	public void settId(Integer tId) {
		this.tId = tId;
	}

	@Column(nullable = true,length = 300)
	@SearchableProperty(boost = 2L, name = "proSourceDesc", index = Index.ANALYZED, store = Store.YES)
	public String getProSourceDesc() {
		return proSourceDesc;
	}

	public void setProSourceDesc(String proSourceDesc) {
		this.proSourceDesc = proSourceDesc;
	}

	@Column(nullable = false,length = 20)
	@SearchableProperty(name = "type", index = Index.NOT_ANALYZED, store = Store.YES)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(nullable = false, length = 1000)
	@SearchableProperty(boost = 3L, name = "problemContentText", index = Index.ANALYZED, store = Store.YES)
	public String getProblemContentText() {
		return problemContentText;
	}

	public void setProblemContentText(String problemContentText) {
		this.problemContentText = problemContentText;
	}

	@Column(nullable = false,length=255)
	@SearchableProperty(name="qPicUrl",index = Index.NOT_ANALYZED,store=Store.YES)
	public String getqPicUrl() {
		return qPicUrl;
	}

	public void setqPicUrl(String qPicUrl) {
		this.qPicUrl = qPicUrl;
	}

	@Column(nullable = false,length = 255)
	@SearchableProperty(name="aPicUrl",index = Index.NOT_ANALYZED,store=Store.YES)
	public String getaPicUrl() {
		return aPicUrl;
	}

	public void setaPicUrl(String aPicUrl) {
		this.aPicUrl = aPicUrl;
	}

	@Column(nullable = false, length = 40)
	@SearchableProperty(name="pubDate", index = Index.NOT_ANALYZED,store=Store.YES)
	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false,length = 6)
	@SearchableProperty(index = Index.NOT_ANALYZED, name = "grade", store = Store.YES)
	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false,length = 6)
	@SearchableProperty(index = Index.NOT_ANALYZED, name = "subject", store = Store.YES)
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tId == null) ? 0 : tId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Timu other = (Timu) obj;
		if (tId == null) {
			if (other.tId != null)
				return false;
		} else if (!tId.equals(other.tId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Timu [tId=" + tId + ", proSourceDesc=" + proSourceDesc
				+ ", type=" + type + ", problemContentText="
				+ problemContentText + ", qPicUrl=" + qPicUrl + ", aPicUrl="
				+ aPicUrl + ", pubDate=" + pubDate + ", grade=" + grade
				+ ", subject=" + subject + "]";
	}	
	
}
