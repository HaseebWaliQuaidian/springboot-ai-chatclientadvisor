package com.example.chat_client_advisor_exploration.config;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatClientConfig {

    @Bean
    ChatMemory chatMemory() {
        return MessageWindowChatMemory.builder()
                .chatMemoryRepository(new InMemoryChatMemoryRepository())
                .maxMessages(20)
                .build();
    }

    @Bean
    ChatClient chatClient(ChatClient.Builder builder, ChatMemory chatMemory) {
        return builder
                    .defaultSystem("You are a helpful travelling assistant.")
                    .defaultAdvisors(
                            MessageChatMemoryAdvisor.builder(chatMemory).build(),
                            SafeGuardAdvisor.builder().sensitiveWords(List.of("illegal","smuggling","terrorism","drug","narcotics")).build(),
                            SimpleLoggerAdvisor.builder().build()
                    )
                    .build();
    }
}
