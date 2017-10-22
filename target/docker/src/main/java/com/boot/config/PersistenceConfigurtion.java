 package com.boot.config;


import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;

/*
 * @Configuration :
 * 		let spring know its a config class
 * @Bean : 
 * 		let the return object to create a spring bean in spring context
 * @ConfigurationProperties(prefix = "spring.datasource") : tell the data 
 * 		sourcebuilder to use the connection and pulling properties located 
 * 		in app.properties begin with prefix
 * @Primary :
 * 		prevent ambiguity if there're two datasource
 */

@Configuration
public class PersistenceConfigurtion {
	@Bean 
	@ConfigurationProperties(prefix = "spring.datasource") 
	@Primary 
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	@ConfigurationProperties(prefix = "datasource.flyway")
	@FlywayDataSource
	public DataSource flywayDataSource() {
		return DataSourceBuilder.create().build();
	}
}
