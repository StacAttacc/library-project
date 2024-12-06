package com.example.tp4h23initial.models.users;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Entity
@ToString
@Component
@DiscriminatorValue("prepose")
public class Librarian extends LibraryUser {
}
