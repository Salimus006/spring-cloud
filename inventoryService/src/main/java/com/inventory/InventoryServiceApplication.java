package com.inventory;

import com.inventory.entities.Product;
import com.inventory.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner populate(ProductRepository productRepository) {
		return args -> {
			productRepository.saveAll(List.of(
					Product.builder().name("Chocolat").price(12.0).build(),
					Product.builder().name("Lait").price(12.0).build(),
					Product.builder().name("Banane").price(11.0).build()
			));
		};
	}

}
