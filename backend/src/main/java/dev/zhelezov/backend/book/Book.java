package dev.zhelezov.backend.book;

import java.util.List;
import java.util.UUID;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    private String author;
    private String isbn;
    private List<String> genres;
    // private List<String> chapters;
    // private int pages;
}
