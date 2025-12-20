package org.example.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.entity.Service;

import java.time.LocalDate;

public class InvalidDateValidator implements ConstraintValidator<InvalidDate, Service> {

    @Override
    public boolean isValid(Service service, ConstraintValidatorContext context){

        return !service.getArrDate().isBefore(service.getDepDate());
    }
}
