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
    public ResponseEntity<Book> createBook() {
        Book book = new Book("Dune", "Frank Herbert", "9780441013593", new ArrayList<>(Arrays.asList("sci-fim", "classic")));

        return ResponseEntity.ok().body(book);
    }  
}
