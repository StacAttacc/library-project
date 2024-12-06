package com.example.tp4h23initial.models.documents;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.springframework.stereotype.Component;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@ToString
@Component
@DiscriminatorValue("dvd")
public class Dvd extends Document {
    private int nbMinutes;
    private String genre;
    @OneToOne
    @JoinColumn(name = "document_id")
    @ToString.Exclude
    private Document document;
}