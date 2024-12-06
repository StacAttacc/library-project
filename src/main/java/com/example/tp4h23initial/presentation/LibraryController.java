package com.example.tp4h23initial.presentation;

import com.example.tp4h23initial.services.LibrarianService;
import com.example.tp4h23initial.services.LibraryService;
import com.example.tp4h23initial.services.BorrowerService;
import com.example.tp4h23initial.services.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

@RestController
@RequestMapping("/api/library/v1")
public class LibraryController {
    private LibraryUtils utils;

    private final LibraryService libraryService;
    private final LibrarianService librarianService;
    private final BorrowerService borrowerService;

    @Autowired
    public LibraryController(LibraryService libraryService,
                             LibrarianService librarianService,
                             BorrowerService borrowerService) {
        this.libraryService = libraryService;
        this.librarianService = librarianService;
        this.borrowerService = borrowerService;
        this.utils = new LibraryUtils(this.librarianService, this.borrowerService);
    }





    @PostMapping("/book")
    public ResponseEntity<Object> saveBook(@RequestBody NewBookDTO book) {
        return utils.saveBook(book);
    }

    @PostMapping("/cd")
    public ResponseEntity<Object> saveCd(@RequestBody NewCdDTO cd) {
        return utils.saveCd(cd);
    }

    @PostMapping("/dvd")
    public ResponseEntity<Object> saveDvd(@RequestBody NewDvdDTO dvd) {
        return utils.saveDvd(dvd);
    }





    @GetMapping("/books")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ok(libraryService.findAllBooks());
    }

    @GetMapping("/cds")
    public ResponseEntity<List<CdDTO>> getAllCds() {
        return ok(libraryService.findAllCds());
    }

    @GetMapping("/dvds")
    public ResponseEntity<List<DvdDTO>> getAllDvds() {
        return ok(libraryService.findAllDvds());
    }

    @GetMapping("/document-copies")
    public ResponseEntity<List<DocumentCopyStockDTO>> getAllDocumentCopies() {
        return ok(borrowerService.findAllDocumentCopies());
    }





    @PostMapping("/borrower")
    public ResponseEntity<Object> saveBorrower(@RequestBody NewBorrowerDTO borrower) {
        return utils.saveBorrower(borrower);
    }

    @GetMapping("/borrowers")
    public ResponseEntity<List<BorrowerDTO>> getAllBorrowers() {
        return ok(borrowerService.findAllBorrowers());
    }





    @GetMapping("/librarians")
    public ResponseEntity<List<LibrarianDTO>> getAllLibrarians() {
        return ok(librarianService.findAllLibrarians());
    }





    @GetMapping("/books/search-by-title")
    public ResponseEntity<List<BookDTO>> searchBooksByTitle(@RequestBody String title) {
        return ok(libraryService.findBooksByTitle(title));
    }

    @GetMapping("/cds/search-by-title")
    public ResponseEntity<List<CdDTO>> searchCdsByTitle(@RequestBody String title) {
        return ok(libraryService.findCdsByTitle(title));
    }

    @GetMapping("/dvds/search-by-title")
    public ResponseEntity<List<DvdDTO>> searchDvdsByTitle(@RequestBody String title) {
        return ok(libraryService.findDvdsByTitle(title));
    }





    @GetMapping("/books/search-by-author")
    public ResponseEntity<List<BookDTO>> searchBooksByAuthor(@RequestBody String author){
        return ok(libraryService.findBooksByAuthor(author));
    }

    @GetMapping("/cds/search-by-author")
    public ResponseEntity<List<CdDTO>> searchCdsByAuthor(@RequestBody String author) {
        return ok(libraryService.findCdsByAuthor(author));
    }

    @GetMapping("/dvds/search-by-author")
    public ResponseEntity<List<DvdDTO>> searchDvdsByAuthor(@RequestBody String author) {
        return ok(libraryService.findDvdsByAuthor(author));
    }





    @GetMapping("/books/search-by-year")
    public ResponseEntity<List<BookDTO>> searchBooksByYear(@RequestBody int year){
        return ok(libraryService.findBooksByYear(year));
    }

    @GetMapping("/cds/search-by-year")
    public ResponseEntity<List<CdDTO>> searchCdsByYear(@RequestBody int year) {
        return ok(libraryService.findCdsByYear(year));
    }

    @GetMapping("/dvds/search-by-year")
    public ResponseEntity<List<DvdDTO>> searchDvdsByYear(@RequestBody int year) {
        return ok(libraryService.findDvdsByYear(year));
    }




    @GetMapping("/books/search-by-genre")
    public ResponseEntity<List<BookDTO>> searchBooksByGenre(@RequestBody String genre){
        return ok(libraryService.findBooksByGenre(genre));
    }

    @GetMapping("/cds/search-by-genre")
    public ResponseEntity<List<CdDTO>> searchCdsByGenre(@RequestBody String genre) {
        return ok(libraryService.findCdsByGenre(genre));
    }

    @GetMapping("/dvds/search-by-genre")
    public ResponseEntity<List<DvdDTO>> searchDvdsByGenre(@RequestBody String genre) {
        return ok(libraryService.findDvdsByGenre(genre));
    }





    @PostMapping("/loan")
    public ResponseEntity<Object> saveDocumentLoan(@RequestBody NewDocumentLoanDTO newDocumentLoanDTO)
            throws Exception {
        return utils.saveLoan(newDocumentLoanDTO);
    }

    @PostMapping("/list-borrower-loans")
    public ResponseEntity<List<DocumentLoanDTO>> getBorrowerLoans(@RequestBody BorrowerDTO client) {
        return ok(borrowerService.getBorrowersLoans(client));
    }

    @PostMapping("/return-loan")
    public ResponseEntity<Object> returnDocumentLoan(@RequestBody DocumentLoanDTO empreunt) {
        return utils.returnDocumentLoan(empreunt);
    }





    private ResponseEntity<Object> isCreated() {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private ResponseEntity<Object> badRequest() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    private ResponseEntity<Object> isAccepted() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
