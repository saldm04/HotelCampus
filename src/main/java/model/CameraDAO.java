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

public class CameraDAO implements BeanDAO<Camera, Integer>{

	public static String NOME_TABELLA = "camera";
	private DataSource dataSource = null;
	
	public CameraDAO(DataSource dataSource){
		this.dataSource=dataSource;
	}
	
	@Override
	public synchronized void doSave(Camera data) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		if(doRetrieveByKey(data.getNumero()).getNumero() == 0 ){
		
			String insertSQL = "INSERT INTO "+ CameraDAO.NOME_TABELLA + 
					" (numero, numeroMaxOspiti, quadratura, costo, tipo, "
					+ "img1, img2, disponibile) VALUES (?,?,?,?,?,?,?,?)";
			try{
				connection = dataSource.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setInt(1, data.getNumero());
				preparedStatement.setInt(2, data.getNumeroMaxOspiti());
				preparedStatement.setInt(3, data.getQuadratura());
				preparedStatement.setInt(4, data.getCosto());
				preparedStatement.setString(5, data.getTipo());
				preparedStatement.setBytes(6, data.getImg1());
				preparedStatement.setBytes(7, data.getImg2());
				preparedStatement.setBoolean(8, data.isDisponibile());
			
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
		}else {
			
			String alterSQL = "UPDATE " + CameraDAO.NOME_TABELLA
					+ " SET numeroMaxOspiti = ?, costo = ? ,quadratura = ?, img1 = ?, img2 = ?, disponibile = ?, tipo = ? WHERE numero = ?";
			
			try {
				connection = dataSource.getConnection();
				preparedStatement = connection.prepareStatement(alterSQL);
				preparedStatement.setInt(1, data.getNumeroMaxOspiti());
				preparedStatement.setInt(2, data.getCosto());
				preparedStatement.setInt(3, data.getQuadratura());
				preparedStatement.setBytes(4, data.getImg1());
				preparedStatement.setBytes(5, data.getImg2());
				preparedStatement.setBoolean(6, true);
				preparedStatement.setString(7, data.getTipo());
				preparedStatement.setInt(8, data.getNumero());
				preparedStatement.executeUpdate();
			}finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if(connection != null)
						connection.close();
				}
			}
		}
	}

	@Override
	public synchronized boolean doDelete(Integer code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + CameraDAO.NOME_TABELLA + " WHERE numero = ?";
		
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
	public synchronized Camera doRetrieveByKey(Integer code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Camera bean = new Camera();

		String selectSQL = "SELECT * FROM " + CameraDAO.NOME_TABELLA + " WHERE numero = ?";

		try {
		
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setNumero(rs.getInt("numero"));
				bean.setNumeroMaxOspiti(rs.getInt("numeroMaxOspiti"));
				bean.setQuadratura(rs.getInt("quadratura"));
				bean.setCosto(rs.getInt("costo"));
				bean.setTipo(rs.getString("tipo"));
				bean.setImg1(rs.getBytes("img1"));
				bean.setImg2(rs.getBytes("img2"));
				bean.setDisponibile(rs.getBoolean("disponibile"));
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
	public synchronized Collection<Camera> doRetrieveAll(String order) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement = null;
		
		Collection<Camera> camere = new ArrayList<Camera>();
		
		List<String> validOrders = Arrays.asList(
				"NUMERO","NUMEROMAXOSPITI","QUADRATURA","COSTO","TIPO","IMG1","IMG2","DISPONIBILE"
		);
		
		String selectSQL = "SELECT * FROM " + CameraDAO.NOME_TABELLA;
		
		if(order != null && validOrders.contains(order.toUpperCase())){
			selectSQL += " ORDER BY "+order;
		}
		
		try{
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Camera bean = new Camera();
				
				bean.setNumero(rs.getInt("numero"));
				bean.setNumeroMaxOspiti(rs.getInt("numeroMaxOspiti"));
				bean.setQuadratura(rs.getInt("quadratura"));
				bean.setCosto(rs.getInt("costo"));
				bean.setTipo(rs.getString("tipo"));
				bean.setImg1(rs.getBytes("img1"));
				bean.setImg2(rs.getBytes("img2"));
				bean.setDisponibile(rs.getBoolean("disponibile"));
				
				camere.add(bean);
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
		
		return camere;
	}
	
	public synchronized boolean setDisponibile(Integer numero, Boolean disponibilita) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String alterSQL = "UPDATE " + CameraDAO.NOME_TABELLA
				+ " SET disponibile = ? WHERE numero = ?";
		
		int result = 0;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(alterSQL);
			
			preparedStatement.setBoolean(1, disponibilita);
			preparedStatement.setInt(2, numero);
			
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
