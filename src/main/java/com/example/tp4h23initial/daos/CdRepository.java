package com.example.tp4h23initial.daos;

import com.example.tp4h23initial.models.documents.Cd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CdRepository extends JpaRepository<Cd, Long> {
    List<Cd> findCdsByAuthor(String author);
    List<Cd> findCdsByYearOfPublishing(int year);
    List<Cd> findCdsByGenre(String genre);
    List<Cd> findCdsByTitleContains(String titleSubstring);
}
