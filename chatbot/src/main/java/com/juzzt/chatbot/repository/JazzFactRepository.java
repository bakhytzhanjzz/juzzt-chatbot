package com.juzzt.chatbot.repository;

import com.juzzt.chatbot.model.JazzFact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JazzFactRepository extends JpaRepository<JazzFact, Long> {

    JazzFact findTopByOrderByIdDesc(); // Fetch the most recent fact (useful for getting the latest trivia)
}
