package com.abcrestaurant.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.abcrestaurant.common.entity", "com.abcrestaurant.admin.user"})
public class AbcBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbcBackEndApplication.class, args);
	}

}
