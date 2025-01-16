package com.exmp315;

import com.exmp315.configuration.MyConfig;
import com.exmp315.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Application {


    public static void main(String[] args) {

        User updatedUser = new User(3L, "Thomas", "Shelby", (byte) 39);



        // РАБОЧИЙ
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);

        ConsumeWebService consumeWebService = context.getBean("consumeWebService",
                ConsumeWebService.class);


        String sessionID = consumeWebService.getListUsersExchMethod();
        System.out.println(sessionID);

        String result = consumeWebService.addUserByExchMethod();
        System.out.println(result);


        // Создаю объект обновленного пользователя, для изм

        String updateResult = consumeWebService.updateUserByExchMethod();
        System.out.println(updateResult);


    }
}






