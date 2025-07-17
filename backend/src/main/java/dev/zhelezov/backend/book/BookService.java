package dev.zhelezov.backend.book;

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
}
