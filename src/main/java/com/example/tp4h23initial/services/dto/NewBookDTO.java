package com.example.tp4h23initial.services.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewBookDTO {
    private String title;
    private String author;
    private String editor;
    private int yearOfPublishing;
    private int nbPages;
    private String genre;
    private int nbCopies;
}
