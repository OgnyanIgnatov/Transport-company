package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Payment extends BaseEntity{

    @Positive
    private double price;

    @NotNull
    @PastOrPresent
    private LocalDate paymentDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Service service;

}
