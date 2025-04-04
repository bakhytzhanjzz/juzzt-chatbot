package com.juzzt.chatbot.repository;

import com.juzzt.chatbot.model.JazzArtist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JazzArtistRepository extends JpaRepository<JazzArtist, Long> {

    Optional<JazzArtist> findByName(String name); // Find artist by name

    List<JazzArtist> findByGenresName(String genreName); // Find artists by genre

}
