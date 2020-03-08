package cn.com.hellowood.dynamicdatasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan("cn.com.hellowood.dynamicdatasource.mapper")
public class DynamicDataSourceApplication {

	public static void main(String[] args) {

		SpringApplication.run(DynamicDataSourceApplication.class, args);
	}
}
