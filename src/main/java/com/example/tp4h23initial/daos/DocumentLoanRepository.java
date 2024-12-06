package com.example.tp4h23initial.daos;

import com.example.tp4h23initial.models.DocumentLoan;
import com.example.tp4h23initial.models.users.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentLoanRepository extends JpaRepository<DocumentLoan, Long> {
    List<DocumentLoan> findDocumentLoansByBorrower(Optional<Borrower> client);
    DocumentLoan findDocumentLoanById(Long id);
}
