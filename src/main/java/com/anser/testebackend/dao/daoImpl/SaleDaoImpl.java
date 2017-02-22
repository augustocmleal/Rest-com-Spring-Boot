package com.anser.testebackend.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.anser.testebackend.dao.SaleDao;
import com.anser.testebackend.factory.ConnectionFactory;
import com.anser.testebackend.vo.CustomerVo;
import com.anser.testebackend.vo.SaleVo;

public class SaleDaoImpl extends ConnectionFactory implements SaleDao{

	@Override
	public void inserir(SaleVo sale) throws SQLException {
		String sql = "INSERT INTO SALE (CUSTOMER_ID, DATE, AMOUNT) VALUES (?, ?, ?)";
		Connection conn = null;
		
		try {
			conn = criarConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, sale.getCustomer().getId());
			ps.setTimestamp(2, new Timestamp(sale.getDate().getTime()));
			ps.setDouble(3, sale.getAmount());
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	@Override
	public List<SaleVo> listar() throws SQLException {
		String sql = "SELECT * FROM SALE";
		Connection conn = null;
		
		try {
			conn = criarConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			List<SaleVo> lista = new ArrayList<SaleVo>();
			SaleVo sale = null;
			CustomerVo customer = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				sale = new SaleVo();
				customer = new CustomerVo();
				sale.setCustomer(customer);
				sale.setId(rs.getInt("ID"));
				sale.getCustomer().setId(rs.getInt("CUSTOMER_ID"));;
				sale.setDate(rs.getDate("DATE"));
				sale.setAmount(rs.getDouble("AMOUNT"));	
				lista.add(sale);
			}
			rs.close();
			ps.close();
			return lista;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}

}
