package dev.zhelezov.backend.book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.annotations.NotFound;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;

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
}
