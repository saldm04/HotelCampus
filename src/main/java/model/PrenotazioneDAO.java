package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

public class PrenotazioneDAO implements BeanDAO<Prenotazione, Integer>{

	public static String NOME_TABELLA = "prenotazioni";
	private DataSource dataSource = null;
	
	public PrenotazioneDAO(DataSource dataSource){
		this.dataSource=dataSource;
	}
	
	@Override
	public synchronized void doSave(Prenotazione data) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO "+ PrenotazioneDAO.NOME_TABELLA +
				" (dataPrenotazione, dataInizio, dataFine, importo, utente)"
				+" VALUES (?,?,?,?,?)";
		try{
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setTimestamp(1, data.getDataPrenotazione());
			preparedStatement.setDate(2, data.getDataInizio());
			preparedStatement.setDate(3, data.getDataFine());
			preparedStatement.setInt(4, data.getImporto());
			preparedStatement.setString(5, data.getUtente());
			
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
	
	public synchronized int doSaveReturnKey(Prenotazione data) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet generatedKeys = null;

	    String insertSQL = "INSERT INTO " + PrenotazioneDAO.NOME_TABELLA +
	            " (dataPrenotazione, dataInizio, dataFine, importo, utente)" +
	            " VALUES (?,?,?,?,?)";

	    try {
	        connection = dataSource.getConnection();
	        preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
	        preparedStatement.setTimestamp(1, data.getDataPrenotazione());
	        preparedStatement.setDate(2, data.getDataInizio());
	        preparedStatement.setDate(3, data.getDataFine());
	        preparedStatement.setInt(4, data.getImporto());
	        preparedStatement.setString(5, data.getUtente());

	        int affectedRows = preparedStatement.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Creating prenotazione failed, no rows affected.");
	        }

	        generatedKeys = preparedStatement.getGeneratedKeys();
	        if (generatedKeys.next()) {
	            return generatedKeys.getInt(1);
	        } else {
	            throw new SQLException("Creating prenotazione failed, no ID obtained.");
	        }
	    } finally {
	        try {
	            if (generatedKeys != null)
	                generatedKeys.close();
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

		String deleteSQL = "DELETE FROM " + PrenotazioneDAO.NOME_TABELLA + " WHERE idPrenotazione = ?";
		
		try{
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
	public synchronized Prenotazione doRetrieveByKey(Integer code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Prenotazione bean = new Prenotazione();

		String selectSQL = "SELECT * FROM " + PrenotazioneDAO.NOME_TABELLA + " WHERE idPrenotazione = ?";

		try {
		
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				bean.setId(rs.getInt("idPrenotazione"));
				bean.setDataPrenotazione(rs.getTimestamp("dataPrenotazione"));
				bean.setDataInizio(rs.getDate("dataInizio"));
				bean.setDataFine(rs.getDate("dataFine"));
				bean.setImporto(rs.getInt("importo"));
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
	public synchronized Collection<Prenotazione> doRetrieveAll(String order) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement = null;
		
		Collection<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
		
		List<String> validOrders = Arrays.asList(
				"IDPRENOTAZIONE","DATAPRENOTAZIONE","DATAINIZIO","DATAFINE","IMPORTO","UTENTE"
		);
		
		String selectSQL = "SELECT * FROM " + PrenotazioneDAO.NOME_TABELLA;
		
		if(order != null && validOrders.contains(order.toUpperCase())){
			selectSQL += " ORDER BY "+order;
		}
		
		try{
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Prenotazione bean = new Prenotazione();
				
				bean.setId(rs.getInt("idPrenotazione"));
				bean.setDataPrenotazione(rs.getTimestamp("dataPrenotazione"));
				bean.setDataInizio(rs.getDate("dataInizio"));
				bean.setDataFine(rs.getDate("dataFine"));
				bean.setImporto(rs.getInt("importo"));
				bean.setUtente(rs.getString("utente"));
				
				prenotazioni.add(bean);
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
		
		return prenotazioni;
	}
	
	public synchronized Collection<Prenotazione> doRetrieveByFilter(String email, Date dataInizio, Date dataFine) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    Collection<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();

	    String selectSQL = "SELECT * FROM " + PrenotazioneDAO.NOME_TABELLA + " WHERE dataInizio >= ? AND dataFine <= ?";
	    if (email != null && !email.isEmpty()) {
	        selectSQL += " AND utente = ?";
	    }

	    try {
	        connection = dataSource.getConnection();
	        preparedStatement = connection.prepareStatement(selectSQL);
	        preparedStatement.setDate(1, dataInizio);
	        preparedStatement.setDate(2, dataFine);
	        if (email != null && !email.isEmpty()) {
	            preparedStatement.setString(3, email);
	        }

	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            Prenotazione bean = new Prenotazione();

	            bean.setId(rs.getInt("idPrenotazione"));
	            bean.setDataPrenotazione(rs.getTimestamp("dataPrenotazione"));
	            bean.setDataInizio(rs.getDate("dataInizio"));
	            bean.setDataFine(rs.getDate("dataFine"));
	            bean.setImporto(rs.getInt("importo"));
	            bean.setUtente(rs.getString("utente"));

	            prenotazioni.add(bean);
	        }
	    } finally {
	        try {
	            if (preparedStatement != null)
	                preparedStatement.close();
	        } finally {
	            if (connection != null)
	                connection.close();
	        }
	    }

	    return prenotazioni;
	}
	
}
