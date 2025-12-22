package org.example.dto;


import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.example.entity.EmployeeCategory;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class EmployeeDto{

    private long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Pattern(regexp = "0\\d{9}")
    @Column(unique = true)
    private String telephoneNumber;

    @NotBlank
    @Pattern(regexp = "\\d{10}", message = "Invalid ID number")
    private String IDNumber;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private EmployeeCategory category;
}
