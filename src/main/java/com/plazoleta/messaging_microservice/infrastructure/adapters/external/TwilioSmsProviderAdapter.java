package com.plazoleta.messaging_microservice.infrastructure.adapters.external;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.plazoleta.messaging_microservice.domain.ports.out.SmsProviderPort;

@Component
public class TwilioSmsProviderAdapter implements SmsProviderPort {

    private static final Logger logger = LoggerFactory.getLogger(TwilioSmsProviderAdapter.class);

    @Override
    public String sendSms(String phoneNumber, String message) {
        // TODO: Implement real Twilio integration
        // For now, we'll simulate the SMS sending
        
        logger.info("📱 SMS SENT SIMULATION");
        logger.info("To: {}", phoneNumber);
        logger.info("Message: {}", message);
        logger.info("Provider: Twilio");
        logger.info("Status: SUCCESS");
        
        // Simulate a successful response from Twilio
        String messageId = "SM" + System.currentTimeMillis();
        logger.info("MessageId: {}", messageId);
        
        return messageId;
    }
}
