package com.tilisou.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/**
 * ����Աʵ����
 * @author Administrator��<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-10-8 ����10:11:14
 *
 */
@Entity
public class Admin implements Serializable {
	private static final long serialVersionUID = -6133396807713979395L;
	/** ����ԱId **/
	private Integer aId;
	/** �û��� **/
	private String name;
	/** �������� **/
	private String password;
	/** �칫�������� **/
	private String email;
	/** ��¼�ܴ��� **/
	private Integer countlogin = 0;
	/** �ϴε�¼ʱ�� **/
	private Date lastLogindate;

	@Id
	@GeneratedValue
	public Integer getaId() {
		return aId;
	}

	public void setaId(Integer aId) {
		this.aId = aId;
	}

	@Column(length = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 60, nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(length = 50, nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(nullable = false)
	public Integer getCountlogin() {
		return countlogin;
	}

	public void setCountlogin(Integer countlogin) {
		this.countlogin = countlogin;
	}

	@Column(nullable = true)
	public Date getLastLogindate() {
		return lastLogindate;
	}

	public void setLastLogindate(Date lastLogindate) {
		this.lastLogindate = lastLogindate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aId == null) ? 0 : aId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Admin other = (Admin) obj;
		if (aId == null) {
			if (other.aId != null)
				return false;
		} else if (!aId.equals(other.aId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}	

}
