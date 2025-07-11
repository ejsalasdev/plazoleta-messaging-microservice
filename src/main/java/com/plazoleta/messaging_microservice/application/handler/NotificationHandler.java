package com.plazoleta.messaging_microservice.application.handler;

import com.plazoleta.messaging_microservice.application.dto.request.SendSmsRequest;
import com.plazoleta.messaging_microservice.application.dto.response.NotificationResponse;

public interface NotificationHandler {

    NotificationResponse sendSmsNotification(SendSmsRequest request);
}
