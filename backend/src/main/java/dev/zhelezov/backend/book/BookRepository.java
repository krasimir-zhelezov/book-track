package dev.zhelezov.backend.book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, UUID> {
    List<Book> findAll();

    Optional<Book> findById(UUID id);

    List<Book> findByTitleContaining(String title);
    
    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Book> searchByTitle(@Param("query") String query);
}