package com.exmp315.configuration;


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

import java.net.http.HttpClient;

//
@Configuration
@ComponentScan ("com.exmp315")
public class MyConfig {


//     для совершения hhtp запросов из REST клиента использ вспомогательный класс Spring
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
//        RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
//        HttpClient httpClient = HttpClientBuilder.create()
//                .setDefaultRequestConfig(requestConfig)
//                .build();
//
//        RestTemplate restTemplate = restTemplateBuilder
//                .requestFactory(
//                        () -> {
//                            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//                            requestFactory.setHttpClient(httpClient);
//                            return new BufferingClientHttpRequestFactory(requestFactory);
//                        })
//                .basicAuthentication("name", "lastName")
//                .build();
//
//        return restTemplate;
//    }


//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        return builder.build();
//    }


}
