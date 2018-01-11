package cn.yearcon.sportapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("cn.yearcon.sportapi.mapper")
@EnableCaching
public class SportapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportapiApplication.class, args);
	}
}
