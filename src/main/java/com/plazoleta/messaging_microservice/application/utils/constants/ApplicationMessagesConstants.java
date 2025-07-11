package com.plazoleta.messaging_microservice.application.utils.constants;

public class ApplicationMessagesConstants {

    // Success messages
    public static final String NOTIFICATION_SENT_SUCCESSFULLY = "Notification sent successfully";
    public static final String NOTIFICATION_CREATED_SUCCESSFULLY = "Notification created successfully";
    
    // Error messages
    public static final String NOTIFICATION_SEND_ERROR = "Error sending notification";
    public static final String INVALID_REQUEST_DATA = "Invalid request data provided";
    public static final String NOTIFICATION_SERVICE_UNAVAILABLE = "Notification service is currently unavailable";
    
    private ApplicationMessagesConstants() {
        throw new IllegalStateException("Utility class");
    }
}
