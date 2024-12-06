package com.example.tp4h23initial.services.dto;

import com.example.tp4h23initial.models.DocumentCopyStock;
import com.example.tp4h23initial.models.DocumentLoan;
import com.example.tp4h23initial.models.users.Borrower;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DocumentLoanDTO {
    private Long id;
    private Long documentCopyStockId;
    private String documentTitle;
    private Long borrowerId;
    private LocalDate expectedReturnDate;
    private boolean isReturned;
    private double fines;

    public static DocumentLoanDTO fromEntity(DocumentLoan documentLoan) {
        return DocumentLoanDTO.builder()
                .id(documentLoan.getId())
                .documentCopyStockId(documentLoan.getDocumentCopyStock().getId())
                .borrowerId(documentLoan.getBorrower().getId())
                .expectedReturnDate(documentLoan.getExpectedReturnDate())
                .isReturned(documentLoan.isReturned())
                .fines(documentLoan.getFines())
                .documentTitle(documentLoan.getDocumentCopyStock().getDocument().getTitle())
                .build();
    }

    public DocumentLoan toEntity() {
        DocumentLoan documentLoan = new DocumentLoan();
        documentLoan.setId(this.id);
        DocumentCopyStock documentCopyStock = new DocumentCopyStock();
        documentCopyStock.setId(this.documentCopyStockId);
        documentLoan.setDocumentCopyStock(documentCopyStock);
        Borrower borrower = new Borrower(); // Assuming you have Borrower entity
        borrower.setId(this.borrowerId);
        documentLoan.setBorrower(borrower);
        documentLoan.setExpectedReturnDate(this.expectedReturnDate);
        documentLoan.setReturned(this.isReturned);
        documentLoan.setFines(this.fines);
        return documentLoan;
    }
}
