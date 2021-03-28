package com.mx.gnp.exam.pet.repositiry.entyties;

import com.mx.gnp.exam.pet.api.model.VisitRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pet")
@NamedQuery(name = "Pet.findByOwnerName", query = "SELECT p FROM Pet p\n" +
        "WHERE p.owner in (\n" +
        "SELECT own from Owner own\n" +
        "where own.name = ?1 and own.lastName = ?2 and (?3 is null or own.motherLastName = ?3) and (?4 is null or own.middleName = ?4))")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String breed;
    private String color;
    private String size;
    private int age;
    private String gender;
    @OneToOne(cascade = {CascadeType.ALL})
    private Owner owner;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pet_id")
    private List<Visit> visits;
}
