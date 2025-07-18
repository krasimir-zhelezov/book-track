package dev.zhelezov.backend.book;

import java.util.List;

public class BookDto {
    private String title;
    private String author;
    private String isbn;
    private List<String> genres;
    private int published;

    public BookDto(String title, String author, String isbn, List<String> genres, int published) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genres = genres;
        this.published = published;
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
    public int getPublished() {
        return published;
    }
    public void setPublished(int published) {
        this.published = published;
    }
}