package kr.co.ujin.service.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.hibernate.annotations.IndexColumn;
import org.springframework.format.annotation.DateTimeFormat;

@JsonAutoDetect
@XmlRootElement
@Entity
@Table(name = "TB_FINANCE")
public class Finance {
	@Id
	@NotNull
	@IndexColumn(name = "seq", nullable = false)
	long seq;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@IndexColumn(name = "workdate", nullable = false)
	String workdate;

	@NotNull
	@Column(name = "worker", nullable = false)
	String worker;

	@NotNull
	@Column(name = "pay", nullable = false)
	long pay;

	@IndexColumn(name = "company", nullable = false)
	String company;

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getWorkdate() {
		return workdate;
	}

	public void setWorkdate(String workdate) {
		this.workdate = workdate;
	}

	public String getWorker() {
		return worker;
	}

	public void setWorker(String worker) {
		this.worker = worker;
	}

	public long getPay() {
		return pay;
	}

	public void setPay(long pay) {
		this.pay = pay;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}
