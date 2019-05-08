package com.mozhumz.zuul;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@MapperScan("com.mozhumz.zuul.mapper")
@EnableZuulProxy
@EnableEurekaClient
//@EnableDiscoveryClient
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 7200)
public class Application {

	public static void main(String[] args)  {

		SpringApplication.run(Application.class, args);
	}

}
