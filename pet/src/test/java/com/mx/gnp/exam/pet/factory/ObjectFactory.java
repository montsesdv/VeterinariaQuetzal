package com.mx.gnp.exam.pet.factory;

import com.mx.gnp.exam.pet.api.model.OwnerRequest;
import com.mx.gnp.exam.pet.api.model.PetRequest;
import com.mx.gnp.exam.pet.api.model.VisitRequest;
import com.mx.gnp.exam.pet.repositiry.entyties.Owner;
import com.mx.gnp.exam.pet.repositiry.entyties.Pet;
import com.mx.gnp.exam.pet.repositiry.entyties.Visit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ObjectFactory {

    public static Page<Pet> createPagePet() {

        return new PageImpl<>(createListPet());
    }

    public static List<Pet> createListPet() {

        final List<Pet> pets = new ArrayList<>();
        pets.add(createPet());

        return pets;
    }

    public static List<Visit> createListVisit() {

        final List<Visit> pets = new ArrayList<>();
        pets.add(createVisit());

        return pets;
    }

    public static VisitRequest createVisitRequest() {

        final VisitRequest visitRequest = new VisitRequest();

        visitRequest.setSymptom("dolor");
        visitRequest.setDiagnosis("indigestion");
        visitRequest.setDate(new Date());
        visitRequest.setPet(createPetRequest());

        return visitRequest;
    }

    public static PetRequest createPetRequest() {

        final PetRequest petRequest = new PetRequest();

        petRequest.setName("Rex");
        petRequest.setBreed("Huxky");
        petRequest.setColor("Gris");
        petRequest.setSize("Grande");
        petRequest.setAge(5);
        petRequest.setGender("Macho");
        petRequest.setOwner(createOwnerRequest());

        return petRequest;
    }

    public static OwnerRequest createOwnerRequest() {

        final OwnerRequest ownerRequest = new OwnerRequest();

        ownerRequest.setName("Montse");
        ownerRequest.setLastName("De La Paz");
        ownerRequest.setMotherLastName("Villa");
        ownerRequest.setAddress("Domicilio");
        ownerRequest.setPhoneNumber("5513141516");

        return ownerRequest;
    }

    public static Visit createVisit() {

        final Visit visit = new Visit();

        visit.setPet(createPet());
        visit.setSymptom("Dolor");
        visit.setDiagnosis("Indiguestion");
        visit.setDate(new Date());
        visit.setId(1);

        return visit;
    }

    public static Pet createPet() {

        final Pet pet = new Pet();

        pet.setId(1);
        pet.setName("Rex");
        pet.setBreed("Huxky");
        pet.setColor("Gris");
        pet.setSize("Grande");
        pet.setAge(5);
        pet.setGender("Macho");
        pet.setOwner(createOwner());

        return pet;
    }

    public static Owner createOwner() {

        final Owner owner = new Owner();

        owner.setId(1);
        owner.setName("Montse");
        owner.setLastName("De La Paz");
        owner.setMotherLastName("Villa");
        owner.setAddress("Domicilio");
        owner.setPhoneNumber("5513141516");

        return owner;
    }
}
