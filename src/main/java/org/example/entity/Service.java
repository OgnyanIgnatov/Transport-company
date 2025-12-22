package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.validator.InvalidDate;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "services")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
@InvalidDate
public class Service extends BaseEntity{
    @NotBlank
    private String depLocation;

    @NotBlank
    private String arrLocation;

    @NotNull
    @FutureOrPresent
    private LocalDate depDate;

    @NotNull
    @FutureOrPresent
    private LocalDate arrDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EmployeeCategory requiredCategory;

    @NotNull
    @Enumerated(EnumType.STRING)
    private VehicleType requiredVehicleType;

    @NotNull
    private double servicePrice;

    @OneToMany(mappedBy = "service")
    private Set<Payment> payments;

}
