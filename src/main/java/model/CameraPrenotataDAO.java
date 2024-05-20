package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

public class CameraPrenotataDAO implements BeanDAO<CameraPrenotata, Integer>{

	public static String NOME_TABELLA = "camera_prenotata";
	private DataSource dataSource = null;
	
	public CameraPrenotataDAO(DataSource dataSource){
		this.dataSource=dataSource;
	}
	
	@Override
	public void doSave(CameraPrenotata data) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO "+ CameraPrenotataDAO.NOME_TABELLA +
				" (costo, camera, prenotazione) VALUES (?,?,?)";
		try{
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, data.getCosto());
			preparedStatement.setInt(2, data.getCamera());
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

		String deleteSQL = "DELETE FROM " + CameraPrenotataDAO.NOME_TABELLA + " WHERE id = ?";
		
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
	public CameraPrenotata doRetrieveByKey(Integer code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		CameraPrenotata bean = new CameraPrenotata();

		String selectSQL = "SELECT * FROM " + CameraPrenotataDAO.NOME_TABELLA + " WHERE id = ?";

		try {
		
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				bean.setId(rs.getInt("id"));
				bean.setCosto(rs.getInt("costo"));
				bean.setCamera(rs.getInt("camera"));
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
	public Collection<CameraPrenotata> doRetrieveAll(String order) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement = null;
		
		Collection<CameraPrenotata> camere = new ArrayList<CameraPrenotata>();
		
		String selectSQL = "SELECT * FROM " + CameraPrenotataDAO.NOME_TABELLA;
		
		if(order != null && !order.equals("")){
			selectSQL += " ORDER BY "+order;
		}
		
		try{
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				CameraPrenotata bean = new CameraPrenotata();
				
				bean.setId(rs.getInt("id"));
				bean.setCosto(rs.getInt("costo"));
				bean.setCamera(rs.getInt("camera"));
				bean.setPrenotazione(rs.getInt("prenotazione"));
				
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
}
