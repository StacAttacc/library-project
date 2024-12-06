package com.example.tp4h23initial.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewDvdDTO {
    private String title;
    private String author;
    private String editor;
    private int yearOfPublishing;
    private int nbMinutes;
    private String genre;
    private int nbCopies;
}
