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
public class PassengerServiceDto {
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

    @NotNull
    @Enumerated(EnumType.STRING)
    private EmployeeCategory requiredCategory = EmployeeCategory.C;

    @NotNull
    @Enumerated(EnumType.STRING)
    private VehicleType requiredVehicleType = VehicleType.PASSENGER;

    @NotNull
    private double servicePrice;

    @Positive
    @DecimalMax(value = "50")
    private long passengerCount;
}
