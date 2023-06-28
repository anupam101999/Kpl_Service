package com.kpl.registration;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@SpringBootApplication
public class RegistrationApplication  extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationApplication.class, args);
	}
	
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/**");
		
	}
	
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	
}
