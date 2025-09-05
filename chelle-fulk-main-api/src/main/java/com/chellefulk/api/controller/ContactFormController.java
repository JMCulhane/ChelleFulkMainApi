package com.chellefulk.api.controller;

import com.chellefulk.api.dto.ContactFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import com.chellefulk.api.service.ContactFormService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactFormController {

    @Autowired
    private ContactFormService contactFormService;

    @PostMapping
    public java.util.Map<String, Object> sendContactEmail(@RequestBody ContactFormDTO form) {
        boolean success = contactFormService.sendContactEmail(form);
        java.util.Map<String, Object> response = new java.util.HashMap<>();
        response.put("success", success);
        if (success) {
            response.put("message", "Message sent successfully.");
        } else {
            response.put("message", "Failed to send message. Please try again later.");
        }
        return response;
    }
}
