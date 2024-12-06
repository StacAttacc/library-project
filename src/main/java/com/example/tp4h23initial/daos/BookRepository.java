package com.example.tp4h23initial.daos;

import com.example.tp4h23initial.models.documents.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBooksByTitleContains(String titleSubstring);
    List<Book> findBooksByAuthor(String author);
    List<Book> findBooksByYearOfPublishing(int year);
    List<Book> findBooksByGenre(String genre);
    Book findLivreById(Long id);
}
