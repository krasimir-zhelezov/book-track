package dev.zhelezov.backend.book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.stream.IntStream;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.javafaker.Faker;

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
    private final Faker faker;
    private final Random random;

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
        return bookRepository.searchByTitle(query);
    }

    public boolean readBook(UUID bookId) {
        boolean read;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        if (user.getBooksRead().contains(book)) {
            user.removeBookRead(book);
            read = false;
        } else {
            user.addBookRead(book);
            read = true;
        }
        
        userRepository.save(user);

        return read;
    }

    public Set<Book> completedBooks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername()).get();

        return user.getBooksRead();
    }

    public List<Book> generate(int number) {
        List<Book> generatedBooks = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            List<String> genres = IntStream.range(0, 1 + random.nextInt(5))
                .mapToObj(genre -> faker.book().genre())
                .distinct()
                .toList();

            Book book = new Book(
                faker.book().title(),
                faker.book().author(),
                faker.code().isbn13(),
                genres,
                1400 + random.nextInt(625),
                faker.lorem().paragraph()
            );
            generatedBooks.add(book);
            bookRepository.save(book);
        }
        return generatedBooks;
    }

    public boolean isBookCompleted(UUID bookId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        return user.getBooksRead().contains(bookRepository.findById(bookId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found")));
    }
}
