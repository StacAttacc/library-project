package com.example.tp4h23initial.services;

import com.example.tp4h23initial.daos.BorrowerRepository;
import com.example.tp4h23initial.daos.DocumentCopyStockRepository;
import com.example.tp4h23initial.daos.DocumentLoanRepository;
import com.example.tp4h23initial.models.DocumentCopyStock;
import com.example.tp4h23initial.models.DocumentLoan;
import com.example.tp4h23initial.models.users.Borrower;
import com.example.tp4h23initial.services.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.example.tp4h23initial.services.dto.EntityDtoConverter.convertList;

@Service
public class BorrowerService {
    private final BorrowerRepository borrowerRepository;
    private final DocumentLoanRepository documentLoanRepository;
    private final DocumentCopyStockRepository documentCopyStockRepository;
    private final double FINE_PER_DAY = 0.25;

    @Autowired
    public BorrowerService(BorrowerRepository borrowerRepository,
                           DocumentLoanRepository documentLoanRepository,
                           DocumentCopyStockRepository documentCopyStockRepository
                         ) {
        this.borrowerRepository = borrowerRepository;
        this.documentLoanRepository = documentLoanRepository;
        this.documentCopyStockRepository = documentCopyStockRepository;
    }

    @Transactional
    public void saveBorrower(NewBorrowerDTO borrowerDTO) {
        Borrower borrower = new Borrower();
        borrower.setFirstName(borrowerDTO.getFirstName());
        borrower.setLastName(borrowerDTO.getLastName());
        borrowerRepository.save(borrower);
    }

    @Transactional
    public void saveDocumentLoan(NewDocumentLoanDTO newDocumentLoanDTO) throws Exception {
        DocumentCopyStock documentCopyStock = getDocumentCopyStock(newDocumentLoanDTO);
        Borrower borrower = getBorrower(newDocumentLoanDTO);

        isStockAvailable(documentCopyStock);

        DocumentLoan documentLoan = createDocumentLoan(borrower, documentCopyStock);
        decrementAvailableStock(documentCopyStock);

        documentLoanRepository.save(documentLoan);
    }

    @Transactional
    public List<BorrowerDTO> findAllBorrowers(){
        return convertList(borrowerRepository.findAll(),
                BorrowerDTO::fromEntity);
    }

    @Transactional
    public List<DocumentCopyStockDTO> findAllDocumentCopies() {
        return convertList(documentCopyStockRepository.findAll(),
                DocumentCopyStockDTO::fromEntity);
    }

    @Scheduled(fixedDelay = 24*60*60*1000)
    public void updateFines(){
        List<DocumentLoan> documentLoans = documentLoanRepository.findAll();
        documentLoans.forEach(document -> {
            if(!isReturnedOnTime(document)){
                document.setFines(document.getFines() + FINE_PER_DAY);
            }
        });
        documentLoanRepository.saveAll(documentLoans);
    }

    private boolean isReturnedOnTime(DocumentLoan element) {
        return element.getExpectedReturnDate().isBefore(LocalDate.now()) && element.isReturned();
    }

    @Transactional
    public void returnDocumentLoan(DocumentLoanDTO documentLoanDTO){
        DocumentLoan documentLoan = documentLoanRepository
                .findDocumentLoanById(documentLoanDTO.getId());
        documentLoan.setFines(0.0);
        documentLoan.setReturned(true);
        documentLoan.getDocumentCopyStock().setNbAvailableCopies(
            documentLoan.getDocumentCopyStock().getNbAvailableCopies() + 1);
        documentLoanRepository.save(documentLoan);
    }
    
    @Transactional
    public List<DocumentLoanDTO> getBorrowersLoans(BorrowerDTO borrowerDTO){
        return convertList(documentLoanRepository.findDocumentLoansByBorrower(
                borrowerRepository.findById(borrowerDTO.getId())),
                DocumentLoanDTO::fromEntity);
    }


    @Transactional
    protected Borrower getBorrower(NewDocumentLoanDTO newDocumentLoanDTO) {
        return borrowerRepository.findBorrowerById(newDocumentLoanDTO.getBorrowerId());
    }

    @Transactional
    protected DocumentCopyStock getDocumentCopyStock(NewDocumentLoanDTO newDocumentLoanDTO) {
        return documentCopyStockRepository
                .findDocumentCopyStockById(newDocumentLoanDTO.getDocumentCopyStockId());
    }

    private DocumentLoan createDocumentLoan(Borrower borrower,
                                                   DocumentCopyStock documentCopyStock) {
        DocumentLoan documentLoan = new DocumentLoan();
        documentLoan.setBorrower(borrower);
        documentLoan.setDocumentCopyStock(documentCopyStock);
        documentLoan.setReturned(false);
        documentLoan.setFines(0.0);
        documentLoan.setExpectedReturnDate(LocalDate
                .now()
                .plusDays(documentCopyStock.getDocument().getBorrowLength()));
        return documentLoan;
    }

    private void isStockAvailable(DocumentCopyStock documentCopyStock) throws Exception {
        if(documentCopyStock.getNbAvailableCopies() <= 0) throw new Exception("plus d'exemplaires");
    }

    private void decrementAvailableStock(DocumentCopyStock documentCopyStock) {
        documentCopyStock.setNbAvailableCopies(documentCopyStock.getNbAvailableCopies() -1);
    }
}
