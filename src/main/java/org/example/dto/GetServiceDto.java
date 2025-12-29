package org.example.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class GetServiceDto {
    private long id;

    @NotBlank
    private String depLocation;

    @NotBlank
    private String arrLocation;

    @NotBlank
    private LocalDate depDate;

    @NotBlank
    @FutureOrPresent
    private LocalDate arrDate;
}
