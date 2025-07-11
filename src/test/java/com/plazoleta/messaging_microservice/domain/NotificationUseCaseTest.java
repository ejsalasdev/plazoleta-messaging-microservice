package com.plazoleta.messaging_microservice.domain;

import com.plazoleta.messaging_microservice.domain.model.NotificationModel;
import com.plazoleta.messaging_microservice.domain.ports.out.SmsProviderPort;
import com.plazoleta.messaging_microservice.domain.usecases.NotificationUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationUseCaseTest {

    @Mock
    private SmsProviderPort smsProviderPort;

    private NotificationUseCase notificationUseCase;

    @BeforeEach
    void setUp() {
        notificationUseCase = new NotificationUseCase(smsProviderPort);
    }

    @Test
    void when_sendSmsNotificationWithValidData_Expect_SuccessfulNotification() {
        // Arrange
        String phoneNumber = "+1234567890";
        String message = "Your order is ready!";
        String securityPin = "123456";
        Long orderId = 1L;
        String providerResponse = "SM12345";

        when(smsProviderPort.sendSms(phoneNumber, message)).thenReturn(providerResponse);

        // Act
        NotificationModel result = notificationUseCase.sendSmsNotification(phoneNumber, message, securityPin, orderId);

        // Assert
        assertNotNull(result);
        assertEquals("SENT", result.getStatus().toString());
        assertEquals(providerResponse, result.getProviderResponse());
        verify(smsProviderPort, times(1)).sendSms(phoneNumber, message);
    }

    @Test
    void when_sendSmsNotificationAndProviderFails_Expect_FailedNotification() {
        // Arrange
        String phoneNumber = "+1234567890";
        String message = "Your order is ready!";
        String securityPin = "123456";
        Long orderId = 1L;
        String errorMessage = "Provider error";

        when(smsProviderPort.sendSms(phoneNumber, message)).thenThrow(new RuntimeException(errorMessage));

        // Act
        NotificationModel result = notificationUseCase.sendSmsNotification(phoneNumber, message, securityPin, orderId);

        // Assert
        assertNotNull(result);
        assertEquals("FAILED", result.getStatus().toString());
        assertEquals(errorMessage, result.getProviderResponse());
        verify(smsProviderPort, times(1)).sendSms(phoneNumber, message);
    }

    @Test
    void when_sendSmsNotificationWithNullPhoneNumber_Expect_IllegalArgumentException() {
        // Arrange
        String message = "Your order is ready!";
        String securityPin = "123456";
        Long orderId = 1L;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationUseCase.sendSmsNotification(null, message, securityPin, orderId);
        });
        verify(smsProviderPort, never()).sendSms(anyString(), anyString());
    }

    @Test
    void when_sendSmsNotificationWithEmptyMessage_Expect_IllegalArgumentException() {
        // Arrange
        String phoneNumber = "+1234567890";
        String securityPin = "123456";
        Long orderId = 1L;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            notificationUseCase.sendSmsNotification(phoneNumber, "", securityPin, orderId);
        });
        verify(smsProviderPort, never()).sendSms(anyString(), anyString());
    }
}
