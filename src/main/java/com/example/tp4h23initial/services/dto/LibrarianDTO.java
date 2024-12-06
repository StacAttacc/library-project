package com.example.tp4h23initial.services.dto;

import com.example.tp4h23initial.models.users.Librarian;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LibrarianDTO {
    private Long id;
    private String firstName;
    private String lastName;

    public static LibrarianDTO fromEntity(Librarian librarian){
        LibrarianDTO librarianDTO = new LibrarianDTO();
        librarianDTO.setId(librarian.getId());
        librarianDTO.setFirstName(librarian.getFirstName());
        librarianDTO.setLastName(librarian.getFirstName());
        return librarianDTO;
    }

    public Librarian toEntity(){
        Librarian librarian = new Librarian();
        librarian.setId(this.getId());
        librarian.setFirstName(this.getFirstName());
        librarian.setLastName(this.getLastName());
        return librarian;
    }
}
