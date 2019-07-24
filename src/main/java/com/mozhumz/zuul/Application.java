package com.mozhumz.zuul;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
	/**
	 * redisTemplate 序列化使用的jdkSerializeable, 存储二进制字节码, 所以自定义序列化类
	 * @param redisConnectionFactory
	 * @return
	 */
//	@Bean
//	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
//		redisTemplate.setConnectionFactory(redisConnectionFactory);
//
//		// 使用Jackson2JsonRedisSerialize 替换默认序列化
//		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//		objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//
//		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//
//		// 设置value的序列化规则和 key的序列化规则
//		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//		redisTemplate.setKeySerializer(new StringRedisSerializer());
//		redisTemplate.afterPropertiesSet();
//		return redisTemplate;
//	}
}
