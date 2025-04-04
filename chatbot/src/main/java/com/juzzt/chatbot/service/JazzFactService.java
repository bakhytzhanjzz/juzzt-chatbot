package com.juzzt.chatbot.service;

import com.juzzt.chatbot.model.JazzFact;
import com.juzzt.chatbot.repository.JazzFactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JazzFactService {

    private final JazzFactRepository jazzFactRepository;

    @Autowired
    public JazzFactService(JazzFactRepository jazzFactRepository) {
        this.jazzFactRepository = jazzFactRepository;
    }

    public JazzFact getLatestFact() {
        return jazzFactRepository.findTopByOrderByIdDesc();
    }

    public JazzFact createFact(JazzFact fact) {
        return jazzFactRepository.save(fact);
    }
}
