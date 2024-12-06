package com.example.tp4h23initial.services.dto;

import com.example.tp4h23initial.models.documents.Book;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private String title;
    private String author;
    private String editor;
    private int yearOfPublishing;
    private int borrowLength;
    private int nbPages;
    private String genre;

    public Book toEntity(int borrowLength) {
        Book book = new Book();
        book.setTitle(this.getTitle());
        book.setAuthor(this.getAuthor());
        book.setEditor(this.getEditor());
        book.setYearOfPublishing(this.getYearOfPublishing());
        book.setBorrowLength(borrowLength);
        book.setNbPages(this.getNbPages());
        book.setGenre(this.getGenre());
        return book;
    }

    public static BookDTO fromEntity(Book book) {
        return new BookDTO(
                book.getTitle(),
                book.getAuthor(),
                book.getEditor(),
                book.getYearOfPublishing(),
                book.getBorrowLength(),
                book.getNbPages(),
                book.getGenre()
        );
    }
}
