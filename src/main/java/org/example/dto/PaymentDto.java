package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.example.entity.Client;
import org.example.entity.Service;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaymentDto {
    private long id;

    @Positive
    private double price;

    @NotNull
    @PastOrPresent
    private LocalDate paymentDate;

    @Positive
    private long clientId;

    @Positive
    private long serviceId;
}
