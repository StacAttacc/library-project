package com.example.tp4h23initial.services.dto;

import com.example.tp4h23initial.models.DocumentCopyStock;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentCopyStockDTO {
    private Long id;
    private Long documentId;
    private int nbAvailableCopies;
    private String documentTitle;

    public static DocumentCopyStockDTO fromEntity(DocumentCopyStock documentCopyStock) {
        DocumentCopyStockDTO dto = new DocumentCopyStockDTO();
        dto.setId(documentCopyStock.getId());
        dto.setDocumentId(documentCopyStock.getDocument().getId());
        dto.setNbAvailableCopies(documentCopyStock.getNbAvailableCopies());
        dto.setDocumentTitle(documentCopyStock.getDocument().getTitle());
        return dto;
    }
    
    public DocumentCopyStock toEntity(DocumentCopyStockDTO documentCopyStockDTO){
        DocumentCopyStock documentCopyStock = new DocumentCopyStock();
        documentCopyStock.setId(documentCopyStockDTO.getId());
        documentCopyStock.setDocument(documentCopyStock.getDocument());
        documentCopyStock.setNbAvailableCopies(documentCopyStockDTO.getNbAvailableCopies());
        return documentCopyStock;
    }
}

