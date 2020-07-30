package com.example.twilio_client_spring.controller.api;

import com.example.twilio_client_spring.service.CallService;
import com.twilio.twiml.TwiMLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/call")
public class CallController {
    private Logger LOGGER = LoggerFactory.getLogger(CallController.class);

    @Autowired
    CallService callService;

    @RequestMapping(value = "/voice", method = RequestMethod.POST, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> detConnectClientVoice(@RequestParam(value = "To", required = false) String to) throws TwiMLException {
        LOGGER.info("Enter getIncommingVoiceMessage() in CallController [to - {}]", to);
        String xml = callService.createVoiceResponse(to);
        LOGGER.info("XML VALUE - {}", xml);
        return new ResponseEntity<>(xml, HttpStatus.OK);
    }

    @RequestMapping(value = "/direct-client", method = RequestMethod.POST, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> getDirectClientVoice() throws TwiMLException {
        LOGGER.info("Enter getDirectClientVoice() in CallController [to - {}]");
        String xml = callService.getRedirectToClientVoiceResponse();
        LOGGER.info("XML VALUE - {}", xml);
        return new ResponseEntity<>(xml, HttpStatus.OK);
    }
}
