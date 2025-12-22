package org.example.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.*;
import org.example.entity.EmployeeCategory;
import org.example.entity.VehicleType;
import org.example.validator.InvalidDate;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
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

    @NotNull
    @Enumerated(EnumType.STRING)
    private EmployeeCategory requiredCategory = EmployeeCategory.D;

    @NotNull
    @Enumerated(EnumType.STRING)
    private VehicleType requiredVehicleType = VehicleType.CARRYING;

    @NotNull
    private double servicePrice;

    @Positive
    @DecimalMax("44") //tonnes. European limits
    private double weight;
}
