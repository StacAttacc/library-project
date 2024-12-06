package com.example.tp4h23initial.services;

import com.example.tp4h23initial.daos.BorrowerRepository;
import com.example.tp4h23initial.daos.DocumentCopyStockRepository;
import com.example.tp4h23initial.daos.DocumentLoanRepository;
import com.example.tp4h23initial.models.DocumentCopyStock;
import com.example.tp4h23initial.models.DocumentLoan;
import com.example.tp4h23initial.models.documents.Book;
import com.example.tp4h23initial.models.users.Borrower;
import com.example.tp4h23initial.presentation.LibraryUtils;
import com.example.tp4h23initial.services.dto.DocumentLoanDTO;
import com.example.tp4h23initial.services.dto.NewBookDTO;
import com.example.tp4h23initial.services.dto.NewDocumentLoanDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class ServicesTest {

    @Mock
    private DocumentCopyStockRepository documentCopyStockRepository;

    @Mock
    private BorrowerRepository borrowerRepository;

    @Mock
    private DocumentLoanRepository documentLoanRepository;

    @InjectMocks
    private BorrowerService borrowerService;

    @InjectMocks
    private LibrarianService librarianService;

    @InjectMocks
    private LibraryUtils libraryUtils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveBookTest() {
        // Arrange
        NewBookDTO newBookDTO = createNewBookDTO();

        // Act
        librarianService.saveBook(newBookDTO);

        // Assert
        ArgumentCaptor<DocumentCopyStock> documentCopyStockCaptor = ArgumentCaptor.forClass(DocumentCopyStock.class);
        verify(documentCopyStockRepository, times(1)).save(documentCopyStockCaptor.capture());

        DocumentCopyStock savedDocumentCopyStock = documentCopyStockCaptor.getValue();
        assertBookDetails(savedDocumentCopyStock, newBookDTO);
    }

    @Test
    void testSaveDocumentLoan() throws Exception {
        // Arrange
        Long borrowerId = 1L;
        Long documentCopyStockId = 2L;
        NewDocumentLoanDTO newDocumentLoanDTO = createNewDocumentLoanDTO(borrowerId, documentCopyStockId);
        DocumentCopyStock documentCopyStock = createDocumentCopyStock(documentCopyStockId);
        Borrower borrower = createBorrower(borrowerId);

        when(borrowerService.getDocumentCopyStock(newDocumentLoanDTO)).thenReturn(documentCopyStock);
        when(borrowerService.getBorrower(newDocumentLoanDTO)).thenReturn(borrower);
        when(documentCopyStockRepository.findById(documentCopyStockId)).thenReturn(Optional.of(documentCopyStock));
        when(borrowerRepository.findById(borrowerId)).thenReturn(Optional.of(borrower));

        // Act
        borrowerService.saveDocumentLoan(newDocumentLoanDTO);

        // Assert
        ArgumentCaptor<DocumentLoan> documentLoanCaptor = ArgumentCaptor.forClass(DocumentLoan.class);
        verify(documentLoanRepository, times(1)).save(documentLoanCaptor.capture());

        DocumentLoan savedDocumentLoan = documentLoanCaptor.getValue();
        assertDocumentLoanDetails(savedDocumentLoan, borrowerId, documentCopyStockId);
    }

    @Test
    void testReturnDocumentLoan() {
        // Arrange
        Long documentLoanId = 1L;
        Long documentCopyStockId = 2L;
        Long borrowerId = 3L;
        int initialAvailableCopies = 0;

        DocumentLoanDTO documentLoanDTO = new DocumentLoanDTO();
        documentLoanDTO.setId(documentLoanId);

        DocumentLoan documentLoan = createDocumentLoan(documentLoanId, documentCopyStockId, borrowerId, initialAvailableCopies);
        when(documentLoanRepository.findDocumentLoanById(documentLoanId)).thenReturn(documentLoan);

        // Act
        borrowerService.returnDocumentLoan(documentLoanDTO);

        // Assert
        ArgumentCaptor<DocumentLoan> documentLoanCaptor = ArgumentCaptor.forClass(DocumentLoan.class);
        verify(documentLoanRepository, times(1)).save(documentLoanCaptor.capture());

        DocumentLoan updatedDocumentLoan = documentLoanCaptor.getValue();
        assertDocumentLoanReturned(updatedDocumentLoan, documentLoanId, documentCopyStockId, borrowerId, initialAvailableCopies);
    }

    private NewBookDTO createNewBookDTO() {
        NewBookDTO newBookDTO = new NewBookDTO();
        newBookDTO.setTitle("test");
        newBookDTO.setAuthor("test");
        newBookDTO.setGenre("test");
        newBookDTO.setEditor("test");
        newBookDTO.setNbPages(100);
        newBookDTO.setYearOfPublishing(2000);
        newBookDTO.setNbCopies(100);
        return newBookDTO;
    }

    private NewDocumentLoanDTO createNewDocumentLoanDTO(Long borrowerId, Long documentCopyStockId) {
        NewDocumentLoanDTO newDocumentLoanDTO = new NewDocumentLoanDTO();
        newDocumentLoanDTO.setBorrowerId(borrowerId);
        newDocumentLoanDTO.setDocumentCopyStockId(documentCopyStockId);
        return newDocumentLoanDTO;
    }

    private DocumentCopyStock createDocumentCopyStock(Long documentCopyStockId) {
        DocumentCopyStock documentCopyStock = new DocumentCopyStock();
        documentCopyStock.setId(documentCopyStockId);
        documentCopyStock.setNbAvailableCopies(1);
        documentCopyStock.setDocument(new Book());
        return documentCopyStock;
    }

    private Borrower createBorrower(Long borrowerId) {
        Borrower borrower = new Borrower();
        borrower.setId(borrowerId);
        borrower.setFirstName("Borrower");
        borrower.setLastName("Name");
        return borrower;
    }

    private DocumentLoan createDocumentLoan(Long documentLoanId, Long documentCopyStockId, Long borrowerId, int initialAvailableCopies) {
        DocumentLoan documentLoan = new DocumentLoan();
        documentLoan.setId(documentLoanId);

        Borrower borrower = new Borrower();
        borrower.setId(borrowerId);

        DocumentCopyStock documentCopyStock = new DocumentCopyStock();
        documentCopyStock.setId(documentCopyStockId);
        documentCopyStock.setNbAvailableCopies(initialAvailableCopies);

        documentLoan.setDocumentCopyStock(documentCopyStock);
        documentLoan.setBorrower(borrower);
        return documentLoan;
    }

    private void assertBookDetails(DocumentCopyStock savedDocumentCopyStock, NewBookDTO newBookDTO) {
        assertThat(savedDocumentCopyStock.getNbAvailableCopies()).isEqualTo(newBookDTO.getNbCopies());
        assertThat(savedDocumentCopyStock.getDocument()).isInstanceOf(Book.class);
        assertThat(savedDocumentCopyStock.getDocument().getTitle()).isEqualTo(newBookDTO.getTitle());
    }

    private void assertDocumentLoanDetails(DocumentLoan savedDocumentLoan, Long borrowerId, Long documentCopyStockId) {
        assertThat(savedDocumentLoan.getBorrower().getId()).isEqualTo(borrowerId);
        assertThat(savedDocumentLoan.getDocumentCopyStock().getId()).isEqualTo(documentCopyStockId);
        assertThat(savedDocumentLoan.getDocumentCopyStock().getNbAvailableCopies()).isEqualTo(0);
    }

    private void assertDocumentLoanReturned(DocumentLoan updatedDocumentLoan, Long documentLoanId, Long documentCopyStockId, Long borrowerId, int initialAvailableCopies) {
        assertThat(updatedDocumentLoan.getId()).isEqualTo(documentLoanId);
        assertThat(updatedDocumentLoan.getDocumentCopyStock().getId()).isEqualTo(documentCopyStockId);
        assertThat(updatedDocumentLoan.getBorrower().getId()).isEqualTo(borrowerId);
        assertThat(updatedDocumentLoan.isReturned()).isTrue();
        assertThat(updatedDocumentLoan.getFines()).isEqualTo(0.0);
        assertThat(updatedDocumentLoan.getDocumentCopyStock().getNbAvailableCopies()).isEqualTo(initialAvailableCopies + 1);
    }
}
