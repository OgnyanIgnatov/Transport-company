package org.example.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.entity.EmployeeCategory;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDto {
    private long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Pattern(regexp = "'+'\\d{12}")
    private String telephoneNumber;

    @NotBlank
    private EmployeeCategory category;
}
