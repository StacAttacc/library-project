package com.example.tp4h23initial;

import com.example.tp4h23initial.services.BorrowerService;
import com.example.tp4h23initial.services.LibrarianService;
import com.example.tp4h23initial.services.dto.NewBookDTO;
import com.example.tp4h23initial.services.dto.NewBorrowerDTO;
import com.example.tp4h23initial.services.dto.NewDocumentLoanDTO;
import com.example.tp4h23initial.services.dto.NewLibrarianDTO;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Tp4H23InitialApplication {

    public static void main(String[] args) {
        SpringApplication.run(Tp4H23InitialApplication.class, args);
    }

    @Bean
    public ApplicationRunner initializeData(LibrarianService librarianService,
                                            BorrowerService borrowerService){
        return args -> {
            librarianService.saveLibrarian(new NewLibrarianDTO("a", "libraarian"));
            librarianService.saveBook(new NewBookDTO(
                    "livre1",
                    "auteur1",
                    "editor1",
                    1,
                    1,
                    "genre1",
                    3
            ));
            librarianService.saveBook(new NewBookDTO(
                    "livre2",
                    "auteur1",
                    "editor1",
                    1,
                    1,
                    "genre1",
                    3
            ));
            borrowerService.saveBorrower(new NewBorrowerDTO(
                    "a",
                    "borrower"
            ));
            borrowerService.saveDocumentLoan(new NewDocumentLoanDTO(
                    1L,
                    1L
            ));
        };
    }

}
