package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "passenger_service")
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class PassengerService extends Service{
    private long passengerCount;
}
