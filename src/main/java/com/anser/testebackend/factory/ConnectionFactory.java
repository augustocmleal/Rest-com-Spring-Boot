package com.anser.testebackend.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionFactory {

	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://localhost:5432/ansertestebackend";
	private static final String USUARIO = "postgres";
	private static final String SENHA = "antonio1";
	
	public Connection criarConexao(){
		Connection conn = null;
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USUARIO, SENHA);
			
		} catch (Exception e) {
			System.out.println("Erro ao conectar o banco.");
			e.printStackTrace();
		}
		return conn;
	}
	
	public void fecharConexao(Connection conn, PreparedStatement pstmt, ResultSet rs){
		try {
			if(conn != null){
				conn.close();
			}
			if(pstmt != null){
				pstmt.close();
			}
			if(rs != null){
				rs.close();
			}
		} catch (Exception e) {
			System.out.println("Erro ao fechar conexão;");
			e.printStackTrace();
		}
	
	}

}
