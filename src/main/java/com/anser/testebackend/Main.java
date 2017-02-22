package com.anser.testebackend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages="com.anser")
public class Main {
	
	public static void main(String[] args) throws Exception{
		SpringApplication.run(Main.class, args);
	}
	
}
