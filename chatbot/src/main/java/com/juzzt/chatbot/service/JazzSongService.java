package com.juzzt.chatbot.service;

import com.juzzt.chatbot.model.JazzSong;
import com.juzzt.chatbot.repository.JazzSongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JazzSongService {

    private final JazzSongRepository jazzSongRepository;

    @Autowired
    public JazzSongService(JazzSongRepository jazzSongRepository) {
        this.jazzSongRepository = jazzSongRepository;
    }

    public List<JazzSong> getAllSongs() {
        return jazzSongRepository.findAll();
    }

    public Optional<JazzSong> getSongById(Long id) {
        return jazzSongRepository.findById(id);
    }

    public List<JazzSong> getSongsByAlbumTitle(String albumTitle) {
        return jazzSongRepository.findByAlbumTitle(albumTitle);
    }

    public List<JazzSong> searchSongsByTitle(String title) {
        return jazzSongRepository.findByTitleContainingIgnoreCase(title);
    }

    public JazzSong createSong(JazzSong song) {
        return jazzSongRepository.save(song);
    }

    public JazzSong updateSong(Long id, JazzSong songDetails) {
        JazzSong song = jazzSongRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Song not found with id " + id));

        song.setTitle(songDetails.getTitle());
        song.setAlbum(songDetails.getAlbum());
        song.setDuration(songDetails.getDuration());

        return jazzSongRepository.save(song);
    }

    public void deleteSong(Long id) {
        jazzSongRepository.deleteById(id);
    }
}
