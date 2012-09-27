package net.ujin.recruit.model;

public class UjinCustomerDataModel {
	private int _idx;
	private String customerName;
	private String customerEmail;
	private String customerTel;
	private String customerAddr;
	private String customerBigo;

	public String getCustomerName() {
		return customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public String getCustomerTel() {
		return customerTel;
	}

	public String getCustomerAddr() {
		return customerAddr;
	}

	public String getCustomerBigo() {
		return customerBigo;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}

	public void setCustomerAddr(String customerAddr) {
		this.customerAddr = customerAddr;
	}

	public void setCustomerBigo(String customerBigo) {
		this.customerBigo = customerBigo;
	}

	public int get_idx() {
		return _idx;
	}

	public void set_idx(int _idx) {
		this._idx = _idx;
	}

}
