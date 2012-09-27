package net.ujin.recruit.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class UjinFinancesDataModel {
	private String fyear;
	private String fmonth;
	private String fday;
	private String fName;
	private String fMoney;
	private String fEtc;
	private int customerIdx;

	public String getFyear() {
		return fyear;
	}

	public String getFmonth() {
		return fmonth;
	}

	public String getFday() {
		return fday;
	}

	public String getfName() {
		return fName;
	}

	public String getfMoney() {
		return fMoney;
	}

	public String getfEtc() {
		return fEtc;
	}

	public void setFyear(String fyear) {
		this.fyear = fyear;
	}

	public void setFmonth(String fmonth) {
		this.fmonth = fmonth;
	}

	public void setFday(String fday) {
		this.fday = fday;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public void setfMoney(String fMoney) {
		this.fMoney = fMoney;
	}

	public void setfEtc(String fEtc) {
		this.fEtc = fEtc;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

	public int getCustomerIdx() {
		return customerIdx;
	}

	public void setCustomerIdx(int customerIdx) {
		this.customerIdx = customerIdx;
	}
}
