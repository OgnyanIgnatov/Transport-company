package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.entity.VehicleType;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class VehicleDto {
    private long id;

    @NotBlank
    private String regNumber;

    @NotBlank
    private VehicleType type;
}
