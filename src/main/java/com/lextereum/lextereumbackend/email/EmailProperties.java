package com.lextereum.lextereumbackend.email;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "email")
public class EmailProperties {
    private String from;
    private String subject;
}
