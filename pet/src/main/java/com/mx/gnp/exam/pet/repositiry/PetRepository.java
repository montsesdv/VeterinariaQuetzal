package com.mx.gnp.exam.pet.repositiry;

import com.mx.gnp.exam.pet.repositiry.entyties.Pet;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends PagingAndSortingRepository<Pet, Integer> {

    public List<Pet> findByOwnerName(final String name, final String lastName, final String motherLastName, final String middleName);
}
