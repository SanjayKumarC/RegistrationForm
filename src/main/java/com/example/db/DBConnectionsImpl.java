package com.example.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

public class DBConnectionsImpl implements DBConnections {

	public String DB_DRIVER = null;
	public String DB_CONNECTION = null;
	public String DB_USER = null;
	public String DB_PASSWORD = null;

	public DBConnectionsImpl(String dB_DRIVER, String dB_CONNECTION, String dB_USER, String dB_PASSWORD) {
		super();
		DB_DRIVER = dB_DRIVER;
		DB_CONNECTION = dB_CONNECTION;
		DB_USER = dB_USER;
		DB_PASSWORD = dB_PASSWORD;
	}

	public Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}

	@PostConstruct
	public void initDB() {
		String CreateTABLEQuery = "CREATE TABLE EMPLOYEE"
				+ "("
				+ "EMPID int primary key, "
				+ "USERNAME varchar(30),"
				+ "PASSWORD varchar(30),"
				+ "FIRSTNAME varchar(30),"
				+ "LASTNAME varchar(30),"
				+ "ADDRESS varchar(100))";
		
		String createSeqQuery = "CREATE SEQUENCE "
				+ "IF NOT EXISTS "
				+ "SEQ_EMP_ID "
				+ "START WITH 1 "
				+ "INCREMENT BY 1 "
				+ "NOCYCLE NOCACHE";
		
		String selectSeq = "call NEXT VALUE FOR SEQ_EMP_ID";
		
		Connection connection = getDBConnection();
		PreparedStatement createPreparedStatement = null;
		CallableStatement callableStatement = null;
		try {
			createPreparedStatement = connection.prepareStatement(CreateTABLEQuery);
			createPreparedStatement.executeUpdate();
			createPreparedStatement.close();
			
			createPreparedStatement = connection.prepareStatement(createSeqQuery);
			createPreparedStatement.executeUpdate();
			createPreparedStatement.close();
			
			callableStatement = connection.prepareCall(selectSeq);
			ResultSet resultSet = callableStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println("*********");
				System.out.println(resultSet.getRow());
				System.out.println("*********");
			}
			resultSet.close();
			callableStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
