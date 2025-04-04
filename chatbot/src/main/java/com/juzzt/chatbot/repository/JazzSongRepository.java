package com.juzzt.chatbot.repository;

import com.juzzt.chatbot.model.JazzSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JazzSongRepository extends JpaRepository<JazzSong, Long> {

    List<JazzSong> findByAlbumTitle(String albumTitle); // Find songs by album title

    List<JazzSong> findByTitleContainingIgnoreCase(String title); // Search songs by partial title
}
