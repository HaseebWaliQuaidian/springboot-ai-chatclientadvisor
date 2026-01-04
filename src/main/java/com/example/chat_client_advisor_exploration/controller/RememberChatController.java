package com.example.chat_client_advisor_exploration.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/remember")
public class RememberChatController {

    private final ChatClient chatClient;

    public RememberChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @PostMapping("/chat")
    public String getChatHistory(@RequestParam String message) {
        return chatClient.prompt().user(message).call().content();
    }
}
