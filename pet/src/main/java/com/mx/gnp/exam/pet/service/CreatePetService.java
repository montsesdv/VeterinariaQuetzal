package com.mx.gnp.exam.pet.service;

import com.mx.gnp.exam.pet.api.model.OwnerRequest;
import com.mx.gnp.exam.pet.api.model.PetRequest;
import com.mx.gnp.exam.pet.exception.SaveNotFoundException;
import com.mx.gnp.exam.pet.repositiry.PetRepository;
import com.mx.gnp.exam.pet.repositiry.entyties.Owner;
import com.mx.gnp.exam.pet.repositiry.entyties.Pet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.mx.gnp.exam.pet.util.Validation.validateOwnerDataInput;
import static com.mx.gnp.exam.pet.util.Validation.validatePetDataInput;

@Service
@AllArgsConstructor
public class CreatePetService {

    private final PetRepository petRepository;

    public PetRequest execute(final PetRequest newPet) {

        validatePetDataInput(newPet);
        validateOwnerDataInput(newPet.getOwner());

        Pet pet;

        try {
            pet = petRepository.save(createPetEntityFromPetRequest(newPet));
        } catch (Exception ex) {
            throw new SaveNotFoundException("");
        }

        setIdToPetRequest(pet, newPet);
        return newPet;
    }

    private Pet createPetEntityFromPetRequest(final PetRequest newPet) {

        final Pet pet = new Pet();
        final Optional<PetRequest> petRequestOptional = Optional.of(newPet);

        petRequestOptional.map(PetRequest::getAge)
                .ifPresent(pet::setAge);

        petRequestOptional.map(PetRequest::getBreed)
                .ifPresent(pet::setBreed);

        petRequestOptional.map(PetRequest::getColor)
                .ifPresent(pet::setColor);

        petRequestOptional.map(PetRequest::getGender)
                .ifPresent(pet::setGender);

        petRequestOptional.map(PetRequest::getName)
                .ifPresent(pet::setName);

        petRequestOptional.map(PetRequest::getOwner)
                .ifPresent(ownerRequest -> pet.setOwner(createOwnerFromPetRequest(ownerRequest)));

        petRequestOptional.map(PetRequest::getSize)
                .ifPresent(pet::setSize);

        return pet;
    }

    private Owner createOwnerFromPetRequest(final OwnerRequest newOwnerRequest) {

        final Owner owner = new Owner();
        final Optional<OwnerRequest> optionalOwnerRequest = Optional.of(newOwnerRequest);

        optionalOwnerRequest.map(OwnerRequest::getAddress)
                .ifPresent(owner::setAddress);

        optionalOwnerRequest.map(OwnerRequest::getLastName)
                .ifPresent(owner::setLastName);

        optionalOwnerRequest.map(OwnerRequest::getMiddleName)
                .ifPresent(owner::setMiddleName);

        optionalOwnerRequest.map(OwnerRequest::getMotherLastName)
                .ifPresent(owner::setMotherLastName);

        optionalOwnerRequest.map(OwnerRequest::getPhoneNumber)
                .ifPresent(owner::setPhoneNumber);

        optionalOwnerRequest.map(OwnerRequest::getName)
                .ifPresent(owner::setName);

        return owner;
    }

    private void setIdToPetRequest(final Pet pet, final PetRequest newPet) {

        newPet.setId(pet.getId());
        newPet.getOwner().setId(pet.getOwner().getId());
    }
}
