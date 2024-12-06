package com.example.tp4h23initial.services.dto;

import com.example.tp4h23initial.models.users.Borrower;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BorrowerDTO {
    private Long id;
    private String firstName;
    private String lastName;

    public static BorrowerDTO fromEntity(Borrower borrower) {
        return BorrowerDTO.builder()
                .id(borrower.getId())
                .firstName(borrower.getFirstName())
                .lastName(borrower.getLastName())
                .build();
    }

    public Borrower toEntity() {
        Borrower borrower = new Borrower();
        borrower.setId(this.id);
        borrower.setFirstName(this.firstName);
        borrower.setLastName(this.lastName);
        return borrower;
    }
}
