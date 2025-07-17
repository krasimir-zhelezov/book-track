package dev.zhelezov.backend.book;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/books")
public class BookController {
    @PostMapping("/")
    public ResponseEntity<Book> createBook(@RequestBody BookDto bookDto) {
        Book book = new Book(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getIsbn(), bookDto.getGenres());

        return ResponseEntity.ok().body(book);
    }  
}
