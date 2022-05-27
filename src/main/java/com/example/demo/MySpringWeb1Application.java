package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.demo.dao.UserRepository;

@SpringBootApplication
public class MySpringWeb1Application {

	public static void main(String[] args) {
		System.out.println("Hello main");
		ApplicationContext context =SpringApplication.run(MySpringWeb1Application.class, args);
		//UserRepository userRepository = context.getBean(UserRepository.class);
	}

}
