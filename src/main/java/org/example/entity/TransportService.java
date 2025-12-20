package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "transport_services")
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class TransportService extends Service{
    @NotBlank
    @DecimalMax("44") //tonnes. European limits
    private long weight;
}
