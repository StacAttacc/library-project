package com.example.tp4h23initial.daos;

import com.example.tp4h23initial.models.documents.Dvd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DvdRepository extends JpaRepository<Dvd, Long> {
    List<Dvd> findDvdsByTitleContains(String titleSubstring);
    List<Dvd> findDvdsByAuthor(String author);
    List<Dvd> findDvdsByYearOfPublishing(int year);
    List<Dvd> findDvdsByGenre(String genre);
}