package com.anser.testebackend.vo;

import javax.validation.constraints.NotNull;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class CustomerVo{
	

	private Integer id;
	@NotNull (message = "Name não pode ser nulo")
	private String name;
	@NotNull (message = "Age não pode ser nulo")
	private Integer age;
	
	public CustomerVo() {
		// TODO Auto-generated constructor stub
	}
	public CustomerVo(Integer id, String name, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "CustomerVo [id= " + id + ", name= " + name + ", age= " + age + "]";
	}
	
}
