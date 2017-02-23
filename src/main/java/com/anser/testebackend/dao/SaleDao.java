package com.anser.testebackend.dao;

import java.sql.SQLException;
import java.util.List;

import com.anser.testebackend.vo.SaleVo;

public interface SaleDao {
	
	public Integer inserir(SaleVo sale) throws SQLException;
	
	public void alterar(SaleVo sale) throws SQLException;
	
	public List<SaleVo> listar() throws SQLException ;
	
	public SaleVo carregar(Integer id) throws SQLException ;

	public void excluir(Integer id) throws SQLException ;
}
