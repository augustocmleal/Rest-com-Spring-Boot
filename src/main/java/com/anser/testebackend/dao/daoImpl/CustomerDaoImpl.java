package com.anser.testebackend.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.anser.testebackend.dao.CustomerDao;
import com.anser.testebackend.factory.ConnectionFactory;
import com.anser.testebackend.vo.CustomerVo;

@Repository
public class CustomerDaoImpl extends ConnectionFactory implements CustomerDao{

	@Override
	public void inserir(CustomerVo customer) throws SQLException {
		String sql = "INSERT INTO CUSTOMER (NAME, AGE) VALUES (?, ?)";
		Connection conn = null;
		
		try {
			conn = criarConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, customer.getName());
			ps.setInt(2, customer.getAge());
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
	public void alterar(CustomerVo customer) throws SQLException {
		String sql = "UPDATE CUSTOMER SET NAME = ?, AGE = ? WHERE ID = ?";
		Connection conn = null;
		
		try {
			conn = criarConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, customer.getName());
			ps.setInt(2, customer.getAge());
			ps.setInt(3, customer.getId());
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
	public List<CustomerVo> listar() throws SQLException {
		String sql = "SELECT * FROM CUSTOMER";
		Connection conn = null;
		
		try {
			conn = criarConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			List<CustomerVo> lista = new ArrayList<CustomerVo>();
			CustomerVo customer = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				customer = new CustomerVo();
				customer.setId(rs.getInt("ID"));
				customer.setName(rs.getString("NAME"));
				customer.setAge(rs.getInt("AGE"));	
				lista.add(customer);
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
	public void excluir(Integer id) throws SQLException {
		String sql = "DELETE FROM CUSTOMER WHERE ID = ?";
		
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

	@Override
	public CustomerVo carregar(Integer id) throws SQLException{
		String sql = "SELECT * FROM CUSTOMER WHERE ID = ?";
		
		Connection conn = null;
		
		try {
			conn = criarConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			CustomerVo customer = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				customer = new CustomerVo();
				customer.setId(rs.getInt("ID"));
				customer.setName(rs.getString("NAME"));
				customer.setAge(rs.getInt("AGE"));
			}
			rs.close();
			ps.close();
			return customer;
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
