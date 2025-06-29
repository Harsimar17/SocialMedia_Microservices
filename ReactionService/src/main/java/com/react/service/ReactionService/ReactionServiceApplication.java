package com.react.service.ReactionService;

import java.sql.Connection;
import javax.sql.DataSource;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;


@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.react.service.ReactionService", "com.socialmediaapp.required"})
public class ReactionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactionServiceApplication.class, args);
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	
	@Bean
	public ApplicationRunner runner(DataSource dataSource) {
		return args -> {
			Connection connection = dataSource.getConnection();
			org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ReactionServiceApplication.class);
			logger.info(connection.toString());
		};
	}
}
