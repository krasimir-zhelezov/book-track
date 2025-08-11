package dev.zhelezov.backend.book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.zhelezov.backend.auth.dto.CustomUserDetails;
import dev.zhelezov.backend.auth.model.User;
import dev.zhelezov.backend.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public Book createBook(BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);

        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(UUID id) {
        return bookRepository.findById(id);
    }

    public Optional<Book> updateBookById(UUID id, BookDto bookDto) {
        return bookRepository.findById(id)
            .map(book -> {
                modelMapper.map(bookDto, book);
                return bookRepository.save(book);
            });
    }

    public boolean deleteBookById(UUID id) {
        return bookRepository.findById(id)
            .map(book -> {
                bookRepository.delete(book);
                return true;
            })
            .orElse(false);
    }

    public List<Book> searchByTitle(String query) {
        return bookRepository.findByTitleContaining(query);
    }

    public void readBook(UUID bookId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername()).get();

        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        user.addBookRead(book);
        userRepository.save(user);
    }
}
