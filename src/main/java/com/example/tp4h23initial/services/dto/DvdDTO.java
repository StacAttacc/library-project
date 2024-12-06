package com.example.tp4h23initial.services.dto;

import com.example.tp4h23initial.models.documents.Dvd;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DvdDTO {
    private String title;
    private String author;
    private String editor;
    private int yearOfPublishing;
    private int borrowLength;
    private int nbPages;
    private String genre;

    public Dvd toEntity(int borrowLength) {
        Dvd dvd = new Dvd();
        dvd.setTitle(this.getTitle());
        dvd.setAuthor(this.getAuthor());
        dvd.setEditor(this.getEditor());
        dvd.setYearOfPublishing(this.getYearOfPublishing());
        dvd.setBorrowLength(borrowLength);
        dvd.setNbMinutes(this.getNbPages());
        dvd.setGenre(this.getGenre());
        return dvd;
    }

    public static DvdDTO fromEntity(Dvd dvd) {
        return new DvdDTO(
                dvd.getTitle(),
                dvd.getAuthor(),
                dvd.getEditor(),
                dvd.getYearOfPublishing(),
                dvd.getBorrowLength(),
                dvd.getNbMinutes(),
                dvd.getGenre()
        );
    }
}
