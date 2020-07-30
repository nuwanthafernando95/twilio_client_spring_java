package com.example.twilio_client_spring.controller.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {
    private static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/index")
    public ModelAndView indexPage(){
        LOGGER.info("Enter indexPage() in IndexController");
        return new ModelAndView("public/index");
    }
}
