package com.juzzt.chatbot.controller;

import com.juzzt.chatbot.model.JazzArtist;
import com.juzzt.chatbot.service.JazzArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/artists")
public class JazzArtistController {

    private final JazzArtistService jazzArtistService;

    @Autowired
    public JazzArtistController(JazzArtistService jazzArtistService) {
        this.jazzArtistService = jazzArtistService;
    }

    @GetMapping
    public ResponseEntity<List<JazzArtist>> getAllArtists() {
        List<JazzArtist> artists = jazzArtistService.getAllArtists();
        return ResponseEntity.ok(artists);
    }

    @GetMapping("/{name}")
    public ResponseEntity<JazzArtist> getArtistByName(@PathVariable String name) {
        Optional<JazzArtist> artist = jazzArtistService.getArtistByName(name);
        return artist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/genre/{genreName}")
    public ResponseEntity<List<JazzArtist>> getArtistsByGenre(@PathVariable String genreName) {
        List<JazzArtist> artists = jazzArtistService.getArtistsByGenre(genreName);
        return ResponseEntity.ok(artists);
    }

    @PostMapping
    public ResponseEntity<JazzArtist> createArtist(@RequestBody JazzArtist artist) {
        JazzArtist createdArtist = jazzArtistService.createArtist(artist);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArtist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JazzArtist> updateArtist(@PathVariable Long id, @RequestBody JazzArtist artistDetails) {
        JazzArtist updatedArtist = jazzArtistService.updateArtist(id, artistDetails);
        return ResponseEntity.ok(updatedArtist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        jazzArtistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }
}
