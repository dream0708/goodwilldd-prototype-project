package kr.co.ujin.service.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.hibernate.annotations.IndexColumn;

@JsonAutoDetect
@XmlRootElement
@Entity
@Table(name = "TB_CUSTOMER")
public class Customer {

	@Id
	@NotNull
	@IndexColumn(name = "seq", nullable = false)
	long seq;

	@NotNull
	@IndexColumn(name = "customerName", nullable = false)
	String customerName;

	@NotNull
	@Column(name = "tel")
	String tel;

	@NotNull
	@Column(name = "mobile")
	String mobile;

	@NotNull
	@Column(name = "email")
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
