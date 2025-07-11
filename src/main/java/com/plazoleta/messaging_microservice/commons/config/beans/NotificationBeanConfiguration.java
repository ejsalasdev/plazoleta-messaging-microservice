package com.plazoleta.messaging_microservice.commons.config.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.plazoleta.messaging_microservice.domain.ports.in.NotificationServicePort;
import com.plazoleta.messaging_microservice.domain.ports.out.SmsProviderPort;
import com.plazoleta.messaging_microservice.domain.usecases.NotificationUseCase;

@Configuration
public class NotificationBeanConfiguration {

    @Bean
    public NotificationServicePort notificationServicePort(SmsProviderPort smsProviderPort) {
        return new NotificationUseCase(smsProviderPort);
    }
}
