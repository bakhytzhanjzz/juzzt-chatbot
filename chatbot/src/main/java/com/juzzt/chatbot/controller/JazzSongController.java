package com.juzzt.chatbot.controller;

import com.juzzt.chatbot.model.JazzSong;
import com.juzzt.chatbot.service.JazzSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/songs")
public class JazzSongController {

    private final JazzSongService jazzSongService;

    @Autowired
    public JazzSongController(JazzSongService jazzSongService) {
        this.jazzSongService = jazzSongService;
    }

    @GetMapping
    public ResponseEntity<List<JazzSong>> getAllSongs() {
        List<JazzSong> songs = jazzSongService.getAllSongs();
        return ResponseEntity.ok(songs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JazzSong> getSongById(@PathVariable Long id) {
        Optional<JazzSong> song = jazzSongService.getSongById(id);
        return song.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/album/{albumTitle}")
    public ResponseEntity<List<JazzSong>> getSongsByAlbumTitle(@PathVariable String albumTitle) {
        List<JazzSong> songs = jazzSongService.getSongsByAlbumTitle(albumTitle);
        return ResponseEntity.ok(songs);
    }

    @GetMapping("/search")
    public ResponseEntity<List<JazzSong>> searchSongsByTitle(@RequestParam String title) {
        List<JazzSong> songs = jazzSongService.searchSongsByTitle(title);
        return ResponseEntity.ok(songs);
    }

    @PostMapping
    public ResponseEntity<JazzSong> createSong(@RequestBody JazzSong song) {
        JazzSong createdSong = jazzSongService.createSong(song);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSong);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JazzSong> updateSong(@PathVariable Long id, @RequestBody JazzSong songDetails) {
        JazzSong updatedSong = jazzSongService.updateSong(id, songDetails);
        return ResponseEntity.ok(updatedSong);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        jazzSongService.deleteSong(id);
        return ResponseEntity.noContent().build();
    }
}
