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

        return sessionId;
    }

        public String addUserByExchMethod() {

            // Создание User, который отправлю на сервер
            User newUser = new User(3L, "James", "Brown", (byte) 39);

            // Установка заголовка Accept как APPLICATION_JSON, чтобы получить содержимое в формате JSON
            // и устанавливает заголовок Content-Type на application/json.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Cookie", "JSESSIONID=" + sessionId); // Использование SessionID из поля класса

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
        headersUpDate.set("Cookie", "JSESSIONID=" + sessionId); // Использование SessionID из поля класса

        HttpEntity<User> request = new HttpEntity<>(updatedUser, headersUpDate);

        ResponseEntity<String> responseEntityUpd = restTemplate.exchange(
                UPDATE_USER_URL,
                HttpMethod.PUT,
                request,
                String.class);

        return " User updating successfully: " + responseEntityUpd.getBody();

    }

    public String deleteUserByExchMethod(Long userId) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", "JSESSIONID=" + sessionId);

        HttpEntity<User> requestEntity = new HttpEntity<>(headers);
        String url = DELETE_USER_URL.replace("{id}", String.valueOf(userId));

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                requestEntity,
                String.class);

        return "User deleted successfully: " + response.getBody();
    }

}

