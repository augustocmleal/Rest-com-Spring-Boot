package com.anser.testebackend.dao;

import java.sql.SQLException;
import java.util.List;

import com.anser.testebackend.vo.SaleVo;

public interface SaleDao {
	
	public void inserir(SaleVo sale) throws SQLException;
	
	public List<SaleVo> listar() throws SQLException ;
}
