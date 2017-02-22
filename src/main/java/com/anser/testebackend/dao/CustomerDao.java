package com.anser.testebackend.dao;

import java.sql.SQLException;
import java.util.List;

import com.anser.testebackend.vo.CustomerVo;

public interface CustomerDao {
	
	public void inserir(CustomerVo customer) throws SQLException;
	
	public void alterar(CustomerVo customer) throws SQLException;
	
	public List<CustomerVo> listar() throws SQLException ;
	
	public CustomerVo carregar(Integer id) throws SQLException ;

	public void excluir(Integer id) throws SQLException ;
}
