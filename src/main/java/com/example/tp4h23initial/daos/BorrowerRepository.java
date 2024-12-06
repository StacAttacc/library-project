package com.example.tp4h23initial.daos;

import com.example.tp4h23initial.models.users.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, Long> {
    Borrower findBorrowerById(Long id);
}
