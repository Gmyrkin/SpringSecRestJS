package com.exmp315;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

// коммуникация с REST (осуществляет http запросы и получать ответы)
@Component
public class ConsumeWebService {

    static String URL = "http://94.198.50.185:7081/api/users";
    static RestTemplate restTemplate = new RestTemplate();


      // РАБОЧИЙ


    public String useExchangeMethodRestTempl() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List<Object>> requestEntity = new HttpEntity<>(headers);


        return restTemplate
                .exchange(URL,
                        HttpMethod.GET,
                        requestEntity,
                        String.class)
                .getBody();

    }



    public String getListUsersExchMethod() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List<Object>> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<List> responseEntity = restTemplate.exchange(URL,
                HttpMethod.GET,
                requestEntity,
                List.class);
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        System.out.println("status code - " + statusCode);
        List users = responseEntity.getBody();
        System.out.println("response body - " + users);
        HttpHeaders responseHeaders = responseEntity.getHeaders();
        System.out.println("response Headers - " + responseHeaders);


        return restTemplate
                .exchange(URL, HttpMethod.GET, requestEntity, String.class)
                .getBody();
    }
}



//    @GetMapping
//    private String getListUsersExchMethod(HttpEntity<Object> requestEntity) {
//        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "users",
//                HttpMethod.GET,
//                requestEntity,
//                String.class);
//        HttpStatusCode statusCode = responseEntity.getStatusCode();
//        System.out.println("status code - " + statusCode);
//        String users = responseEntity.getBody();
//        System.out.println("response body - " + users);
//        HttpHeaders responseHeaders = responseEntity.getHeaders();
//        System.out.println("response Headers - " + responseHeaders);
//
//
//        return users;
//    }



//    @GetMapping(value = "/set-headers")
//    public ResponseEntity<?> setHeaders() {
//        HttpHeaders httpHeaders = new HttpHeaders();
//
//
//
//        httpHeaders.add("customer-header", "value-header1");
//
//
//        httpHeaders.add(HttpHeaders.FROM, "russia");
//
//        httpHeaders.setDate(0);
//
//        Long date = httpHeaders.getDate();
//        System.out.println(date);
//        return ResponseEntity
//                .ok().headers(httpHeaders)
//                .body(HttpStatus.OK);
//    }


// // Первый от Трегулова

//    public List<Object> getAllUsers() {
//
//        ResponseEntity<List<Object>> responseEntity =
//                restTemplate.exchange(URL,
//                        HttpMethod.GET,
//                        null,
//                        new org.springframework.core.ParameterizedTypeReference<List<Object>>() {
//                        });
//
//        List<Object> allUsers = responseEntity.getBody();
//        return allUsers;
//
//    }


