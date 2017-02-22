package com.anser.testebackend.vo;

import java.util.Date;

public class SaleVo {
	private Integer id;
	private CustomerVo customer;
	private Date date;
	private Double amount;
	private Integer externalId;
	
	public SaleVo() {
		// TODO Auto-generated constructor stub
	}

	public SaleVo(Integer id, CustomerVo customer, Date date, Double amount, Integer externalId) {
		super();
		this.id = id;
		this.customer = customer;
		this.date = date;
		this.amount = amount;
		this.externalId = externalId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CustomerVo getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerVo customer) {
		this.customer = customer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getExternalId() {
		return externalId;
	}

	public void setExternalId(Integer externalId) {
		this.externalId = externalId;
	}
	
	
}
