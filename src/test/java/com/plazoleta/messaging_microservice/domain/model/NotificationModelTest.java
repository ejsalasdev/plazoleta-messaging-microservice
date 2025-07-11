package com.plazoleta.messaging_microservice.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.plazoleta.messaging_microservice.domain.enums.NotificationStatus;

class NotificationModelTest {

    @Test
    void when_CreateNotificationWithDefaultConstructor_Expect_AllFieldsAreNull() {
        // Arrange & Act
        NotificationModel notification = new NotificationModel();

        // Assert
        assertNull(notification.getId());
        assertNull(notification.getPhoneNumber());
        assertNull(notification.getMessage());
        assertNull(notification.getSecurityPin());
        assertNull(notification.getOrderId());
        assertNull(notification.getStatus());
        assertNull(notification.getCreatedAt());
        assertNull(notification.getSentAt());
        assertNull(notification.getProviderResponse());
    }

    @Test
    void when_CreateNotificationWithParameterizedConstructor_Expect_CorrectInitialization() {
        // Arrange
        String phoneNumber = "+573001234567";
        String message = "Your order is ready! PIN: 1234";
        String securityPin = "1234";
        Long orderId = 123L;
        LocalDateTime beforeCreation = LocalDateTime.now();

        // Act
        NotificationModel notification = new NotificationModel(phoneNumber, message, securityPin, orderId);
        LocalDateTime afterCreation = LocalDateTime.now();

        // Assert
        assertEquals(phoneNumber, notification.getPhoneNumber());
        assertEquals(message, notification.getMessage());
        assertEquals(securityPin, notification.getSecurityPin());
        assertEquals(orderId, notification.getOrderId());
        assertEquals(NotificationStatus.PENDING, notification.getStatus());
        assertNotNull(notification.getCreatedAt());
        assertNull(notification.getId());
        assertNull(notification.getSentAt());
        assertNull(notification.getProviderResponse());
        
        // Verify createdAt is between before and after
        assert(notification.getCreatedAt().isAfter(beforeCreation) || notification.getCreatedAt().isEqual(beforeCreation));
        assert(notification.getCreatedAt().isBefore(afterCreation) || notification.getCreatedAt().isEqual(afterCreation));
    }

    @Test
    void when_SetId_Expect_IdIsUpdated() {
        // Arrange
        NotificationModel notification = new NotificationModel();
        Long expectedId = 100L;

        // Act
        notification.setId(expectedId);

        // Assert
        assertEquals(expectedId, notification.getId());
    }

    @Test
    void when_SetPhoneNumber_Expect_PhoneNumberIsUpdated() {
        // Arrange
        NotificationModel notification = new NotificationModel();
        String expectedPhoneNumber = "+573009876543";

        // Act
        notification.setPhoneNumber(expectedPhoneNumber);

        // Assert
        assertEquals(expectedPhoneNumber, notification.getPhoneNumber());
    }

    @Test
    void when_SetMessage_Expect_MessageIsUpdated() {
        // Arrange
        NotificationModel notification = new NotificationModel();
        String expectedMessage = "Test message";

        // Act
        notification.setMessage(expectedMessage);

        // Assert
        assertEquals(expectedMessage, notification.getMessage());
    }

    @Test
    void when_SetSecurityPin_Expect_SecurityPinIsUpdated() {
        // Arrange
        NotificationModel notification = new NotificationModel();
        String expectedPin = "5678";

        // Act
        notification.setSecurityPin(expectedPin);

        // Assert
        assertEquals(expectedPin, notification.getSecurityPin());
    }

    @Test
    void when_SetOrderId_Expect_OrderIdIsUpdated() {
        // Arrange
        NotificationModel notification = new NotificationModel();
        Long expectedOrderId = 999L;

        // Act
        notification.setOrderId(expectedOrderId);

        // Assert
        assertEquals(expectedOrderId, notification.getOrderId());
    }

    @Test
    void when_SetStatus_Expect_StatusIsUpdated() {
        // Arrange
        NotificationModel notification = new NotificationModel();
        NotificationStatus expectedStatus = NotificationStatus.SENT;

        // Act
        notification.setStatus(expectedStatus);

        // Assert
        assertEquals(expectedStatus, notification.getStatus());
    }

    @Test
    void when_SetCreatedAt_Expect_CreatedAtIsUpdated() {
        // Arrange
        NotificationModel notification = new NotificationModel();
        LocalDateTime expectedCreatedAt = LocalDateTime.now().minusDays(1);

        // Act
        notification.setCreatedAt(expectedCreatedAt);

        // Assert
        assertEquals(expectedCreatedAt, notification.getCreatedAt());
    }

    @Test
    void when_SetSentAt_Expect_SentAtIsUpdated() {
        // Arrange
        NotificationModel notification = new NotificationModel();
        LocalDateTime expectedSentAt = LocalDateTime.now();

        // Act
        notification.setSentAt(expectedSentAt);

        // Assert
        assertEquals(expectedSentAt, notification.getSentAt());
    }

    @Test
    void when_SetProviderResponse_Expect_ProviderResponseIsUpdated() {
        // Arrange
        NotificationModel notification = new NotificationModel();
        String expectedResponse = "SMS sent successfully";

        // Act
        notification.setProviderResponse(expectedResponse);

        // Assert
        assertEquals(expectedResponse, notification.getProviderResponse());
    }

    @Test
    void when_MarkAsSent_Expect_StatusSentAndTimestampAndResponse() {
        // Arrange
        NotificationModel notification = new NotificationModel("+573001234567", "Test message", "1234", 123L);
        String providerResponse = "Message sent successfully";
        LocalDateTime beforeMarking = LocalDateTime.now();

        // Act
        notification.markAsSent(providerResponse);
        LocalDateTime afterMarking = LocalDateTime.now();

        // Assert
        assertEquals(NotificationStatus.SENT, notification.getStatus());
        assertEquals(providerResponse, notification.getProviderResponse());
        assertNotNull(notification.getSentAt());
        
        // Verify sentAt is between before and after
        assert(notification.getSentAt().isAfter(beforeMarking) || notification.getSentAt().isEqual(beforeMarking));
        assert(notification.getSentAt().isBefore(afterMarking) || notification.getSentAt().isEqual(afterMarking));
    }

    @Test
    void when_MarkAsFailed_Expect_StatusFailedAndErrorMessage() {
        // Arrange
        NotificationModel notification = new NotificationModel("+573001234567", "Test message", "1234", 123L);
        String errorMessage = "Failed to send SMS";

        // Act
        notification.markAsFailed(errorMessage);

        // Assert
        assertEquals(NotificationStatus.FAILED, notification.getStatus());
        assertEquals(errorMessage, notification.getProviderResponse());
        assertNull(notification.getSentAt()); // Should remain null for failed notifications
    }

    @Test
    void when_MarkAsFailedAfterSent_Expect_StatusFailedAndSentAtPreserved() {
        // Arrange
        NotificationModel notification = new NotificationModel("+573001234567", "Test message", "1234", 123L);
        notification.markAsSent("Initial success");
        LocalDateTime originalSentAt = notification.getSentAt();
        String errorMessage = "Later failure";

        // Act
        notification.markAsFailed(errorMessage);

        // Assert
        assertEquals(NotificationStatus.FAILED, notification.getStatus());
        assertEquals(errorMessage, notification.getProviderResponse());
        assertEquals(originalSentAt, notification.getSentAt()); // Should preserve original sentAt
    }

    @Test
    void when_MarkAsSentAfterFailed_Expect_StatusSentAndNewTimestamp() {
        // Arrange
        NotificationModel notification = new NotificationModel("+573001234567", "Test message", "1234", 123L);
        notification.markAsFailed("Initial failure");
        String successResponse = "Retry successful";
        LocalDateTime beforeRetry = LocalDateTime.now();

        // Act
        notification.markAsSent(successResponse);
        LocalDateTime afterRetry = LocalDateTime.now();

        // Assert
        assertEquals(NotificationStatus.SENT, notification.getStatus());
        assertEquals(successResponse, notification.getProviderResponse());
        assertNotNull(notification.getSentAt());
        
        // Verify sentAt is updated with new timestamp
        assert(notification.getSentAt().isAfter(beforeRetry) || notification.getSentAt().isEqual(beforeRetry));
        assert(notification.getSentAt().isBefore(afterRetry) || notification.getSentAt().isEqual(afterRetry));
    }
}
