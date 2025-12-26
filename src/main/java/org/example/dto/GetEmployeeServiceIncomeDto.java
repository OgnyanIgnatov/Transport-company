package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class GetEmployeeServiceIncomeDto {
    private long employeeId;
    private String firstName;
    private String lastName;
    private double incomeSum;
}
