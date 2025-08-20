package com.plazoleta.messaging_microservice.domain.model;

import java.time.LocalDateTime;

import com.plazoleta.messaging_microservice.domain.enums.NotificationStatus;

public class NotificationModel {

    private Long id;
    private String phoneNumber;
    private String message;
    private String securityPin;
    private Long orderId;
    private NotificationStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime sentAt;
    private String providerResponse;

    public NotificationModel() {
    }

    public NotificationModel(String phoneNumber, String message, String securityPin, Long orderId) {
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.securityPin = securityPin;
        this.orderId = orderId;
        this.status = NotificationStatus.PENDING;
        this.createdAt = LocalDateTime.now();
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

    public String getSecurityPin() {
        return securityPin;
    }

    public void setSecurityPin(String securityPin) {
        this.securityPin = securityPin;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
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

    public void markAsSent(String providerResponse) {
        this.status = NotificationStatus.SENT;
        this.sentAt = LocalDateTime.now();
        this.providerResponse = providerResponse;
    }

    public void markAsFailed(String errorMessage) {
        this.status = NotificationStatus.FAILED;
        this.providerResponse = errorMessage;
    }
}
