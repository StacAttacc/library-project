package com.example.tp4h23initial.services;

import com.example.tp4h23initial.daos.CdRepository;
import com.example.tp4h23initial.daos.DvdRepository;
import com.example.tp4h23initial.daos.BookRepository;
import com.example.tp4h23initial.models.documents.Cd;
import com.example.tp4h23initial.models.documents.Dvd;
import com.example.tp4h23initial.models.documents.Book;
import com.example.tp4h23initial.services.dto.CdDTO;
import com.example.tp4h23initial.services.dto.DvdDTO;
import com.example.tp4h23initial.services.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.tp4h23initial.services.dto.EntityDtoConverter.convertList;

@Service
public class LibraryService {
    private final BookRepository bookRepository;
    private final DvdRepository dvdRepository;
    private final CdRepository cdRepository;

    @Autowired
    public LibraryService(BookRepository bookRepository,
                          DvdRepository dvdRepository,
                          CdRepository cdRepository) {
        this.bookRepository = bookRepository;
        this.dvdRepository = dvdRepository;
        this.cdRepository = cdRepository;
    }


    @Transactional
    public List<BookDTO> findAllBooks(){
        return convertList(bookRepository.findAll(),
                BookDTO::fromEntity);
    }

    @Transactional
    public List<CdDTO> findAllCds(){
        return convertList(cdRepository.findAll(),
                CdDTO::fromEntity);
    }

    @Transactional
    public List<DvdDTO> findAllDvds(){
        return convertList(dvdRepository.findAll(),
                DvdDTO::fromEntity);
    }

    @Transactional
    public List<BookDTO> findBooksByAuthor(String author){
        return convertList(bookRepository.findBooksByAuthor(author),
                BookDTO::fromEntity);
    }

    @Transactional
    public List<CdDTO> findCdsByAuthor(String author){
        return convertList(cdRepository.findCdsByAuthor(author),
                CdDTO::fromEntity);
    }

    @Transactional
    public List<DvdDTO> findDvdsByAuthor(String author){
        return convertList(dvdRepository.findDvdsByAuthor(author),
                DvdDTO::fromEntity);
    }

    @Transactional
    public List<BookDTO> findBooksByYear(int year){
        return convertList(bookRepository.findBooksByYearOfPublishing(year),
                BookDTO::fromEntity);
    }

    @Transactional
    public List<DvdDTO> findDvdsByYear(int year){
        return convertList(dvdRepository.findDvdsByYearOfPublishing(year),
                DvdDTO::fromEntity);
    }

    @Transactional
    public List<CdDTO> findCdsByYear(int year){
        return convertList(cdRepository.findCdsByYearOfPublishing(year),
                CdDTO::fromEntity);
    }

    @Transactional
    public List<BookDTO> findBooksByGenre(String genre){
        return convertList(bookRepository.findBooksByGenre(genre),
                BookDTO::fromEntity);
    }

    @Transactional
    public List<CdDTO> findCdsByGenre(String genre){
        return convertList(cdRepository.findCdsByGenre(genre),
                CdDTO::fromEntity);
    }

    @Transactional
    public List<DvdDTO> findDvdsByGenre(String genre){
        return convertList(dvdRepository.findDvdsByGenre(genre),
                DvdDTO::fromEntity);
    }

    @Transactional
    public List<BookDTO> findBooksByTitle(String title){
        return convertList(findBooksByTitleKeywords(title),
                BookDTO::fromEntity);
    }

    @Transactional
    public List<CdDTO> findCdsByTitle(String title){
        return convertList(findCdsByTitleKeywords(title),
                CdDTO::fromEntity);
    }

    @Transactional
    public List<DvdDTO> findDvdsByTitle(String title){
        return convertList(findDvdsByTitleKeywords(title),
                DvdDTO::fromEntity);
    }



    private List<String> titleKeywords(String title){
        return List.of(title.trim().split(" "));
    }

    private List<Book> findBooksByTitleKeywords(String title) {
        return titleKeywords(title)
                .stream()
                .flatMap(keyword -> bookRepository.findBooksByTitleContains(keyword).stream())
                .collect(Collectors.toList());
    }

    private List<Cd> findCdsByTitleKeywords(String title) {
        return titleKeywords(title)
                .stream()
                .flatMap(keyword -> cdRepository.findCdsByTitleContains(keyword).stream())
                .collect(Collectors.toList());
    }

    private List<Dvd> findDvdsByTitleKeywords(String title) {
        return titleKeywords(title)
                .stream()
                .flatMap(keyword -> dvdRepository.findDvdsByTitleContains(keyword).stream())
                .collect(Collectors.toList());
    }
}