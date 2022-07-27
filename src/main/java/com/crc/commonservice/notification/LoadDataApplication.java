package com.crc.commonservice.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;


@SpringBootApplication(scanBasePackages="com.crc.commonservice.notification")
public class LoadDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoadDataApplication.class, args);

	}
	
}
