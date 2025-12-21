package org.example.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
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

    @NotBlank
    private long price;

    @NotBlank
    @PastOrPresent
    private LocalDate paymentDate;

    @NotBlank
    private long clientId;

    @NotBlank
    private long serviceId;
}
