package org.example.dto;

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
    private String registration;
    private VehicleType type;
}
