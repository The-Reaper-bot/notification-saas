package com.saas.notification.notification;

import org.springframework.stereotype.Component;

@Component
public class SmsSender {

    public boolean send(String phoneNumber, String message) {

        // Simulate SMS sending
        System.out.println("Sending SMS to " + phoneNumber);
        System.out.println("Message: " + message);

        // Always succeed for now
        return true;
    }
}
