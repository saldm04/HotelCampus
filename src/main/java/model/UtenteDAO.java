package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;



public class UtenteDAO implements BeanDAO<Utente, String> {
	
	private static String NOME_TABELLA = "utente";
	private DataSource dataSource = null;

	public UtenteDAO(DataSource dataSource) {
		this.dataSource = dataSource;	
	}
	
	@Override
	public synchronized void doSave(Utente data) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + UtenteDAO.NOME_TABELLA
				+ " (email, nome, cognome, nazionalità, dataDiNascita, password, isAdmin) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, data.getEmail());
			preparedStatement.setString(2, data.getNome());
			preparedStatement.setString(3, data.getCognome());
			preparedStatement.setString(4, data.getNazionalità());
			preparedStatement.setDate(5, data.getDataNascita());
			preparedStatement.setString(6, data.getPassword());
			preparedStatement.setBoolean(7, data.isAdmin());

			preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
	}
	
	@Override
	public synchronized boolean doDelete(String code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + UtenteDAO.NOME_TABELLA + " WHERE email = ?";

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, code);

			result = preparedStatement.executeUpdate();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}
	
	@Override
	public synchronized Utente doRetrieveByKey(String code) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Utente bean = new Utente();

		String selectSQL = "SELECT * FROM " + UtenteDAO.NOME_TABELLA + " WHERE email = ?";

		try {
		
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setEmail(rs.getString("email"));
				bean.setNazionalità(rs.getString("nazionalità"));
				bean.setDataNascita(rs.getDate("dataDiNascita"));
				bean.setPassword(rs.getString("password"));
				bean.setAdmin(rs.getBoolean("isAdmin"));
			}

		}finally {
		
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if(connection != null)
					connection.close();
			}
		} 
		return bean;
		
	}
	
	@Override

	public synchronized Collection<Utente> doRetrieveAll(String order) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement = null;
		
		Collection<Utente> utenti = new ArrayList<Utente>();
		
		String selectSQL = "SELECT * FROM " + UtenteDAO.NOME_TABELLA;
		
		List<String> validOrders = Arrays.asList(
				"EMAIL","NOME","COGNOME","NAZIONALITÀ","DATADINASCITA","PASSWORD","ISADMIN"
		);
		
		if(order != null && validOrders.contains(order.toUpperCase())){
			selectSQL += " ORDER BY "+order;
		}
		Utente bean = null;
		try{
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean = new Utente();
				
				bean.setEmail(rs.getString("email"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setNazionalità(rs.getString("nazionalità"));
				bean.setDataNascita(rs.getDate("dataDiNascita"));
				bean.setPassword(rs.getString("password"));
				bean.setAdmin(rs.getBoolean("isAdmin"));
				
				utenti.add(bean);
			}
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if(connection != null)
					connection.close();
			}
		}
		
		return utenti;
	}
	
	 public int updateUtente(Utente utente) throws SQLException {
	        String updateSQL = "UPDATE " + NOME_TABELLA + " SET " +
	                           "nome = ?, cognome = ?, nazionalità = ?, " +
	                           "dataDiNascita = ?, password = ?, isAdmin = ? " +
	                           "WHERE email = ?";
	        int value=0;
	        
	        try (Connection conn = dataSource.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(updateSQL)) {

	            stmt.setString(1, utente.getNome());
	            stmt.setString(2, utente.getCognome());
	            stmt.setString(3, utente.getNazionalità());
	            stmt.setDate(4, utente.getDataNascita());
	            stmt.setString(5, utente.getPassword());
	            stmt.setBoolean(6, utente.isAdmin());
	            stmt.setString(7, utente.getEmail());

	            value=stmt.executeUpdate();
	        }
	        return value;
	 }

}
