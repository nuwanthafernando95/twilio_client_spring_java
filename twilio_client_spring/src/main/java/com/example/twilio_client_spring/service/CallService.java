package com.example.twilio_client_spring.service;

import com.example.twilio_client_spring.constants.AppMessages;
import com.example.twilio_client_spring.constants.AppStatics;
import com.github.javafaker.App;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Client;
import com.twilio.twiml.voice.Dial;
import com.twilio.twiml.voice.Number;
import com.twilio.twiml.voice.Say;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CallService {
    public Logger LOGGER = LoggerFactory.getLogger(CallService.class);

    @Value("${twilio.trial_number}")
    private String calledId;

    public String createVoiceResponse(String to) {
        VoiceResponse voiceTwimlResponse;

        if (to != null) {

            Dial.Builder dialBuilder = new Dial.Builder()
                    .callerId(calledId);

            Dial.Builder dialBuilderWithReceiver = addChildReceiver(dialBuilder, to);

            voiceTwimlResponse = new VoiceResponse.Builder()
                    .dial(dialBuilderWithReceiver.build())
                    .build();

        } else {
            voiceTwimlResponse = new VoiceResponse.Builder()
                    .say(new Say.Builder(AppMessages.DEFAULT_CALL_MESSAGE).build())
                    .build();
        }

        return voiceTwimlResponse.toXml();
    }

    public String getRedirectToClientVoiceResponse() {
        LOGGER.info("Enter getRedirectToClientVoiceResponse() in CallService");
        VoiceResponse voiceTwimlResponse;

        Say say = new Say.Builder(AppMessages.REDIRECT_WAIT_MESSAGE).build();
        Client client = new Client.Builder(AppStatics.current_logged_user).build();
        Dial dial = new Dial.Builder().client(client).build();

        voiceTwimlResponse = new VoiceResponse.Builder().say(say).dial(dial).build();
        return voiceTwimlResponse.toXml();
    }


    private Dial.Builder addChildReceiver(Dial.Builder builder, String to) {
        LOGGER.info("Enter addChildReceiver() in CallService");
        // wrap the phone number or client name in the appropriate TwiML verb
        // by checking if the number given has only digits and format symbols
        if (isPhoneNumber(to)) {
            return builder.number(new Number.Builder(to).build());
        }
        return builder.client(new Client.Builder(to).build());
    }

    private boolean isPhoneNumber(String to) {
        LOGGER.info("Enter isPhoneNumber() in CallService [to - {}]", to);
        return to.matches("^[\\d\\+\\-\\(\\) ]+$");
    }
}
