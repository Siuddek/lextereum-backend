package com.lextereum.lextereumbackend;

import com.lextereum.lextereumbackend.config.MinioProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@EnableConfigurationProperties(MinioProperties.class)
public class LextereumBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(LextereumBackendApplication.class, args);
    }

}
