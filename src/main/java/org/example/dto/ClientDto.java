package org.example.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClientDto {
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
