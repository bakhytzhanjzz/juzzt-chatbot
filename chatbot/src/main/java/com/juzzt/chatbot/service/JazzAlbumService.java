package com.juzzt.chatbot.service;

import com.juzzt.chatbot.model.JazzAlbum;
import com.juzzt.chatbot.repository.JazzAlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JazzAlbumService {

    private final JazzAlbumRepository jazzAlbumRepository;

    @Autowired
    public JazzAlbumService(JazzAlbumRepository jazzAlbumRepository) {
        this.jazzAlbumRepository = jazzAlbumRepository;
    }

    public List<JazzAlbum> getAllAlbums() {
        return jazzAlbumRepository.findAll();
    }

    public Optional<JazzAlbum> getAlbumById(Long id) {
        return jazzAlbumRepository.findById(id);
    }

    public List<JazzAlbum> getAlbumsByArtistName(String artistName) {
        return jazzAlbumRepository.findByArtistName(artistName);
    }

    public List<JazzAlbum> getAlbumsByReleaseDate(String releaseDate) {
        return jazzAlbumRepository.findByReleaseDate(releaseDate);
    }

    public JazzAlbum createAlbum(JazzAlbum album) {
        return jazzAlbumRepository.save(album);
    }

    public JazzAlbum updateAlbum(Long id, JazzAlbum albumDetails) {
        JazzAlbum album = jazzAlbumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Album not found with id " + id));

        album.setTitle(albumDetails.getTitle());
        album.setReleaseDate(albumDetails.getReleaseDate());
        album.setArtist(albumDetails.getArtist());

        return jazzAlbumRepository.save(album);
    }

    public void deleteAlbum(Long id) {
        jazzAlbumRepository.deleteById(id);
    }
}
