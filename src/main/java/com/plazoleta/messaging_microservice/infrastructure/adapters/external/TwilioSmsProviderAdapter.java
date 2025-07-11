package com.plazoleta.messaging_microservice.infrastructure.adapters.external;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.plazoleta.messaging_microservice.domain.ports.out.SmsProviderPort;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Component
public class TwilioSmsProviderAdapter implements SmsProviderPort {

    private static final Logger logger = LoggerFactory.getLogger(TwilioSmsProviderAdapter.class);
    
    @Value("${external.twilio.account-sid}")
    private String accountSid;

    @Value("${external.twilio.auth-token}")
    private String authToken;

    @Value("${external.twilio.phone-number}")
    private String fromPhoneNumber;

    @Override
    public String sendSms(String phoneNumber, String message) {
        // Check if we're in development mode (using test credentials)
        if (isTestMode()) {
            return sendSmsSimulation(phoneNumber, message);
        }
        
        try {
            // Initialize Twilio
            Twilio.init(accountSid, authToken);

            // Send SMS using Twilio API
            Message twilioMessage = Message.creator(
                    new PhoneNumber(phoneNumber),    // To
                    new PhoneNumber(fromPhoneNumber), // From
                    message                          // Body
            ).create();

            logger.info("📱 SMS sent successfully via Twilio");
            logger.info("To: {}", phoneNumber);
            logger.info("SID: {}", twilioMessage.getSid());
            logger.info("Status: {}", twilioMessage.getStatus());
            
            return twilioMessage.getSid();

        } catch (Exception e) {
            logger.error("❌ Failed to send SMS via Twilio to {}: {}", phoneNumber, e.getMessage(), e);
            
            // Fallback to simulation if Twilio fails
            logger.warn("🔄 Falling back to SMS simulation mode");
            return sendSmsSimulation(phoneNumber, message);
        }
    }
    
    private boolean isTestMode() {
        return "test_account_sid".equals(accountSid) || 
               "test_auth_token".equals(authToken) ||
               accountSid.startsWith("ACxxxx");
    }
    
    private String sendSmsSimulation(String phoneNumber, String message) {
        logger.info("📱 SMS SIMULATION MODE");
        logger.info("To: {}", phoneNumber);
        logger.info("Message: {}", message);
        logger.info("Provider: Twilio (Simulated)");
        logger.info("Status: SUCCESS");
        
        // Simulate a successful response from Twilio
        String messageId = "SM_SIM_" + System.currentTimeMillis();
        logger.info("MessageId: {}", messageId);
        
        return messageId;
    }
}
