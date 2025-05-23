package com.baeldung.cdi2observers.observers;

import com.baeldung.cdi2observers.events.ExampleEvent;

import jakarta.annotation.Priority;
import jakarta.enterprise.event.Observes;

public class AnotherExampleEventObserver {
    
    public String onEvent(@Observes @Priority(2) ExampleEvent event) {
        return event.getEventMessage();
    }   
}
