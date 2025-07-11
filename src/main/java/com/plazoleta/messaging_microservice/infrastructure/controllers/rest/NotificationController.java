package com.plazoleta.messaging_microservice.infrastructure.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plazoleta.messaging_microservice.application.dto.request.SendSmsRequest;
import com.plazoleta.messaging_microservice.application.dto.response.NotificationResponse;
import com.plazoleta.messaging_microservice.application.handler.NotificationHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/notifications")
@Tag(name = "Notification Management", description = "SMS notification operations")
public class NotificationController {

    private final NotificationHandler notificationHandler;

    public NotificationController(NotificationHandler notificationHandler) {
        this.notificationHandler = notificationHandler;
    }

    @PostMapping("/sms")
    @Operation(summary = "Send SMS notification", 
               description = "Send SMS notification to customer when order is ready with security PIN")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "SMS sent successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<NotificationResponse> sendSmsNotification(@Valid @RequestBody SendSmsRequest request) {
        try {
            NotificationResponse response = notificationHandler.sendSmsNotification(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
