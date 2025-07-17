package dev.zhelezov.backend.book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

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
}
