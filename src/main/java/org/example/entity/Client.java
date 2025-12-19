package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "clients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Client extends Person{
    private LocalDate birthDate;

    @OneToMany(mappedBy = "client")
    private Set<Payment> payments;
}
