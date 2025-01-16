package com.exmp315;

import com.exmp315.configuration.MyConfig;
import com.exmp315.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Application {


    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);

        ConsumeWebService consumeWebService = context.getBean("consumeWebService",
                ConsumeWebService.class);

        // Получаю пользователей и SessionID
        String sessionID = consumeWebService.getListUsersExchMethod();
        System.out.println(sessionID);

        // Добавляю пользователя
        String result = consumeWebService.addUserByExchMethod();
        System.out.println(result);

        // Изменяю пользователя и обновляю
        String updateResult = consumeWebService.updateUserByExchMethod();
        System.out.println(updateResult);


    }
}






