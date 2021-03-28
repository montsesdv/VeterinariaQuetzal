package com.mx.gnp.exam.pet.util;

import com.mx.gnp.exam.pet.api.model.OwnerRequest;
import com.mx.gnp.exam.pet.api.model.PetRequest;
import com.mx.gnp.exam.pet.api.model.VisitRequest;
import com.mx.gnp.exam.pet.exception.SaveNotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Validation {

    public static void validateVisitDataInput(final VisitRequest newVisit) {

        final StringBuilder errorMessage = new StringBuilder();
        final ValidatorFactory factory = javax.validation.Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();
        final Set<ConstraintViolation<VisitRequest>> violations = validator.validate(newVisit);

        for (ConstraintViolation<VisitRequest> violation : violations) {
            errorMessage.append(violation.getMessage() + ",");
        }

        if (!errorMessage.toString().isEmpty()) {
            throw new SaveNotFoundException(errorMessage.toString().substring(0, errorMessage.toString().length() - 1));
        }
    }

    public static void validatePetDataInput(final PetRequest newPet) {

        final StringBuilder errorMessage = new StringBuilder();
        final ValidatorFactory factory = javax.validation.Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();
        final Set<ConstraintViolation<PetRequest>> violations = validator.validate(newPet);

        for (ConstraintViolation<PetRequest> violation : violations) {
            errorMessage.append(violation.getMessage() + ",");
        }

        if (!errorMessage.toString().isEmpty()) {
            throw new SaveNotFoundException(errorMessage.toString().substring(0, errorMessage.toString().length() - 1));
        }
    }

    public static void validateOwnerDataInput(final OwnerRequest newOwner) {

        final StringBuilder errorMessage = new StringBuilder();
        final ValidatorFactory factory = javax.validation.Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();
        final Set<ConstraintViolation<OwnerRequest>> violations = validator.validate(newOwner);

        for (ConstraintViolation<OwnerRequest> violation : violations) {
            errorMessage.append(violation.getMessage() + ",");
        }

        if (!errorMessage.toString().isEmpty()) {
            throw new SaveNotFoundException(errorMessage.toString().substring(0, errorMessage.toString().length() - 1));
        } else {

        }
    }
}
