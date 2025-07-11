package com.plazoleta.messaging_microservice.domain.utils.constants;

public class DomainMessagesConstants {

    // SMS Notification messages
    public static final String SMS_SENT_SUCCESSFULLY = "SMS notification sent successfully";
    public static final String SMS_SEND_FAILED = "Failed to send SMS notification";
    public static final String INVALID_PHONE_NUMBER = "Invalid phone number provided";
    public static final String EMPTY_MESSAGE_CONTENT = "Message content cannot be empty";
    
    // Validation messages
    public static final String NOTIFICATION_NOT_FOUND = "Notification not found";
    public static final String NOTIFICATION_ALREADY_SENT = "Notification has already been sent";
    
    private DomainMessagesConstants() {
        throw new IllegalStateException("Utility class");
    }
}
