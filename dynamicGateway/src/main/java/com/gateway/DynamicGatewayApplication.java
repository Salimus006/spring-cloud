package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DynamicGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamicGatewayApplication.class, args);
	}

	/**
	 * Pour dire à spring cloud gateway quand tu recois une requête le premier / du Path remprésente le nom
	 * du microservice
	 * Ex : <a href="http://localhost:8888/INVENTORY-SERVICE/products">...</a> ==> INVENTORY-SERVICE correspond
	 *
	 * On peut mélanger entre configuration dynamique et statique (voir l'exemple de rapidApi)
	 *
	 * @param rdc
	 * @param dlp
	 * @return
	 */
	@Bean
	DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp) {
		return new DiscoveryClientRouteDefinitionLocator(rdc, dlp);
	}

}
