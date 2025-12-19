package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "people")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
public class Person extends BaseEntity{
    private String firstName;
    private String lastName;
    private String telephoneNumber;
}
