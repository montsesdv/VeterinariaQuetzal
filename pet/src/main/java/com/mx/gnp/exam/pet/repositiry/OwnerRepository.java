package com.mx.gnp.exam.pet.repositiry;

import com.mx.gnp.exam.pet.repositiry.entyties.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Integer> {
}
