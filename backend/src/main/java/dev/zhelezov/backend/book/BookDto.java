package dev.zhelezov.backend.book;

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
    private List<String> genres;
    private int published;
}