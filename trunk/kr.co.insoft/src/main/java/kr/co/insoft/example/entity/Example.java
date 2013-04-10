package kr.co.insoft.example.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
@XmlRootElement
@Entity
@Table(name = "TB_EXAMPLE2")
public class Example implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6820811762239313610L;

	public Example() {

	}

	public Example(long seq, String username) {
		this.seq = seq;
		this.username = username;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long seq;

	@Column(nullable = false, name = "username")
	String username;

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

}
