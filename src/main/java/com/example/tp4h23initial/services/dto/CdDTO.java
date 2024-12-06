package com.example.tp4h23initial.services.dto;

import com.example.tp4h23initial.models.documents.Cd;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CdDTO {
    private String title;
    private String author;
    private String editor;
    private int yearOfPublishing;
    private int borrowLength;
    private int nbPages;
    private String genre;

    public Cd toEntity(int borrowLength) {
        Cd cd = new Cd();
        cd.setTitle(this.getTitle());
        cd.setAuthor(this.getAuthor());
        cd.setEditor(this.getEditor());
        cd.setYearOfPublishing(this.getYearOfPublishing());
        cd.setBorrowLength(borrowLength);
        cd.setNbMinutes(this.getNbPages());
        cd.setGenre(this.getGenre());
        return cd;
    }

    public static CdDTO fromEntity(Cd cd) {
        return new CdDTO(
                cd.getTitle(),
                cd.getAuthor(),
                cd.getEditor(),
                cd.getYearOfPublishing(),
                cd.getBorrowLength(),
                cd.getNbMinutes(),
                cd.getGenre()
        );
    }
}
