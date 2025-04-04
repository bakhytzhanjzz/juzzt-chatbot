package com.juzzt.chatbot.service;

import com.juzzt.chatbot.model.JazzArtist;
import com.juzzt.chatbot.repository.JazzArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JazzArtistService {

    private final JazzArtistRepository jazzArtistRepository;

    @Autowired
    public JazzArtistService(JazzArtistRepository jazzArtistRepository) {
        this.jazzArtistRepository = jazzArtistRepository;
    }

    public List<JazzArtist> getAllArtists() {
        return jazzArtistRepository.findAll();
    }

    public Optional<JazzArtist> getArtistByName(String name) {
        return jazzArtistRepository.findByName(name);
    }

    public List<JazzArtist> getArtistsByGenre(String genreName) {
        return jazzArtistRepository.findByGenresName(genreName);
    }

    public JazzArtist createArtist(JazzArtist artist) {
        return jazzArtistRepository.save(artist);
    }

    public JazzArtist updateArtist(Long id, JazzArtist artistDetails) {
        JazzArtist artist = jazzArtistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found with id " + id));

        artist.setName(artistDetails.getName());
        artist.setBiography(artistDetails.getBiography());
        artist.setBirthDate(artistDetails.getBirthDate());
        artist.setDeathDate(artistDetails.getDeathDate());
        artist.setGenres(artistDetails.getGenres());

        return jazzArtistRepository.save(artist);
    }

    public void deleteArtist(Long id) {
        jazzArtistRepository.deleteById(id);
    }
}
