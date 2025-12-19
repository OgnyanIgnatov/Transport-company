package org.example.entity;

import jakarta.persistence.*;
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
    private String IDNumber;
    private long salary;

    @Enumerated(EnumType.STRING)
    private EmployeeCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @OneToMany(mappedBy = "employee")
    private Set<Service> services;
}
