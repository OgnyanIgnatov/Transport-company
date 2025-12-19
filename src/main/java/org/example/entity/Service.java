package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

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
public class Service extends BaseEntity{
    private String depLocation;
    private String arrLocation;
    private LocalDate depDate;
    private LocalDate arrDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @OneToMany(mappedBy = "service")
    private Set<Payment> payments;

}
