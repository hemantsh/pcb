package com.sc.fe.analyze;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching

@EnableConfigurationProperties ({
    FileStorageProperties.class
})


public class AnalyzeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnalyzeApplication.class, args);
	}
	
}
