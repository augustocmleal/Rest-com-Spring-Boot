package com.anser.testebackend.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.anser.testebackend.dao.SaleDao;
import com.anser.testebackend.factory.ConnectionFactory;
import com.anser.testebackend.vo.CustomerVo;
import com.anser.testebackend.vo.SaleVo;

@Repository
public class SaleDaoImpl extends ConnectionFactory implements SaleDao{

	@Override
	public Integer inserir(SaleVo sale){
		String sql = "INSERT INTO SALE (CUSTOMER_ID, DATE, AMOUNT, EXTERNAL_ID) VALUES (?, ?, ?,?) RETURNING ID;";
		Connection conn = null;
		Integer id = -1;
		try {
			conn = criarConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, sale.getCustomer().getId());
			ps.setTimestamp(2, new Timestamp(sale.getDate().getTime()));
			ps.setDouble(3, sale.getAmount());
			ps.setString(4, sale.getExternalId());
			ResultSet rs = ps.executeQuery(); 
			if(rs.next()) id = rs.getInt("id");
			ps.close();
			return id;
			
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
	public List<SaleVo> listar() {
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
				sale.setDate(rs.getTimestamp("DATE"));
				sale.setAmount(rs.getDouble("AMOUNT"));	
				sale.setExternalId(rs.getString("EXTERNAL_ID"));
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

	@Override
	public void alterar(SaleVo sale){
		String sql = "UPDATE SALE SET CUSTOMER_ID = ?, DATE = ?, AMOUNT = ?, EXTERNAL_ID = ? WHERE ID = ?";
		Connection conn = null;
		
		try {
			conn = criarConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, sale.getCustomer().getId());
			ps.setTimestamp(2, new Timestamp(sale.getDate().getTime()));
			ps.setDouble(3, sale.getAmount());
			ps.setString(4, sale.getExternalId());
			ps.setInt(5, sale.getId());
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
	public SaleVo carregar(Integer id){
String sql = "SELECT * FROM SALE WHERE ID = ?";
		
		Connection conn = null;
		
		try {
			conn = criarConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			SaleVo sale = null;
			CustomerVo customer = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				sale = new SaleVo();
				customer = new CustomerVo();
				sale.setCustomer(customer);
				sale.setId(rs.getInt("ID"));
				sale.getCustomer().setId(rs.getInt("CUSTOMER_ID"));;
				sale.setDate(rs.getTimestamp("DATE"));
				sale.setAmount(rs.getDouble("AMOUNT"));	
				sale.setExternalId(rs.getString("EXTERNAL_ID"));
			}
			rs.close();
			ps.close();
			return sale;
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
	public void excluir(Integer id){
		String sql = "DELETE FROM SALE WHERE ID = ?";
		
		Connection conn = null;
		
		try {
			conn = criarConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();	
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
