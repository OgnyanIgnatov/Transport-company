package org.example.dto;

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

    @NotBlank
    private String name;

    private long income = 0;

}
