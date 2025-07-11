package com.plazoleta.messaging_microservice.domain.usecases;

import com.plazoleta.messaging_microservice.domain.model.NotificationModel;
import com.plazoleta.messaging_microservice.domain.ports.in.NotificationServicePort;
import com.plazoleta.messaging_microservice.domain.ports.out.SmsProviderPort;
import com.plazoleta.messaging_microservice.domain.utils.constants.DomainMessagesConstants;

public class NotificationUseCase implements NotificationServicePort {

    private final SmsProviderPort smsProviderPort;

    public NotificationUseCase(SmsProviderPort smsProviderPort) {
        this.smsProviderPort = smsProviderPort;
    }

    @Override
    public NotificationModel sendSmsNotification(String phoneNumber, String message, String securityPin, Long orderId) {
        validatePhoneNumber(phoneNumber);
        validateMessage(message);

        NotificationModel notification = new NotificationModel(phoneNumber, message, securityPin, orderId);

        try {
            String providerResponse = smsProviderPort.sendSms(phoneNumber, message);
            notification.markAsSent(providerResponse);
        } catch (Exception e) {
            notification.markAsFailed(e.getMessage());
        }

        return notification;
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException(DomainMessagesConstants.INVALID_PHONE_NUMBER);
        }
    }

    private void validateMessage(String message) {
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException(DomainMessagesConstants.EMPTY_MESSAGE_CONTENT);
        }
    }
}
