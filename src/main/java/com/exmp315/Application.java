package com.exmp315;

import com.exmp315.configuration.MyConfig;
import com.exmp315.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;


public class Application {


    public static void main(String[] args) {


        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);

        ConsumeWebService consumeWebService = context.getBean("consumeWebService",
                ConsumeWebService.class);

        // Получение sessionId и списка пользователей
        consumeWebService.getListUsersExchMethod();
        System.out.println("Session ID: " + consumeWebService.getSessionId());

        // Добавляю пользователя
        String result = consumeWebService.addUserByExchMethod();
        System.out.println(result);

//        // Получение sessionId и списка пользователей
//        consumeWebService.getListUsersExchMethod();

        // Метод обновления пользователя
        String updateResult = consumeWebService.updateUserByExchMethod();
        System.out.println(updateResult);


    }

}








