package com.exmp315;

import com.exmp315.configuration.MyConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Application {


    public static void main(String[] args) {

        // РАБОЧИЙ
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);

        ConsumeWebService consumeWebService = context.getBean("consumeWebService",
                ConsumeWebService.class);


        consumeWebService.useExchangeMethodRestTempl();
        String useExchRestTempl = consumeWebService.useExchangeMethodRestTempl();
        System.out.println(useExchRestTempl);


        consumeWebService.getListUsersExchMethod();
        String listUserExchRestTempl = consumeWebService.getListUsersExchMethod();
        System.out.println(listUserExchRestTempl);


    }
}




