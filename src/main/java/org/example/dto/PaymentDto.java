package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class PaymentDto {
    private long id;
    private long price;
    private LocalDate paymentDate;
}
