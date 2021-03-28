package com.mx.gnp.exam.pet.api.controller;

import com.mx.gnp.exam.pet.api.model.PetRequest;
import com.mx.gnp.exam.pet.api.model.Pets;
import com.mx.gnp.exam.pet.api.model.VisitRequest;
import com.mx.gnp.exam.pet.service.CreatePetService;
import com.mx.gnp.exam.pet.service.CreateVisitService;
import com.mx.gnp.exam.pet.service.GetPetsService;
import com.mx.gnp.exam.pet.service.UpdateOwnerService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
public class PetController {

    private final CreatePetService createPetService;
    private final UpdateOwnerService updateOwnerService;
    private final CreateVisitService createVisitService;
    private final GetPetsService getPetsService;

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            value = "createPet")
    public PetRequest postPet(@RequestBody PetRequest newPet) {
        return createPetService.execute(newPet);
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            value = "createVisit")
    public VisitRequest postVisit(@RequestBody VisitRequest newVisit) {
        return createVisitService.execute(newVisit);
    }

    @RequestMapping(method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            value = "modifyOwner")
    public PetRequest putOwner(@RequestBody PetRequest oldPet) {
        return updateOwnerService.execute(oldPet);
    }

    //@Validated
    @RequestMapping(method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            value = "pets")
    public Pets getPets(@RequestParam(name = "sort", required = false) final List<String> sorts,
                        //@ValuesAllowed(propName = "sorts", values = { "description,asc", "description,dsc", "title,asc" })
                        @RequestParam(required = false) final Integer size,
                        @RequestParam(required = false) final Integer page) {

        return getPetsService.getAllPets(StringUtils.join(sorts, ';'), size, page);
    }

    @RequestMapping(method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            value = "pet")
    public Pets getPet(@RequestParam(required = true) final String name,
                         @RequestParam(required = false) final String middleName,
                         @RequestParam(required = true) final String lastName,
                         @RequestParam(required = false) final String motherLastName) {
        return getPetsService.getPetFromOwner(name, middleName, lastName, motherLastName);
    }
}
