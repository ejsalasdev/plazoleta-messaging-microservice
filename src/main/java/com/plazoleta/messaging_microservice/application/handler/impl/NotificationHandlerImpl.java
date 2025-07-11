package com.plazoleta.messaging_microservice.application.handler.impl;

import org.springframework.stereotype.Service;

import com.plazoleta.messaging_microservice.application.dto.request.SendSmsRequest;
import com.plazoleta.messaging_microservice.application.dto.response.NotificationResponse;
import com.plazoleta.messaging_microservice.application.handler.NotificationHandler;
import com.plazoleta.messaging_microservice.domain.model.NotificationModel;
import com.plazoleta.messaging_microservice.domain.ports.in.NotificationServicePort;

@Service
public class NotificationHandlerImpl implements NotificationHandler {

    private final NotificationServicePort notificationServicePort;

    public NotificationHandlerImpl(NotificationServicePort notificationServicePort) {
        this.notificationServicePort = notificationServicePort;
    }

    @Override
    public NotificationResponse sendSmsNotification(SendSmsRequest request) {
        NotificationModel notification = notificationServicePort.sendSmsNotification(
                request.getPhoneNumber(),
                request.getMessage(),
                request.getSecurityPin(),
                request.getOrderId()
        );

        return new NotificationResponse(
                notification.getId(),
                notification.getPhoneNumber(),
                notification.getMessage(),
                notification.getOrderId(),
                notification.getStatus().toString(),
                notification.getCreatedAt(),
                notification.getSentAt(),
                notification.getProviderResponse()
        );
    }
}
