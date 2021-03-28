package com.mx.gnp.exam.pet.service;

import com.mx.gnp.exam.pet.api.model.*;
import com.mx.gnp.exam.pet.repositiry.PetRepository;
import com.mx.gnp.exam.pet.repositiry.entyties.Owner;
import com.mx.gnp.exam.pet.repositiry.entyties.Pet;
import com.mx.gnp.exam.pet.repositiry.entyties.Visit;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetPetsService {

    private final PetRepository petRepository;

    public Pets getAllPets(final String sorts, final Integer size, final Integer page) {

        final List<PetRequest> petRequests = new ArrayList<>();

        final org.springframework.data.domain.Page<Pet> pets = petRepository.findAll(
                createPageable(sorts, size, page));

        pets.getContent().forEach(pet -> petRequests.add(createPetRequestFromPet(pet)));

        final Page pageResponse = new Page();
        pageResponse.setSize((size != null && size > 0) ? size : 10);
        pageResponse.setTotalElements(pets.getTotalElements());
        pageResponse.setTotalPages(pets.getTotalPages());

        return new Pets(petRequests, pageResponse);
    }

    public Pets getPetFromOwner(final String name, final String middleName, final String lastName, final String motherLastName) {

        final List<PetRequest> petRequests = new ArrayList<>();
        final List<Pet> filterPets = petRepository.findByOwnerName(name, lastName, motherLastName, middleName);

        filterPets.forEach(pet -> petRequests.add(createPetRequestFromPet(pet)));

        return new Pets(petRequests, null);
    }

    private Pageable createPageable(final String sorts, final Integer size, final Integer page) {

        Sort sort = Sort.unsorted();

        if (sorts != null && !sorts.isEmpty()) {
            final Sort finalSorts = Sort.unsorted();
            for (final String s : sorts.split(";")) {
                final String[] sortSplit = s.split(",");
                if (sortSplit[0].equals("description")) {
                    if (sortSplit[1].equals("asc")) {
                        sort = sort.and(Sort.by("name").ascending());
                    } else {
                        sort = sort.and(Sort.by("name").descending());
                    }
                } else if (sortSplit[0].equals("title")) {
                    if (sortSplit[1].equals("asc")) {
                        sort = sort.and(Sort.by("owner.name").ascending());
                    } else {
                        sort = sort.and(Sort.by("owner.name").descending());
                    }
                }
            }
            sort = finalSorts;
        }

        return PageRequest.of(
                (page != null && page > 0) ? (page - 1) : 0,
                (size != null && size > 0) ? size : 10,
                sort);
    }

    private PetRequest createPetRequestFromPet(final Pet pet) {

        final PetRequest petRequest = new PetRequest();
        final Optional<Pet> optionalPet = Optional.of(pet);

        optionalPet.map(Pet::getId)
                .ifPresent(petRequest::setId);

        optionalPet.map(Pet::getAge)
                .ifPresent(petRequest::setAge);

        optionalPet.map(Pet::getBreed)
                .ifPresent(petRequest::setBreed);

        optionalPet.map(Pet::getColor)
                .ifPresent(petRequest::setColor);

        optionalPet.map(Pet::getName)
                .ifPresent(petRequest::setName);

        optionalPet.map(Pet::getGender)
                .ifPresent(petRequest::setGender);

        optionalPet.map(Pet::getOwner)
                .map(this::createOwnerRequestFromOwner)
                .ifPresent(petRequest::setOwner);

        optionalPet.map(Pet::getSize)
                .ifPresent(petRequest::setSize);

        optionalPet.map(Pet::getVisits)
                .filter(visits -> !visits.isEmpty())
                .map(visits -> visits.stream()
                        .map(this::createVisitRequestFromVisit).collect(Collectors.toList()))
                .ifPresent(petRequest::setVisits);

        return petRequest;
    }

    private OwnerRequest createOwnerRequestFromOwner(final Owner owner) {

        final OwnerRequest ownerRequest = new OwnerRequest();
        final Optional<Owner> optionalOwner = Optional.of(owner);

        optionalOwner.map(Owner::getId)
                .ifPresent(ownerRequest::setId);

        optionalOwner.map(Owner::getAddress)
                .ifPresent(ownerRequest::setAddress);

        optionalOwner.map(Owner::getLastName)
                .ifPresent(ownerRequest::setLastName);

        optionalOwner.map(Owner::getMiddleName)
                .ifPresent(ownerRequest::setMiddleName);

        optionalOwner.map(Owner::getMotherLastName)
                .ifPresent(ownerRequest::setMotherLastName);

        optionalOwner.map(Owner::getName)
                .ifPresent(ownerRequest::setName);

        optionalOwner.map(Owner::getPhoneNumber)
                .ifPresent(ownerRequest::setPhoneNumber);

        return ownerRequest;
    }

    private VisitRequest createVisitRequestFromVisit(final Visit visit) {

        final VisitRequest visitRequest = new VisitRequest();
        final Optional<Visit> optionalVisit = Optional.of(visit);

        optionalVisit.map(Visit::getId)
                .ifPresent(visitRequest::setId);

        optionalVisit.map(Visit::getDate)
                .ifPresent(visitRequest::setDate);

        optionalVisit.map(Visit::getDiagnosis)
                .ifPresent(visitRequest::setDiagnosis);

        optionalVisit.map(Visit::getSymptom)
                .ifPresent(visitRequest::setSymptom);

        return visitRequest;
    }
}
