package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "companies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Company extends BaseEntity{
    @Column(unique = true)
    private String name;
    private long income;

    @OneToMany(mappedBy = "company")
    private Set<Employee> employees;

    @OneToMany(mappedBy = "company")
    private Set<Vehicle> vehicles;

    @OneToMany(mappedBy = "company")
    private Set<Service> services;

}
