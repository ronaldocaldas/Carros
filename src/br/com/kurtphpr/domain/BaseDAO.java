package br.com.kurtphpr.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
	
	public BaseDAO() {

		try {
			// Necessário usar o driver JDBC do MySQL
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// Erro de driver JDBC
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException {
		BaseDAO db = new BaseDAO();
		// Testa a conexao
		Connection conn = db.getConnection();
		System.out.println(conn);
	}

	protected Connection getConnection() throws SQLException {
		// URL de conexao com o banco de dados
		String url = "jdbc:mysql://localhost/livro";
		// Conecta utilizando a URL, usuário e senha
		Connection conn = DriverManager.getConnection(url, "livro", "livro123");
		return (conn);
	}
}
