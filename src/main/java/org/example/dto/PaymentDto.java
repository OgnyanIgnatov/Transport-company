package org.example.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.entity.Client;
import org.example.entity.Service;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class PaymentDto {
    private long id;

    @NotBlank
    private long price;

    @NotBlank
    @PastOrPresent
    private LocalDate paymentDate;

    @NotBlank
    private Client client;

    @NotBlank
    private Service service;
}
