package com.example.tp4h23initial.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewDocumentLoanDTO {
    private Long borrowerId;
    private Long documentCopyStockId;
}
