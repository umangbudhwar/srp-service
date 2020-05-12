package com.srp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.srp.data.repository")
@EntityScan("com.srp.data.entity")
@ComponentScan("com.srp")
public class SrpServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SrpServiceApplication.class, args);
	}

}
