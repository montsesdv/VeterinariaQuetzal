package com.mx.gnp.exam.pet.service;

import com.mx.gnp.exam.pet.api.model.OwnerRequest;
import com.mx.gnp.exam.pet.api.model.PetRequest;
import com.mx.gnp.exam.pet.api.model.VisitRequest;
import com.mx.gnp.exam.pet.exception.SaveNotFoundException;
import com.mx.gnp.exam.pet.repositiry.VisitRepository;
import com.mx.gnp.exam.pet.repositiry.entyties.Owner;
import com.mx.gnp.exam.pet.repositiry.entyties.Pet;
import com.mx.gnp.exam.pet.repositiry.entyties.Visit;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.mx.gnp.exam.pet.util.Validation.*;

@Service
@AllArgsConstructor
public class CreateVisitService {

    private final VisitRepository visitRepository;

    public VisitRequest execute(final VisitRequest visitRequest) {

        validateVisitDataInput(visitRequest);
        validatePetDataInput(visitRequest.getPet());
        validateOwnerDataInput(visitRequest.getPet().getOwner());

        final Visit visit;

        try {
            visit = visitRepository.save(createVisitFromVisitRequest(visitRequest));
        } catch (Exception ex) {
            throw new SaveNotFoundException("");
        }

        visitRequest.setId(visit.getId());

        return visitRequest;
    }

    private Visit createVisitFromVisitRequest(final VisitRequest visitRequest) {

        final Visit visit = new Visit();

        visit.setDate(visitRequest.getDate());
        visit.setDiagnosis(visitRequest.getDiagnosis());
        visit.setSymptom(visitRequest.getSymptom());
        visit.setPet(createPetEntityFromPetRequest(visitRequest.getPet()));

        return visit;
    }

    private Pet createPetEntityFromPetRequest(final PetRequest newPet) {
        final Pet pet = new Pet();

        pet.setId(newPet.getId());
        pet.setAge(newPet.getAge());
        pet.setBreed(newPet.getBreed());
        pet.setColor(newPet.getColor());
        pet.setGender(newPet.getGender());
        pet.setName(newPet.getName());
        pet.setOwner(createOwnerFromPetRequest(newPet.getOwner()));
        pet.setSize(newPet.getSize());

        return pet;
    }

    private Owner createOwnerFromPetRequest(final OwnerRequest oldOwnerRequest) {

        final Owner owner = new Owner();

        owner.setId(oldOwnerRequest.getId());
        owner.setAddress(oldOwnerRequest.getAddress());
        owner.setLastName(oldOwnerRequest.getLastName());
        owner.setMiddleName(oldOwnerRequest.getMiddleName());
        owner.setMotherLastName(oldOwnerRequest.getMotherLastName());
        owner.setPhoneNumber(oldOwnerRequest.getPhoneNumber());
        owner.setName(oldOwnerRequest.getName());

        return owner;
    }
}
