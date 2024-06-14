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

public class RecensioneDAO implements BeanDAO<Recensione, Integer>{
	
	private static String NOME_TABELLA = "recensione";
	private DataSource dataSource = null;

	public RecensioneDAO(DataSource dataSource) {
		this.dataSource = dataSource;	
	}
	
	@Override
	public synchronized void doSave(Recensione data) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + RecensioneDAO.NOME_TABELLA
				+ " (descrizione, voto, utente) VALUES (?, ?, ?)";

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, data.getDescrizione());
			preparedStatement.setString(2, data.getVoto().toString());
			preparedStatement.setString(3, data.getUtente());

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
	public synchronized boolean doDelete(Integer code) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + RecensioneDAO.NOME_TABELLA + " WHERE codRecensione = ?";

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

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
	public synchronized Recensione doRetrieveByKey(Integer code) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Recensione bean = new Recensione();

		String selectSQL = "SELECT * FROM " + RecensioneDAO.NOME_TABELLA + " WHERE codRecensione = ?";

		try {
		
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setCodRecensione(rs.getInt("codRecensione"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setVoto(Integer.parseInt(rs.getString("voto")));
				bean.setUtente(rs.getString("utente"));
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
	public synchronized Collection<Recensione> doRetrieveAll(String order) throws SQLException {
		
		Connection connection=null;
		PreparedStatement preparedStatement = null;
		
		Collection<Recensione> recensioni = new ArrayList<Recensione>();
		
		List<String> validOrders = Arrays.asList(
				"CODRECENSIONE","DESCRIZIONE","VOTO","UTENTE"
		);
		
		String selectSQL = "SELECT * FROM " + RecensioneDAO.NOME_TABELLA;
		
		if(order != null && validOrders.contains(order.toUpperCase())){
			selectSQL += " ORDER BY " + order;
		}
		
		Recensione bean = null;
		try{
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean = new Recensione();
				
				bean.setCodRecensione(rs.getInt("codRecensione"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setVoto(Integer.parseInt(rs.getString("voto")));
				bean.setUtente(rs.getString("utente"));
				
				recensioni.add(bean);
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
		
		return recensioni;
	}

}
