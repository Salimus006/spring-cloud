package com.billing;

import com.billing.entities.Bill;
import com.billing.entities.ProductItem;
import com.billing.models.Customer;
import com.billing.models.Product;
import com.billing.repositories.BillRepository;
import com.billing.repositories.ProductItemRepository;
import com.billing.services.CustomerRestClient;
import com.billing.services.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	/*
	Exemple d'appel via la GateWay : http://8888/BILLING-SERVICE/...
	8888 pour passer par la GateWay
	BILLING-SERVICE : est le nom de ce micro-service
	 */
	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}


	@Bean
	CommandLineRunner populate(BillRepository billRepository,
							   ProductItemRepository productItemRepository,
							   CustomerRestClient customerRestClient,
							   ProductRestClient productRestClient){
		return args -> {
			Collection<Product> products=productRestClient.allProducts().getContent();
			Long customerId=1L;
			Customer customer=customerRestClient.findCustomerById(customerId);
			if(customer==null) throw new RuntimeException("Customer not found");
			Bill bill= Bill.builder()
					.billDate(new Date())
					.customerId(customerId)
					.build();
			Bill savedBill = billRepository.save(bill);

			products.forEach(product -> {
				ProductItem productItem= ProductItem.builder()
						.bill(savedBill)
						.productId(product.getId())
						.quantity(1+new Random().nextInt(10))
						.price(product.getPrice())
						.discount(Math.random())
						.build();
				productItemRepository.save(productItem);
			});

		};
	}
}
