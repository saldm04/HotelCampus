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

public class ServizioPrenotatoDAO implements BeanDAO<ServizioPrenotato, Integer>{

	public static String NOME_TABELLA = "servizio_prenotato";
	private DataSource dataSource = null;
	
	public ServizioPrenotatoDAO(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	@Override
	public void doSave(ServizioPrenotato data) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO "+ ServizioPrenotatoDAO.NOME_TABELLA +
				" (costo, servizio, prenotazione) VALUES (?,?,?)";
		try{
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, data.getCosto());
			preparedStatement.setString(2, data.getServizio());
			preparedStatement.setInt(3, data.getPrenotazione());
			
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
	public boolean doDelete(Integer code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ServizioPrenotatoDAO.NOME_TABELLA + " WHERE id = ?";
		
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
	public ServizioPrenotato doRetrieveByKey(Integer code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ServizioPrenotato bean = new ServizioPrenotato();

		String selectSQL = "SELECT * FROM " + ServizioPrenotatoDAO.NOME_TABELLA + " WHERE id = ?";

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				bean.setId(rs.getInt("id"));
				bean.setCosto(rs.getInt("costo"));
				bean.setServizio(rs.getString("servizio"));
				bean.setPrenotazione(rs.getInt("prenotazione"));
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
	public Collection<ServizioPrenotato> doRetrieveAll(String order) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement = null;
		
		Collection<ServizioPrenotato> servizi = new ArrayList<ServizioPrenotato>();
		
		List<String> validOrders = Arrays.asList(
				"ID","COSTO","SERVIZIO","PRENOTAZIONE"
		);
		
		String selectSQL = "SELECT * FROM " + ServizioPrenotatoDAO.NOME_TABELLA;
		
		if(order != null && validOrders.contains(order.toUpperCase())){
			selectSQL += " ORDER BY "+order;
		}
		
		try{
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				ServizioPrenotato bean = new ServizioPrenotato();
				
				bean.setId(rs.getInt("id"));
				bean.setCosto(rs.getInt("costo"));
				bean.setServizio(rs.getString("servizio"));
				bean.setPrenotazione(rs.getInt("prenotazione"));
				
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
	
	   public Collection<ServizioPrenotato> doRetrieveByPrenotazione(int idPrenotazione) throws SQLException {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        Collection<ServizioPrenotato> servizi = new ArrayList<ServizioPrenotato>();

	        String selectSQL = "SELECT * FROM "+ ServizioPrenotatoDAO.NOME_TABELLA + " WHERE prenotazione = ?";

	        try {
	            connection = dataSource.getConnection();
	            preparedStatement = connection.prepareStatement(selectSQL);
	            preparedStatement.setInt(1, idPrenotazione);

	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
	                ServizioPrenotato bean = new ServizioPrenotato();
	                bean.setId(rs.getInt("id"));
					bean.setCosto(rs.getInt("costo"));
					bean.setServizio(rs.getString("servizio"));
					bean.setPrenotazione(rs.getInt("prenotazione"));
	                servizi.add(bean);
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

	        return servizi;
	    }
	
}
