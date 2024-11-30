package com.customer;

import com.customer.entities.Customer;
import com.customer.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner populate(CustomerRepository customerRepository) {
		return args -> {
			customerRepository.saveAll(List.of(
					Customer.builder().name("salim").email("salim@email.com").build(),
					Customer.builder().name("salimus").email("salimus@email.com").build(),
					Customer.builder().name("salimas").email("salimas@email.com").build()
			));
		};
	}

}
