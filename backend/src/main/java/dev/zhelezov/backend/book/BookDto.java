package dev.zhelezov.backend.book;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookDto {
    private String title;
    private String author;
    private String isbn;
    private String description;
    private List<String> genres;
    private int published;
    private LocalDateTime createdAt;
}