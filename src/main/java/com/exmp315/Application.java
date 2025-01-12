package com.exmp315;

import com.exmp315.configuration.MyConfig;
import com.exmp315.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Application {


    public static void main(String[] args) {



        // РАБОЧИЙ
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);

        ConsumeWebService consumeWebService = context.getBean("consumeWebService",
                ConsumeWebService.class);


        String listUserExchRestTempl = consumeWebService.getListUsersExchMethod();
        System.out.println(listUserExchRestTempl);

        String result = consumeWebService.addUserByExchMethod();
        System.out.println(result);

//
//        String updateResult = consumeWebService.updateUserByExchMethod(3L, "James", "Brown", (byte) 39);
//        System.out.println(updateResult);


    }
}






