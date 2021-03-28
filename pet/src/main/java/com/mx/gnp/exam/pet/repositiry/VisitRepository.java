package com.mx.gnp.exam.pet.repositiry;

import com.mx.gnp.exam.pet.repositiry.entyties.Visit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends CrudRepository<Visit, Integer> {
}
