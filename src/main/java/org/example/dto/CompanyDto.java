package org.example.dto;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CompanyDto {
    private long id;
    private String name;
    private long income;

}
