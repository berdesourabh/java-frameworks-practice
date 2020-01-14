package com.books.app.event;

import org.springframework.context.ApplicationEvent;

public class CustomeEvent extends ApplicationEvent {

    public CustomeEvent(Object source, String msg) {
        super(source);
    }

    @Override
    public String toString() {
        return "This is custom event";
    }
}
