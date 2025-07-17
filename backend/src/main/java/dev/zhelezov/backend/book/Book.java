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

    public Book() {
        
    }

    public Book(String title, String author, String isbn, List<String> genres) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genres = genres;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public List<String> getGenres() {
        return genres;
    }
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}
