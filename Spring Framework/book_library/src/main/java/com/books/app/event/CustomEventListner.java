package com.books.app.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CustomEventListner implements ApplicationListener<CustomeEvent> {
    @Override
    public void onApplicationEvent(CustomeEvent customeEvent) {
        System.out.print(customeEvent.toString());
    }
}
