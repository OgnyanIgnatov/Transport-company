package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "vehicles")
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Vehicle extends BaseEntity{
    private String regNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

    @OneToMany(mappedBy = "vehicle")
    private Set<Service> services;
}
