package com.kpl.registration;

import com.kpl.registration.config.UTCtoISTConverter;
import com.kpl.registration.logging.LoggingInterceptor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@SpringBootApplication
public class RegistrationApplication extends SpringBootServletInitializer implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationApplication.class, args);
	}

	  @Override
	   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	      return application.sources(RegistrationApplication.class);
	   }

	  @Bean
	    public ModelMapper modelMapper() {
	        ModelMapper modelMapper = new ModelMapper();

	        // Register the UTCtoISTConverter with ModelMapper
	        modelMapper.addConverter(new UTCtoISTConverter());

	        return modelMapper;
	    }
	  @Bean
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	    }

	@Autowired
	private LoggingInterceptor loggingInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loggingInterceptor);
	}

}
