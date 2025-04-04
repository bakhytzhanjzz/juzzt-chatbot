package com.juzzt.chatbot.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class JazzAlbum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String releaseDate;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private JazzArtist artist;
}
