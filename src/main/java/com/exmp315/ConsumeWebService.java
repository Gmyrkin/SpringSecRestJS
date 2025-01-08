package com.exmp315;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import java.util.List;


// коммуникация с REST (осуществляет http запросы и получать ответы)
@Component
@SessionAttributes("usersList")
public class ConsumeWebService {

    static String GET_USERS_URL = "http://94.198.50.185:7081/api/users";
    static String CREATE_USER_URL = "http://94.198.50.185:7081/api/users";
    static String UPDATE_USER_URL = "http://94.198.50.185:7081/api/users";
    static String DELETE_USER_URL = "http://94.198.50.185:7081/api/users/{id}";


    private final RestTemplate restTemplate;

    public ConsumeWebService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getListUsersExchMethod() {

        // GET-запрос к указанному URL, получение списка всех пользователей
        // .exchange метод позволяет отправлять HTTP-запросы и получать ответы

        ResponseEntity<String> response = restTemplate.exchange(GET_USERS_URL,
                HttpMethod.GET,
                null,
                String.class);

        // Получить список всех пользователей c
        String user = response.getBody();
        System.out.println("response body - " + user);
        HttpHeaders responseHeaders = response.getHeaders();
        System.out.println("response Headers - " + responseHeaders);

        // Извлечение cookies
        // .getHeaders() метод позволяет получить заголовки HTTP,
        // которые могут содержать метаданные (статус, тип, cookies)

        HttpHeaders headers = response.getHeaders(); // для доступа к заголовкам HTTP из ответа

        // Извлекаем cookies из заголовка Set-Cookie
        List<String> cookies = headers.get("Set-Cookie"); // используется сервером для отправки cookies, get класса HttpHeaders, возвращает список значений


        String sessionId = null;

        if (cookies != null && !cookies.isEmpty()) {

            // Получение session ID из cookie
            for (String cookie : cookies) {
                if (cookie.startsWith("JSESSIONID")) {
                    sessionId = cookie.split(";")[0] // Вернет "JSESSIONID=ЦЫФРЫ".
                            .split("=")[1]; // Извлечение значения (вернет "ЦЫФРЫ" и присвоит это значение переменной sessionId.
                    break;
                }
            }
        }

        if (sessionId != null) {

            // Сохранение session ID в переменную класса
            System.out.println("Session ID: " + sessionId);
        }

        return sessionId;
    }

}




//            HttpHeaders headers = new HttpHeaders();
//            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
//            HttpEntity<String> entity = new HttpEntity<String>(headers);
//
//            return restTemplate.exchange(
//                    GET_USERS_URL, HttpMethod.GET, entity, String.class).getBody();
//        }











//    public String getListUsersExchMethod() {
//
//    headers.add(HttpHeaders.COOKIE, "sessionid=1");
//
//    HttpEntity<String> entity = new HttpEntity<>(headers);
//    ResponseEntity<String> response = restTemplate.exchange(
//            GET_USER_URL,
//            HttpMethod.GET,
//            entity,
//            String.class);
//
//    return response.getBody();
//}





//            ResponseEntity<String> response = restTemplate.exchange(
//                    GET_USER_URL,
//                    HttpMethod.GET,
//                    null,
//                    String.class);
//
//            HttpHeaders responseHeaders = response.getHeaders();
//            List<String> cookies = responseHeaders.get(HttpHeaders.SET_COOKIE);
//
//            if (cookies != null) {
//                for (String cookie : cookies) {
//                    System.out.println("Received cookie: " + cookie);
//                }
//            }
//
//            return response
//                    .getBody();
//        }
//    }


//            HttpHeaders headers = new HttpHeaders();
//            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
//            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
//            ResponseEntity<String> result = restTemplate.exchange(
//                    GET_USER_URL,
//                    HttpMethod.GET,
//                    entity,
//                    String.class);
//
//            ResponseEntity<String> forEntity = restTemplate.getForEntity(GET_USER_URL, String.class);
//            forEntity.getHeaders().get("Set-Cookie").stream().forEach(System.out::println);




//          // Дополнительный метод

//        ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL, String.class);
//        responseEntity.getHeaders().get("Set-Cookie").stream().forEach(System.out::println);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//
//        return restTemplate
//                .exchange(URL, HttpMethod.GET, entity, String.class)
//                .getBody();
//    }



//        RestTemplate restTemplate = new RestTemplate();
//        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(URL)
//                // predefined params
//                .queryParam("user", "userValue")
//                .queryParam("lastName", "lastNameValue");
//        params.forEach(uriBuilder::queryParam);
//        HttpHeaders headers = new HttpHeaders() {{
//            setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//            setAccept(List.of(MediaType.APPLICATION_JSON));
//        }};
//        ResponseEntity<String> request = restTemplate.exchange(uriBuilder.toUriString(),
//                HttpMethod.GET, new HttpEntity<>(headers), String.class);
//
//
//        return request.getBody();
//
//
//    }


//// коммуникация с REST (осуществляет http запросы и получать ответы)
//@Component
//@SessionAttributes("usersList")
//public class ConsumeWebService {


//    static String URL = "http://94.198.50.185:7081/api/users";
//    static RestTemplate restTemplate = new RestTemplate();

//public String getListUsersExchMethod() {
//
//        HttpHeaders headers = new HttpHeaders();
//       headers.setContentType(MediaType.APPLICATION_JSON);//
//
//        HttpEntity<List<Object>> requestEntity = new HttpEntity<>(headers);
//        ResponseEntity<List> responseEntity = restTemplate
//        .exchange(URL,HttpMethod.GET,requestEntity,List.class);
//
//        HttpStatusCode statusCode = responseEntity.getStatusCode();
//        System.out.println("status code - " + statusCode);
//        List users = responseEntity.getBody();
//        System.out.println("response body - " + users);
//        HttpHeaders responseHeaders = responseEntity.getHeaders();
//        System.out.println("response Headers - " + responseHeaders);
//
//
//
//        return restTemplate
//        .exchange(URL, HttpMethod.GET, requestEntity, String.class)
//        .getBody();
//        }
//








