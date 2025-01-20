package com.exmp315;


import com.exmp315.model.User;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import java.util.List;


// коммуникация с REST (осуществляет http запросы и получать ответы)
@Component
@SessionAttributes("usersList")
public class ConsumeWebService {

    private final RestTemplate restTemplate;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    private String sessionId; // Поле класса для хранения sessionId

    private List<String> allCookies; // Поле для хранения всех значений заголовка Set-Cookie


    public ConsumeWebService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    static String GET_USERS_URL = "http://94.198.50.185:7081/api/users";
    static String CREATE_USER_URL = "http://94.198.50.185:7081/api/users";
    static String UPDATE_USER_URL = "http://94.198.50.185:7081/api/users";
    static String DELETE_USER_URL = "http://94.198.50.185:7081/api/users/{id}";


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
        HttpHeaders headersResponse = response.getHeaders();
        System.out.println("response Headers - " + headersResponse);

        // Извлечение cookies
        // .getHeaders() метод позволяет получить заголовки HTTP,
        // которые могут содержать метаданные (статус, тип, cookies)
        HttpHeaders headers = response.getHeaders(); // для доступа к заголовкам HTTP из ответа
        headers.getFirst("Set-Cookie");

        // Извлекаем cookies из заголовка Set-Cookie
        // headers.get используется сервером для отправки cookies, get класса HttpHeaders, возвращает список значений
        List<String> cookies = headers.get("Set-Cookie");

        sessionId = null;

        if (cookies != null && !cookies.isEmpty()) { // Цикл на отсутвтие и пустое значение

            // Получение session ID из cookie
            for (String cookie : cookies) {
                if (cookie.startsWith("JSESSIONID")) {
                    sessionId = cookie.split(";")[0] // Вернет "JSESSIONID=ЦИФРЫ".
                            .split("=")[1]; // Извлечение значения (вернет "ЦИФРЫ" и присвоит это значение переменной sessionId.
                    break;
                }
            }
        }

//        if (sessionId != null) {
//
//            // Сохранение session ID в переменную класса
//            System.out.println("Session ID: " + sessionId);
//        }


        return sessionId;
    }

        public String addUserByExchMethod() {

            // Создание User, который отправлю на сервер
            User newUser = new User(3L, "James", "Brown", (byte) 39);

            // Установка заголовка Accept как APPLICATION_JSON, чтобы получить содержимое в формате JSON
            // и устанавливает заголовок Content-Type на application/json.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Set-Cookie", "JSESSIONID=" + sessionId); // Использование SessionID из поля класса

            // Создание объекта HttpEntity с использованием HttpHeaders содержит заголовки и тело User
            HttpEntity<User> requestEntity = new HttpEntity<>(newUser, headers);

            // POST-запрос для создания нового пользователя, для получения сообщения от сервера.
                ResponseEntity<String> response = restTemplate.exchange(
                        CREATE_USER_URL,
                        HttpMethod.POST,
                        requestEntity,
                        String.class);


                return "User created successfully: " + response.getBody();

            }

    public String updateUserByExchMethod() {

        // Проверить что sessionId установлен
        if (sessionId == null) {
            getListUsersExchMethod();
        }

        // Создание объекта обновленного пользователя
        User updatedUser = new User(3L, "Thomas", "Shelby", (byte) 39);

        // Создание заголовков для обновления User
        HttpHeaders headersUpDate = new HttpHeaders();
        headersUpDate.setContentType(MediaType.APPLICATION_JSON);
        headersUpDate.set("Set-Cookie", "JSESSIONID=" + sessionId); // Использование SessionID из поля класса

        HttpEntity<User> request = new HttpEntity<>(updatedUser, headersUpDate);

        ResponseEntity<String> responseEntityUpd = restTemplate.exchange(
                UPDATE_USER_URL,
                HttpMethod.PUT,
                request,
                String.class);

        return " User updating successfully: " + responseEntityUpd.getBody();

    }


}




//    public String addUserByExchMethod() {
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        // Создаю объект пользователя, который отправлю
//        User newUser = new User(3L, "James", "Brown", (byte) 39);
//
//        // Установка заголовка Accept как APPLICATION_JSON, чтобы получить содержимое в формате JSON
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        // Создание объекта HttpEntity с использованием HttpHeaders для настройки запросов, содержит заголовки и тело
//        HttpEntity<User> requestEntity  = new HttpEntity<>(newUser, headers);
//
//        // Метод использован для запроса POST, с настрокой запроса при создании нового пользователя
//        ResponseEntity<User> response = restTemplate.exchange(CREATE_USER_URL,
//                HttpMethod.POST,
//                requestEntity,
//                User.class);
//
//
//        return response.getBody();
//
//    }




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








