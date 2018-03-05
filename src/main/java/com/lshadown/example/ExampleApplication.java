package com.lshadown.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"com.lshadown.example" })
public class ExampleApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ExampleApplication.class, args);
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		Arrays.stream(beanNames).forEach(System.out::println);
	}
}
