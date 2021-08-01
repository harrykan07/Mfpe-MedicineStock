package com.cognizantmfpe.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * The Main Entry Point Class MedicineStockMicroserviceApplication.
 */
@SpringBootApplication
@Slf4j
@EnableFeignClients
@EnableSwagger2
@EnableEurekaClient
public class MedicineStockMicroserviceApplication {

	/**
	 * The main method.
	 *
	 * @param args: the arguments
	 */
	public static void main(String[] args) {
		log.info("START");
		SpringApplication.run(MedicineStockMicroserviceApplication.class, args);
		log.info("END");
	}

}
