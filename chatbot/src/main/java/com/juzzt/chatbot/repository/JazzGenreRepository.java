package com.juzzt.chatbot.repository;

import com.juzzt.chatbot.model.JazzGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JazzGenreRepository extends JpaRepository<JazzGenre, Long> {

    JazzGenre findByName(String name); // Find genre by name
}
