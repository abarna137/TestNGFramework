package com.luma.qa.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class ConfigReader {
    @Value("${browserName}")
    private String browserName;

    @Value("${url}")
    private String url;

    @Value("${headless}")
    private String headless;

    @Value("${incognito}")
    private String incognito;

    @Value("${email}")
    private String email;

    @Value("${password}")
    private String password;
}
