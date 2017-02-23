package com.anser.testebackend.vo;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class SaleVo {
	
	private Integer id;
	@NotNull (message = "Customer não pode ser nulo")
	@Autowired
	private CustomerVo customer;
	@NotNull (message = "Date não pode ser nulo")
	private Date date;
	@NotNull (message = "Amount não pode ser nulo")
	private Double amount;
	private String externalId;
	
	public SaleVo() {
		// TODO Auto-generated constructor stub
	}
	public SaleVo(Integer id, CustomerVo customer, Date date, Double amount) {
		super();
		this.id = id;
		this.customer = customer;
		this.date = date;
		this.amount = amount;
	}

	public SaleVo(Integer id, CustomerVo customer, Date date, Double amount, String externalId) {
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

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	
	@Override
	public String toString() {
		return "SaleVo [id= " + id + ", customer= " + customer + ", date= " + date + ", amount= " + amount + ", externalId= "
				+ externalId + "]";
	}
	
}
