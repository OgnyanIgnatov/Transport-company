package org.example.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.validator.InvalidDate;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@ToString
@InvalidDate
public class TransportServiceDto {
    private long id;
    @NotBlank
    private String depLocation;

    @NotBlank
    private String arrLocation;

    @NotBlank
    @FutureOrPresent
    private LocalDate depDate;

    @NotBlank
    @FutureOrPresent
    private LocalDate arrDate;

    @NotBlank
    @DecimalMax("44") //tonnes. European limits
    private long weight;
}
