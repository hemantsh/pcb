package com.sc.fe.analyze;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableCaching
@EnableSwagger2
@EnableConfigurationProperties({
    FileStorageProperties.class
})

public class AnalyzeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnalyzeApplication.class, args);
    }
}
