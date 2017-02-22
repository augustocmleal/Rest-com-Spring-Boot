package com.anser.testebackend.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.anser.testebackend.dao.daoImpl.SaleDaoImpl;
import com.anser.testebackend.vo.SaleVo;

@RestController
@Validated
@RequestMapping(path = "/sale")
public class SaleController {

	private static SaleDaoImpl saleDaoImpl = new SaleDaoImpl();
	
	@RequestMapping(path = "/listar", method = RequestMethod.GET)
	public List<SaleVo> listar() throws SQLException{	
		return saleDaoImpl.listar();
	}
	
	
}
