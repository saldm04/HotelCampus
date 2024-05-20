package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

public class ServizioDAO implements BeanDAO<Servizio, String>{
	
	private static String NOME_TABELLA = "servizio";
	private DataSource dataSource = null;

	public ServizioDAO(DataSource dataSource) {
		this.dataSource = dataSource;	
	}

	@Override
	public synchronized void doSave(Servizio data) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ServizioDAO.NOME_TABELLA
				+ " (nome, descrizione, costo, disponibile, img1, img2) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, data.getNome());
			preparedStatement.setString(2, data.getDescrizione());
			preparedStatement.setInt(3, data.getCosto());
			preparedStatement.setBoolean(4, data.isDisponibile());
			preparedStatement.setBytes(5, data.getImg1());
			preparedStatement.setBytes(6, data.getImg2());

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

		String deleteSQL = "DELETE FROM " + ServizioDAO.NOME_TABELLA + " WHERE nome = ?";

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
	public synchronized Servizio doRetrieveByKey(String code) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Servizio bean = new Servizio();

		String selectSQL = "SELECT * FROM " + ServizioDAO.NOME_TABELLA + " WHERE nome = ?";

		try {
		
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setCosto(rs.getInt("costo"));
				bean.setDisponibile(rs.getBoolean("disponibile"));
				bean.setImg1(rs.getBytes("img1"));
				bean.setImg2(rs.getBytes("img2"));
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
	public synchronized Collection<Servizio> doRetrieveAll(String order) throws SQLException {
		
		Connection connection=null;
		PreparedStatement preparedStatement = null;
		
		Collection<Servizio> servizi = new ArrayList<Servizio>();
		
		String selectSQL = " SELECT * FROM " + ServizioDAO.NOME_TABELLA;
		
		if(order != null && !order.equals("")){
			selectSQL += "ORDER BY "+order;
		}
		
		try{
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				Servizio bean = new Servizio();
				
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setCosto(rs.getInt("costo"));
				bean.setDisponibile(rs.getBoolean("disponibile"));
				bean.setImg1(rs.getBytes("img1"));
				bean.setImg2(rs.getBytes("img2"));
				
				servizi.add(bean);
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
		
		return servizi;
	}
	
	public synchronized boolean setDisponibile(String nome, Boolean disponibilita) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String alterSQL = "UPDATE " + ServizioDAO.NOME_TABELLA
				+ " SET disponibile = ? WHERE nome = ?";
		
		int result = 0;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(alterSQL);
			preparedStatement.setBoolean(0, disponibilita);
			preparedStatement.setString(1, nome);
			
			result = preparedStatement.executeUpdate();
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if(connection != null)
					connection.close();
			}
		}
		
		return (result != 0);
	}
}
