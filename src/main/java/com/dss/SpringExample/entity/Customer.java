package com.dss.SpringExample.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Customer {
	
	int customer_id;
	@NotEmpty(message="please provide a customer name")
	String customer_name;
	@NotEmpty(message="please provide a city name")
	String customer_city;
	@NotNull(message="please provide payment amount")
	int payment_amt;
	@NotNull(message="please enter postal code")
	int postalcode;
	
	public Customer() {
		super();
	}
	public Customer(int customer_id, String customer_name, String customer_city, int payment_amt, int postalcode) {
		super();
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.customer_city = customer_city;
		this.payment_amt = payment_amt;
		this.postalcode = postalcode;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_city() {
		return customer_city;
	}
	public void setCustomer_city(String customer_city) {
		this.customer_city = customer_city;
	}
	public int getPayment_amt() {
		return payment_amt;
	}
	public void setPayment_amt(int payment_amt) {
		this.payment_amt = payment_amt;
	}
	public int getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(int postalcode) {
		this.postalcode = postalcode;
	}
	
	
}

