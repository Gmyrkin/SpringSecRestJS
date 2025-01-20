package com.exmp315.configuration;


import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


//
@Configuration
@ComponentScan ("com.exmp315")
public class MyConfig {


//     для совершения hhtp запросов из REST клиента использ вспомогательный класс Spring
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
