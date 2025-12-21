package org.example.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CompanyDto {
    private long id;

    @Column(unique = true)
    @NotBlank
    private String name;

    private long income = 0;

}
