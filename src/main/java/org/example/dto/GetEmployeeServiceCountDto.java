package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class GetEmployeeServiceCountDto {
    private long employeeId;
    private String firstName;
    private String LastName;
    private long serviceId;
}
