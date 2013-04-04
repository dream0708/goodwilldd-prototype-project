package kr.co.ujin.service.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import kr.co.ujin.core.annotation.MobilePhone;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.validator.constraints.Email;

@JsonAutoDetect
@XmlRootElement
@Entity
@Table(name = "TB_CUSTOMER")
public class Customer {

	@Id
	@NotNull
	@IndexColumn(name = "seq", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	long seq;

	@NotNull
	@IndexColumn(name = "customerName", nullable = false)
	String customerName;

	@NotNull
	@Column(name = "tel")
	String tel;

	@NotNull
	@Column(name = "mobile")
	@MobilePhone
	String mobile;

	@NotNull
	@Column(name = "email")
	@Email
	String email;

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
