package com.niu.springbootdynamicdatasource.config;

import com.niu.springbootdynamicdatasource.apiutil.config.BaseWebMvcConfig;
import com.niu.springbootdynamicdatasource.apiutil.exception.BaseExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Config for application
 *
 * @author HelloWood
 * @date 2017-07-11 21:35
 * @Email hellowoodes@gmail.com
 */

@Configuration
public class WebMvcConfigurer extends BaseWebMvcConfig {

	@Bean
	public BaseExceptionHandler baseExceptionHandler() {

		return new BaseExceptionHandler();
	}
}
