package com.myapp;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

//http://www.thymeleaf.org/doc/articles/layouts.html
@SpringBootApplication
public class SpringBootWebApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootWebApplication.class, args);
	}
	
	/**
	 * Bean que faz a configuração de acesso a dados
	 * @return DataSource configurado para acessar os dados
	 */
	@Bean
	public DataSource dataSource(){
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    dataSource.setUrl("jdbc:mysql://localhost:3306/listavip");
	    dataSource.setUsername("root");
	    dataSource.setPassword("root");
	    return dataSource;
	}

}