package com.example.tp4h23initial.services;

import com.example.tp4h23initial.daos.DocumentCopyStockRepository;
import com.example.tp4h23initial.daos.LibrarianRepository;
import com.example.tp4h23initial.models.DocumentCopyStock;
import com.example.tp4h23initial.models.documents.Cd;
import com.example.tp4h23initial.models.documents.Dvd;
import com.example.tp4h23initial.models.users.Librarian;
import com.example.tp4h23initial.services.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.tp4h23initial.services.dto.EntityDtoConverter.convertList;

@Service
public class LibrarianService {
    private final LibrarianRepository librarianRepository;
    private final DocumentCopyStockRepository documentCopyStockRepository;

    private final int LIVRE_BORROW_LENGTH = 3;
    private final int CD_BORROW_LENGTH = 2;
    private final int DVD_BORROW_LENGTH = 2;

    @Autowired
    public LibrarianService(LibrarianRepository librarianRepository,
                            DocumentCopyStockRepository documentCopyStockRepository) {
        this.librarianRepository = librarianRepository;
        this.documentCopyStockRepository = documentCopyStockRepository;
    }

    @Transactional
    public void saveLibrarian(NewLibrarianDTO newLibrarianDTO) {
        Librarian librarian = new Librarian();
        librarian.setFirstName(newLibrarianDTO.getFirstName());
        librarian.setLastName(newLibrarianDTO.getLastName());
        librarianRepository.save(librarian);
    }

    @Transactional
    public List<LibrarianDTO> findAllLibrarians(){
        return convertList(librarianRepository.findAll(),
                LibrarianDTO::fromEntity);
    }

    @Transactional
    public void saveBook(NewBookDTO newBookDTO) {
        DocumentCopyStock documentCopyStock = createBookCopies(newBookDTO);
        documentCopyStockRepository.save(documentCopyStock);
    }

    @Transactional
    public void saveCd(NewCdDTO newCdDTO) {
        DocumentCopyStock documentCopyStock = createCdCopies(newCdDTO);
        documentCopyStockRepository.save(documentCopyStock);
    }

    @Transactional
    public void saveDvd(NewDvdDTO newDvdDTO) {
        DocumentCopyStock documentCopyStock = createDvdCopies(newDvdDTO);
        documentCopyStockRepository.save(documentCopyStock);
    }



    private BookDTO createLivre(NewBookDTO newBookDTO) {
        BookDTO livre = new BookDTO();
        livre.setTitle(newBookDTO.getTitle());
        livre.setAuthor(newBookDTO.getAuthor());
        livre.setEditor(newBookDTO.getEditor());
        livre.setYearOfPublishing(newBookDTO.getYearOfPublishing());
        livre.setNbPages(newBookDTO.getNbPages());
        livre.setGenre(newBookDTO.getGenre());
        return livre;
    }

    private DocumentCopyStock createBookCopies(NewBookDTO newBookDTO) {
        BookDTO livre = createLivre(newBookDTO);
        DocumentCopyStock documentCopyStock = new DocumentCopyStock();
        documentCopyStock.setDocument(livre.toEntity(LIVRE_BORROW_LENGTH));
        documentCopyStock.setNbAvailableCopies(newBookDTO.getNbCopies());
        return documentCopyStock;
    }

    private Cd createCd(NewCdDTO newCdDTO) {
        Cd cd = new Cd();
        cd.setTitle(newCdDTO.getTitle());
        cd.setAuthor(newCdDTO.getAuthor());
        cd.setEditor(newCdDTO.getEditor());
        cd.setYearOfPublishing(newCdDTO.getYearOfPublishing());
        cd.setBorrowLength(CD_BORROW_LENGTH);
        cd.setNbMinutes(cd.getNbMinutes());
        cd.setGenre(cd.getGenre());
        return cd;
    }

    private DocumentCopyStock createCdCopies(NewCdDTO newCdDTO) {
        Cd cd = createCd(newCdDTO);
        DocumentCopyStock documentCopyStock = new DocumentCopyStock();
        documentCopyStock.setDocument(cd);
        documentCopyStock.setNbAvailableCopies(newCdDTO.getNbCopies());
        return documentCopyStock;
    }

    private Dvd createDvd(NewDvdDTO newDvdDTO) {
        Dvd dvd = new Dvd();
        dvd.setTitle(newDvdDTO.getTitle());
        dvd.setAuthor(newDvdDTO.getAuthor());
        dvd.setEditor(newDvdDTO.getEditor());
        dvd.setYearOfPublishing(newDvdDTO.getYearOfPublishing());
        dvd.setBorrowLength(DVD_BORROW_LENGTH);
        dvd.setNbMinutes(newDvdDTO.getNbMinutes());
        dvd.setGenre(newDvdDTO.getGenre());
        return dvd;
    }

    private DocumentCopyStock createDvdCopies(NewDvdDTO newDvdDTO) {
        Dvd dvd = createDvd(newDvdDTO);
        DocumentCopyStock documentCopyStock = new DocumentCopyStock();
        documentCopyStock.setDocument(dvd);
        documentCopyStock.setNbAvailableCopies(newDvdDTO.getNbCopies());
        return documentCopyStock;
    }
}
