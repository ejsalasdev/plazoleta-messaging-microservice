package com.plazoleta.messaging_microservice.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Schema(description = "Request to send SMS notification")
public class SendSmsRequest {

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
    @Schema(description = "Phone number to send SMS", example = "+573001234567", required = true)
    private String phoneNumber;

    @NotBlank(message = "Message is required")
    @Schema(description = "SMS message content", example = "Your order is ready! PIN: 1234", required = true)
    private String message;

    @NotBlank(message = "Security PIN is required")
    @Schema(description = "Security PIN for order pickup", example = "1234", required = true)
    private String securityPin;

    @NotNull(message = "Order ID is required")
    @Schema(description = "Order ID associated with the notification", example = "12345", required = true)
    private Long orderId;

    public SendSmsRequest() {
    }

    public SendSmsRequest(String phoneNumber, String message, String securityPin, Long orderId) {
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.securityPin = securityPin;
        this.orderId = orderId;
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
}
