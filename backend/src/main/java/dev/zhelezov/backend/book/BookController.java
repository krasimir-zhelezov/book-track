package dev.zhelezov.backend.book;

import java.util.List;
import java.util.Set;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/books")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@Tag(name = "Book Management", description = "Endpoints for managing books")
public class BookController {

    private final BookService bookService;

    @PostMapping("/")
    @Operation(summary = "Create book", description = "Returns the created book")
    @ApiResponse(responseCode = "200", description = "Book created successfully")
    public ResponseEntity<Book> createBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok().body(bookService.createBook(bookDto));
    }  

    @GetMapping("/")
    @Operation(summary = "Get all books", description = "Returns a list of all books")
    @ApiResponse(responseCode = "200", description = "Books retrieved successfully")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok().body(bookService.getAllBooks());
    }
    
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    @Operation(summary = "Get book by id", description = "Returns the book with the specified id")
    @ApiResponse(responseCode = "200", description = "Book found")
    @ApiResponse(responseCode = "404", description = "Book not found")
    public ResponseEntity<Book> getBookById(@PathVariable UUID id) {
        return bookService.getBookById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update book by id", description = "Updates and returns the book with the new values")
    @ApiResponse(responseCode = "200", description = "Book updated successfully")
    @ApiResponse(responseCode = "404", description = "Book not found")
    public ResponseEntity<Book> updateBookById(@PathVariable UUID id, @RequestBody BookDto bookDto) {
        return bookService.updateBookById(id, bookDto)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete book by id", description = "Deletes the book with the specified id")
    @ApiResponse(responseCode = "200", description = "Book deleted successfully")
    @ApiResponse(responseCode = "404", description = "Book not found")
    public ResponseEntity<Void> deleteBookById(@PathVariable UUID id) {
        return bookService.deleteBookById(id) ?  ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/searchByTitle/{query}")
    @Operation(summary = "Search book by title", description = "Returns a list of books that match the title query")
    @ApiResponse(responseCode = "200", description = "Found books matching the title query")
    public ResponseEntity<List<Book>> searchByTitle(@PathVariable String query) {
        return ResponseEntity.ok().body(bookService.searchByTitle(query));
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/read/{bookId}")
    @Operation(summary = "Read book by id", description = "Returns the specified book")
    @ApiResponse(responseCode = "200", description = "Book found")
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content())
    @ApiResponse(responseCode = "404", description = "Book not found")
    public ResponseEntity<?> readBook(@PathVariable UUID bookId) {
        try {
            return ResponseEntity.ok().body(bookService.readBook(bookId));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/completed/{bookId}")
    @Operation(summary = "Check if book is read by id", description = "Returns true or false if the book is read")
    @ApiResponse(responseCode = "200", description = "Book found")
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content())
    @ApiResponse(responseCode = "404", description = "Book not found")
    public ResponseEntity<?> isBookCompleted(@PathVariable UUID bookId) {
        try {
            return ResponseEntity.ok().body(bookService.isBookCompleted(bookId));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/completed")
    @Operation(summary = "Get completed books", description = "Return a list of completed books")
    @ApiResponse(responseCode = "200", description = "Books found")
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content())
    public ResponseEntity<Set<Book>> completedBooks() {
        return ResponseEntity.ok().body(bookService.completedBooks());
    }
    
    @PostMapping("/generate")
    public ResponseEntity<List<Book>> generate(@RequestParam int number) {
        return ResponseEntity.ok().body(bookService.generate(number));
    }
    
}