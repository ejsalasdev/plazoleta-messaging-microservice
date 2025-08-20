package com.plazoleta.messaging_microservice.domain.ports.out;

public interface SmsProviderPort {

    String sendSms(String phoneNumber, String message);
}
