package dev.zhelezov.backend.book;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    private String author;
    private String isbn;
    private List<String> genres;
    private int published;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;


    public Book(String title, String author, String isbn, List<String> genres, int published) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genres = genres;
        this.published = published;
    }
}
