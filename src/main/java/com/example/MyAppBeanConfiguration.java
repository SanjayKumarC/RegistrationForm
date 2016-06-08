package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.example.dao.EmployeeDAO;
import com.example.dao.EmployeeDAOImpl;
import com.example.db.DBConnections;
import com.example.db.DBConnectionsImpl;

@Configuration
@PropertySource(value="classpath:jdbc.properties")
public class MyAppBeanConfiguration {
	
	@Value("${DB_DRIVER}")
    String dbDriver;
	
	@Value("${DB_CONNECTION}")
    String dbConnection;
	
	@Value("${DB_USER}")
    String dbUser;
	
	@Value("${DB_PASSWORD}")
    String dbPassword;
	
	@Bean
	public DBConnections dbConnections() {
		DBConnections dbConn = new DBConnectionsImpl(dbDriver, dbConnection, dbUser, dbPassword);
		return dbConn;
	}
	
	@Bean
	public EmployeeDAO employeeDAO() {
		return new EmployeeDAOImpl();
	}

}
