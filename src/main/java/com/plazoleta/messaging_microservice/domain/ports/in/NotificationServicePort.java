package com.plazoleta.messaging_microservice.domain.ports.in;

import com.plazoleta.messaging_microservice.domain.model.NotificationModel;

public interface NotificationServicePort {

    NotificationModel sendSmsNotification(String phoneNumber, String message, String securityPin, Long orderId);
}
