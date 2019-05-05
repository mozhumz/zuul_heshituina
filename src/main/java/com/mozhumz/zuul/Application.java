package com.mozhumz.zuul;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@MapperScan("com.mozhumz.zuul.mapper")
@EnableZuulProxy
@EnableEurekaClient
@EnableDiscoveryClient
public class Application {

	public static void main(String[] args)  {

		SpringApplication.run(Application.class, args);
	}

}
