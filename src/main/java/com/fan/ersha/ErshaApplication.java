package com.fan.ersha;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fan.ersha.mapper")
public class ErshaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErshaApplication.class, args);
	}
}
