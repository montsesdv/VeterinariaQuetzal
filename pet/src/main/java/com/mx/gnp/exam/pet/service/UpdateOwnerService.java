package com.mx.gnp.exam.pet.service;

import com.mx.gnp.exam.pet.api.model.OwnerRequest;
import com.mx.gnp.exam.pet.api.model.PetRequest;
import com.mx.gnp.exam.pet.exception.OwnerNotFoundException;
import com.mx.gnp.exam.pet.repositiry.OwnerRepository;
import com.mx.gnp.exam.pet.repositiry.entyties.Owner;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateOwnerService {

    private final OwnerRepository ownerRepository;

    public PetRequest execute(final PetRequest updatePet) {

        final Owner ownerUpdate = createOwnerFromPetRequest(updatePet.getOwner());

        if (ownerRepository.existsById(ownerUpdate.getId())) {
            ownerRepository.save(ownerUpdate);
        } else {
            throw new OwnerNotFoundException(ownerUpdate.getId());
        }

        return updatePet;
    }

    private Owner createOwnerFromPetRequest(final OwnerRequest oldOwnerRequest) {

        final Owner ownerUpdate = new Owner();

        ownerUpdate.setId(oldOwnerRequest.getId());
        ownerUpdate.setAddress(oldOwnerRequest.getAddress());
        ownerUpdate.setLastName(oldOwnerRequest.getLastName());
        ownerUpdate.setMiddleName(oldOwnerRequest.getMiddleName());
        ownerUpdate.setMotherLastName(oldOwnerRequest.getMotherLastName());
        ownerUpdate.setPhoneNumber(oldOwnerRequest.getPhoneNumber());
        ownerUpdate.setName(oldOwnerRequest.getName());

        return ownerUpdate;
    }
}
