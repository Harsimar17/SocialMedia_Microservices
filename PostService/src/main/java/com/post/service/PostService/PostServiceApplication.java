package com.post.service.PostService;

import java.sql.Connection;

import javax.sql.DataSource;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.post.service.PostService", "com.coreresources.required","com.serviceclient.services"})
public class PostServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostServiceApplication.class, args);
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	   @Bean
	    public ApplicationRunner runner(DataSource dataSource) {
	        return args -> {
				Connection connection = dataSource.getConnection();
				org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(PostServiceApplication.class);
				logger.info(connection.toString());
	        };
	    }
}
