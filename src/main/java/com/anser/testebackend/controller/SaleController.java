package com.anser.testebackend.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anser.testebackend.Main;
import com.anser.testebackend.dao.daoImpl.SaleDaoImpl;
import com.anser.testebackend.message.Receiver;
import com.anser.testebackend.vo.SaleVo;

@RestController
@RequestMapping("/sale")
public class SaleController{
	
	@Autowired
	private SaleDaoImpl saleDaoImpl;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
    	private Receiver receiver;

	
	@PostMapping("/")
	public ResponseEntity<Void> inserir(@Valid @RequestBody SaleVo sale) throws Exception{	
		Integer id = saleDaoImpl.inserir(sale);
	    rabbitTemplate.convertAndSend(Main.queueName, id);
	    receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
	    return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(path = "/{id}")
	public ResponseEntity<Void> alterar(@PathVariable Integer id, @Valid @RequestBody SaleVo sale) throws SQLException{	
		saleDaoImpl.alterar(sale);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping("/")
	public List<SaleVo> listar() throws SQLException{	
		return saleDaoImpl.listar();
	}
	
	@RequestMapping(path = "/{id}")
	public SaleVo carregar(@PathVariable Integer id) throws SQLException{	
		return saleDaoImpl.carregar(id);
	}
	
	@PostMapping(path = "/excluir/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) throws SQLException{	
			saleDaoImpl.excluir(id);
			return new ResponseEntity<>(HttpStatus.OK);
	}


}
