package com.plazoleta.messaging_microservice.application.dto.response;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response after sending SMS notification")
public class NotificationResponse {

    @Schema(description = "Notification ID", example = "1")
    private Long id;

    @Schema(description = "Phone number where SMS was sent", example = "+573001234567")
    private String phoneNumber;

    @Schema(description = "SMS message content", example = "Your order is ready! PIN: 1234")
    private String message;

    @Schema(description = "Order ID associated with the notification", example = "12345")
    private Long orderId;

    @Schema(description = "Notification status", example = "SENT")
    private String status;

    @Schema(description = "When the notification was created", example = "2024-01-15T10:30:00")
    private LocalDateTime createdAt;

    @Schema(description = "When the SMS was sent", example = "2024-01-15T10:30:05")
    private LocalDateTime sentAt;

    @Schema(description = "Provider response message", example = "SM123456789")
    private String providerResponse;

    public NotificationResponse() {
    }

    public NotificationResponse(Long id, String phoneNumber, String message, Long orderId, 
                               String status, LocalDateTime createdAt, LocalDateTime sentAt, 
                               String providerResponse) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.orderId = orderId;
        this.status = status;
        this.createdAt = createdAt;
        this.sentAt = sentAt;
        this.providerResponse = providerResponse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public String getProviderResponse() {
        return providerResponse;
    }

    public void setProviderResponse(String providerResponse) {
        this.providerResponse = providerResponse;
    }
}
