package com.juzzt.chatbot.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class JazzSong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String duration;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private JazzAlbum album;
}
