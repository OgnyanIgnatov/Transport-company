package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class TransportServiceDto {
    private long id;
    private String depLocation;
    private String arrLocation;
    private LocalDate depDate;
    private LocalDate arrDate;
    private long weight;
}
