package com.canyin.canyinbackend.controller;

import com.canyin.canyinbackend.model.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class MesssageController {

    @GetMapping("/message")
    Message send() {
        return new Message("first message");
    }

    @PostMapping("/message")
    Message echo (@RequestBody Message message) {
        return message;
    }
}
