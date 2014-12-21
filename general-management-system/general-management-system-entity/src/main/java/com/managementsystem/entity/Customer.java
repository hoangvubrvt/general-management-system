package com.managementsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer extends BusinessEntity{
	private static final long serialVersionUID = 1L;

	@Column(name = "customername", nullable = false)
	private String customerName;

	@Column(name = "address")
	private String address;

	@Column(name = "phonenumber")
	private String phoneNumber;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Customer [customerName=" + customerName + ", address="
				+ address + ", phoneNumber=" + phoneNumber + ", id="
				+ getId() + "]";
	}
}
