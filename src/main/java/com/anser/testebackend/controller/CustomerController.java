package com.anser.testebackend.controller;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anser.testebackend.dao.daoImpl.CustomerDaoImpl;
import com.anser.testebackend.vo.CustomerVo;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerDaoImpl customerDaoImpl;
	
	@PostMapping("/")
	public void inserir(@Valid @RequestBody CustomerVo customer) throws SQLException{	
		customerDaoImpl.inserir(customer);
	}
	
	@PostMapping(value = "/{id}")
	public void alterar(@PathVariable Integer id, @Valid @RequestBody CustomerVo customer) throws SQLException{	
		if(customer != null){
			customer.setId(id);
		}
		customerDaoImpl.alterar(customer);
	}
	
	@RequestMapping("/")
	public List<CustomerVo> listar() throws SQLException{	
		return customerDaoImpl.listar();
	}
	
	@RequestMapping(value = "/{id}")
	public CustomerVo carregar(@PathVariable Integer id) throws SQLException{	
		return customerDaoImpl.carregar(id);
	}
	
	@PostMapping(path = "/excluir/{id}")
	public void excluir(@PathVariable Integer id) throws SQLException{	
			customerDaoImpl.excluir(id);
	}
}
