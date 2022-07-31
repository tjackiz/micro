package de.tjackiz.productinquiryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("de.tjackiz.productinquiryservice")
public class ProductInquiryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductInquiryServiceApplication.class, args);
	}

}
