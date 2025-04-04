package com.juzzt.chatbot.service;

import com.juzzt.chatbot.model.ChatSession;
import com.juzzt.chatbot.repository.ChatSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatSessionService {

    private final ChatSessionRepository chatSessionRepository;

    @Autowired
    public ChatSessionService(ChatSessionRepository chatSessionRepository) {
        this.chatSessionRepository = chatSessionRepository;
    }

    public Optional<ChatSession> getSessionByUserId(String userId) {
        return chatSessionRepository.findByUserId(userId);
    }

    public ChatSession createSession(ChatSession session) {
        return chatSessionRepository.save(session);
    }

    public void deleteSession(Long id) {
        chatSessionRepository.deleteById(id);
    }
}
