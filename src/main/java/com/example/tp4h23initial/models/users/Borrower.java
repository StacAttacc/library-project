package com.example.tp4h23initial.models.users;

import com.example.tp4h23initial.models.DocumentLoan;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@ToString
@Component
@Getter
@Setter
@DiscriminatorValue("client")
public class Borrower extends LibraryUser {
    @OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL)
    private List<DocumentLoan> documentLoans;
}