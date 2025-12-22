package org.example.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.entity.VehicleType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class VehicleDto {
    private long id;

    @NotBlank
    private String regNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    private VehicleType type;
}
