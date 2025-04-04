package com.juzzt.chatbot.service;

import com.juzzt.chatbot.model.JazzArtist;
import com.juzzt.chatbot.model.JazzGenre;
import com.juzzt.chatbot.repository.JazzArtistRepository;
import com.juzzt.chatbot.repository.JazzGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JazzArtistService {

    private final JazzArtistRepository jazzArtistRepository;
    private final JazzGenreRepository jazzGenreRepository;

    @Autowired
    public JazzArtistService(JazzArtistRepository jazzArtistRepository, JazzGenreRepository jazzGenreRepository) {
        this.jazzArtistRepository = jazzArtistRepository;
        this.jazzGenreRepository = jazzGenreRepository;
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

    @Transactional
    public JazzArtist createArtist(JazzArtist artist) {
        // Handle genre persistence: save new genres or link existing ones
        artist.getGenres().forEach(genre -> {
            // Use Optional to check if the genre exists in the database
            Optional<JazzGenre> existingGenre = Optional.ofNullable(jazzGenreRepository.findByName(genre.getName()));
            // If the genre doesn't exist, save it. Otherwise, link the existing genre.
            if (existingGenre.isPresent()) {
                genre.setId(existingGenre.get().getId());
            } else {
                JazzGenre savedGenre = jazzGenreRepository.save(genre);
                genre.setId(savedGenre.getId()); // Ensure the genre is linked correctly
            }
        });

        return jazzArtistRepository.save(artist);
    }

    @Transactional
    public JazzArtist updateArtist(Long id, JazzArtist artistDetails) {
        JazzArtist artist = jazzArtistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found with id " + id));

        artist.setName(artistDetails.getName());
        artist.setBiography(artistDetails.getBiography());
        artist.setBirthDate(artistDetails.getBirthDate());
        artist.setDeathDate(artistDetails.getDeathDate());

        // Handle genres: make sure the genres are saved or linked correctly
        artistDetails.getGenres().forEach(genre -> {
            Optional<JazzGenre> existingGenre = Optional.ofNullable(jazzGenreRepository.findByName(genre.getName()));
            if (existingGenre.isPresent()) {
                genre.setId(existingGenre.get().getId());
            } else {
                JazzGenre savedGenre = jazzGenreRepository.save(genre);
                genre.setId(savedGenre.getId());
            }
        });

        artist.setGenres(artistDetails.getGenres());

        return jazzArtistRepository.save(artist);
    }

    public void deleteArtist(Long id) {
        jazzArtistRepository.deleteById(id);
    }
}
