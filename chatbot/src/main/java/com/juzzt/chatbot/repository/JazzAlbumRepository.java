package com.juzzt.chatbot.repository;

import com.juzzt.chatbot.model.JazzAlbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JazzAlbumRepository extends JpaRepository<JazzAlbum, Long> {

    List<JazzAlbum> findByArtistName(String artistName); // Find albums by artist name

    List<JazzAlbum> findByReleaseDate(String releaseDate); // Find albums by release date
}
