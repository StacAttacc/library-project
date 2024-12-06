package com.example.tp4h23initial.daos;

import com.example.tp4h23initial.models.DocumentCopyStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DocumentCopyStockRepository extends JpaRepository<com.example.tp4h23initial.models.DocumentCopyStock, Long> {
    DocumentCopyStock findDocumentCopyStockById(long id);
}
