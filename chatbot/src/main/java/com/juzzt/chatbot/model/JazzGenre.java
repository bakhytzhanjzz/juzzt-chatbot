package com.juzzt.chatbot.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JazzGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public JazzGenre(String name) {
        this.name = name;
    }

    public static JazzGenre fromString(String name) {
        return new JazzGenre(name);
    }
}

