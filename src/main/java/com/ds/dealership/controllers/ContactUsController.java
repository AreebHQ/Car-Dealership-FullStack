package com.ds.dealership.controllers;

import com.ds.dealership.entities.Message;
import com.ds.dealership.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ContactUsController {

    @Autowired
    MessageRepository messages;

    @PostMapping("/contactus/message")
    private String getUsed(@RequestBody Message message, HttpServletRequest request, final ModelMap model) {

        messages.save(message);
        return  "success";
    }

    @GetMapping("/contactus/getMessages")
    public List<Message> message()
    {
        List<Message> allMessages = messages.findAll();
        return allMessages;
    }
}
