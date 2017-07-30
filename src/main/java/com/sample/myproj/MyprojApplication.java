package com.sample.myproj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sample.myproj.config.ApplicationConfig;

@SpringBootApplication
public class MyprojApplication extends WebMvcConfigurerAdapter {

	@Autowired
	private ApplicationConfig config;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(MyprojApplication.class, args);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	
		this.log.info(config.getVirtualImageConfigPath() + ":" + config.getImageUploadConfigPath());
		
		registry.addResourceHandler(
				config.getVirtualImageConfigPath())
				.addResourceLocations(config.getImageUploadConfigPath()).setCachePeriod(0);
	}

}
