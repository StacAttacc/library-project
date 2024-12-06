package com.example.tp4h23initial.models;

import com.example.tp4h23initial.models.users.Borrower;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Component
public class DocumentLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "empreunt_document_id")
    private DocumentCopyStock documentCopyStock;
    @ManyToOne
    @JoinColumn(name = "client_id")
    @ToString.Exclude
    private Borrower borrower;
    private LocalDate expectedReturnDate;
    private boolean isReturned;
    private double fines;
}