package br.com.animesnew.config;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiObjectFactoryBean;

@Configuration
@ComponentScan("br.com.animesnew.data")
public class DataConfig {

	@Profile("development")
	@Bean
	public DataSource dataDevelopment() throws SQLException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/salaoDoReino");
		dataSource.setUsername("root");
		dataSource.setPassword("41042075-x");
		return dataSource;
	}
	
	
	@Profile("production")
	@Bean
	public DataSource dataProduction() throws IllegalArgumentException, NamingException {
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
		jndiObjectFactoryBean.setJndiName("jdbc/salaoDoReino");
		jndiObjectFactoryBean.setResourceRef(true);
		jndiObjectFactoryBean.setProxyInterface( DataSource.class);
		jndiObjectFactoryBean.afterPropertiesSet();
		
		return (DataSource) jndiObjectFactoryBean.getObject();
	}
	

}
