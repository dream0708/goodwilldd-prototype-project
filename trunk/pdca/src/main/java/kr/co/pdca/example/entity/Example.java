package kr.co.pdca.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import kr.co.pdca.core.annotation.MobilePhone;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.hibernate.validator.constraints.Email;

@JsonAutoDetect
@XmlRootElement
@Entity
@Table(name = "TB_EXAMPLE")
public class Example {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long seq;

	@Column(name = "username", nullable = false)
	String username;

	@Email
	@Column(name = "email", nullable = false)
	String email;

	@MobilePhone
	@Column(name = "mobilePhone", nullable = false)
	String mobilePhone;

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SIMPLE_STYLE);
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
}
