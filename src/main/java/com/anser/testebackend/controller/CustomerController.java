package com.anser.testebackend.controller;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.anser.testebackend.dao.daoImpl.CustomerDaoImpl;
import com.anser.testebackend.vo.CustomerVo;

@RestController
@Validated
@RequestMapping(path = "/customer")
public class CustomerController {
	
	private CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
	
	@RequestMapping(path = "/inserir/{name}/{age}", method = RequestMethod.GET)
	public void inserir(@Valid @PathVariable String name, @Valid @PathVariable Integer age) throws SQLException{	
		CustomerVo customer = new CustomerVo(null, name, age);
		customerDaoImpl.inserir(customer);
	}
	
	@RequestMapping(path = "/alterar/{name}/{age}/{id}", method = RequestMethod.GET)
	public void alterar(@Valid @PathVariable String name, @Valid @PathVariable Integer age, @Valid @PathVariable Integer id) throws SQLException{	
		CustomerVo customer = new CustomerVo(id, name, age);
		customerDaoImpl.alterar(customer);
	}
	
	@RequestMapping(path = "/listar", method = RequestMethod.GET)
	public List<CustomerVo> listar() throws SQLException{	
		return customerDaoImpl.listar();
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public CustomerVo carregar(@PathVariable Integer id) throws SQLException{	
		return customerDaoImpl.carregar(id);
	}
	
	@RequestMapping(path = "/excluir/{id}", method = {RequestMethod.POST, RequestMethod.GET})
	public void excluir(@PathVariable Integer id) throws SQLException{	
			customerDaoImpl.excluir(id);
	}
}
