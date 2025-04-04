package com.juzzt.chatbot.controller;

import com.juzzt.chatbot.model.JazzAlbum;
import com.juzzt.chatbot.service.JazzAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/albums")
public class JazzAlbumController {

    private final JazzAlbumService jazzAlbumService;

    @Autowired
    public JazzAlbumController(JazzAlbumService jazzAlbumService) {
        this.jazzAlbumService = jazzAlbumService;
    }

    @GetMapping
    public ResponseEntity<List<JazzAlbum>> getAllAlbums() {
        List<JazzAlbum> albums = jazzAlbumService.getAllAlbums();
        return ResponseEntity.ok(albums);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JazzAlbum> getAlbumById(@PathVariable Long id) {
        Optional<JazzAlbum> album = jazzAlbumService.getAlbumById(id);
        return album.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/artist/{artistName}")
    public ResponseEntity<List<JazzAlbum>> getAlbumsByArtistName(@PathVariable String artistName) {
        List<JazzAlbum> albums = jazzAlbumService.getAlbumsByArtistName(artistName);
        return ResponseEntity.ok(albums);
    }

    @GetMapping("/releaseDate/{releaseDate}")
    public ResponseEntity<List<JazzAlbum>> getAlbumsByReleaseDate(@PathVariable String releaseDate) {
        List<JazzAlbum> albums = jazzAlbumService.getAlbumsByReleaseDate(releaseDate);
        return ResponseEntity.ok(albums);
    }

    @PostMapping
    public ResponseEntity<JazzAlbum> createAlbum(@RequestBody JazzAlbum album) {
        JazzAlbum createdAlbum = jazzAlbumService.createAlbum(album);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAlbum);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JazzAlbum> updateAlbum(@PathVariable Long id, @RequestBody JazzAlbum albumDetails) {
        JazzAlbum updatedAlbum = jazzAlbumService.updateAlbum(id, albumDetails);
        return ResponseEntity.ok(updatedAlbum);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        jazzAlbumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }
}
