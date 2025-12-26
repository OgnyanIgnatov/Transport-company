package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Employee extends Person{

    @NotBlank
    @Pattern(regexp = "\\d{10}", message = "Invalid ID number")
    private String IDNumber;

    @Positive
    private double salary;

    @Enumerated(EnumType.STRING)
    @NotNull
    private EmployeeCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @OneToMany(mappedBy = "employee")
    private Set<Service> services;
}
