package com.example.tp4h23initial.presentation;

import com.example.tp4h23initial.services.BorrowerService;
import com.example.tp4h23initial.services.LibrarianService;
import com.example.tp4h23initial.services.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class LibraryUtils {
    private final LibrarianService librarianService;
    private final BorrowerService borrowerService;

    public LibraryUtils(LibrarianService librarianService,
                        BorrowerService borrowerService) {
        this.librarianService = librarianService;
        this.borrowerService = borrowerService;
    }

    public ResponseEntity<Object> saveBook(NewBookDTO livre) {
        try {
            librarianService.saveBook(livre);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<Object> saveCd(NewCdDTO cd) {
        try {
            librarianService.saveCd(cd);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<Object> saveDvd(NewDvdDTO dvd) {
        try {
            librarianService.saveDvd(dvd);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<Object> saveBorrower(NewBorrowerDTO borrower){
        try {
            borrowerService.saveBorrower(borrower);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<Object> saveLoan(NewDocumentLoanDTO newDocumentLoanDTO)
            throws Exception {
        try{
            borrowerService.saveDocumentLoan(newDocumentLoanDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<Object> returnDocumentLoan(DocumentLoanDTO loan) {
        try {
            borrowerService.returnDocumentLoan(loan);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
