package com.poc.loan_management;

import com.poc.loan_management.controller.PingController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.poc.loan_management.entity" )
@EnableJpaRepositories(basePackages = "com.poc.loan_management.repository")
@Import({PingController.class})
public class LoanManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(LoanManagementApplication.class, args);
	}

}
