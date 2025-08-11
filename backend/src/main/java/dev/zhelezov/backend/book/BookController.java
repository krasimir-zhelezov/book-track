package dev.zhelezov.backend.book;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/books")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class BookController {

    private final BookService bookService;

    @PostMapping("/")
    public ResponseEntity<Book> createBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok().body(bookService.createBook(bookDto));
    }  

    @GetMapping("/")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok().body(bookService.getAllBooks());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable UUID id) {
        return bookService.getBookById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable UUID id, @RequestBody BookDto bookDto) {
        return bookService.updateBookById(id, bookDto)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable UUID id) {
        return bookService.deleteBookById(id) ?  ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/searchByTitle/{query}")
    public ResponseEntity<List<Book>> searchByTitle(@PathVariable String query) {
        return ResponseEntity.ok().body(bookService.searchByTitle(query));
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/read/{bookId}")
    public ResponseEntity<?> readBook(@PathVariable UUID bookId) {
        try {
            bookService.readBook(bookId);
            return ResponseEntity.ok().build();
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }
}