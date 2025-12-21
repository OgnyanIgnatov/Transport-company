package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
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
    @NotBlank
    private long price;

    @NotBlank
    @PastOrPresent
    private LocalDate paymentDate;

    @NotBlank
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @NotBlank
    @ManyToOne(fetch = FetchType.LAZY)
    private Service service;

}
