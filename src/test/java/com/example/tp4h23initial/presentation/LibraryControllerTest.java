package com.example.tp4h23initial.presentation;

import com.example.tp4h23initial.daos.BookRepository;
import com.example.tp4h23initial.daos.DocumentCopyStockRepository;
import com.example.tp4h23initial.models.DocumentCopyStock;
import com.example.tp4h23initial.models.documents.Book;
import com.example.tp4h23initial.models.users.Borrower;
import com.example.tp4h23initial.services.BorrowerService;
import com.example.tp4h23initial.services.LibrarianService;
import com.example.tp4h23initial.services.LibraryService;
import com.example.tp4h23initial.services.dto.DocumentLoanDTO;
import com.example.tp4h23initial.services.dto.NewBookDTO;
import com.example.tp4h23initial.services.dto.NewDocumentLoanDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LibraryController.class)
public class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DocumentCopyStockRepository documentCopyStockRepository;

    @MockBean
    private BorrowerService borrowerService;

    @MockBean
    private LibrarianService librarianService;

    @MockBean
    private LibraryService libraryService;

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private LibraryController libraryController;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        LibraryUtils libraryUtils = mock(LibraryUtils.class);
        when(libraryUtils.saveBook(any(NewBookDTO.class)))
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).build());
        when(libraryUtils.saveLoan(any(NewDocumentLoanDTO.class)))
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).build());
        when(libraryUtils.returnDocumentLoan(any(DocumentLoanDTO.class)))
                .thenReturn(ResponseEntity.status(HttpStatus.OK).build());

    }

    @Test
    void saveBookTest() throws Exception {
        NewBookDTO newBookDTO = new NewBookDTO();
        newBookDTO.setTitle("test");
        newBookDTO.setAuthor("test");
        newBookDTO.setGenre("test");
        newBookDTO.setEditor("test");
        newBookDTO.setNbPages(100);
        newBookDTO.setYearOfPublishing(2000);
        newBookDTO.setNbCopies(100);

        this.mockMvc.perform(post("/api/library/v1/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newBookDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void borrowTest_shouldWork() throws Exception {
        Borrower borrower = new Borrower();
        borrower.setFirstName("test");
        borrower.setLastName("test");
        borrower.setId(1L);

        DocumentCopyStock documentCopyStock = new DocumentCopyStock();
        documentCopyStock.setNbAvailableCopies(1);
        documentCopyStock.setId(1L);
        documentCopyStock.setDocument(new Book());

        NewDocumentLoanDTO documentLoanDTO = new NewDocumentLoanDTO();
        documentLoanDTO.setBorrowerId(borrower.getId());
        documentLoanDTO.setDocumentCopyStockId(documentCopyStock.getId());

        this.mockMvc.perform(post("/api/library/v1/loan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(documentLoanDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void returnBorrowTest() throws Exception {
        Borrower borrower = new Borrower();
        borrower.setFirstName("test");
        borrower.setLastName("test");
        borrower.setId(1L);

        DocumentCopyStock documentCopyStock = new DocumentCopyStock();
        documentCopyStock.setNbAvailableCopies(1);
        documentCopyStock.setId(1L);
        documentCopyStock.setDocument(new Book());

        NewDocumentLoanDTO documentLoanDTO = new NewDocumentLoanDTO();
        documentLoanDTO.setBorrowerId(borrower.getId());
        documentLoanDTO.setDocumentCopyStockId(documentCopyStock.getId());

        doNothing().when(borrowerService).saveDocumentLoan(any(NewDocumentLoanDTO.class));
        borrowerService.saveDocumentLoan(documentLoanDTO);

        this.mockMvc.perform(put("/api/library/v1/return-loan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(documentLoanDTO)))
                .andExpect(status().isOk());

    }
}
