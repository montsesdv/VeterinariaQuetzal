package com.mx.gnp.exam.pet.service;

import com.mx.gnp.exam.pet.api.model.Pets;
import com.mx.gnp.exam.pet.repositiry.PetRepository;
import com.mx.gnp.exam.pet.repositiry.entyties.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.mx.gnp.exam.pet.factory.ObjectFactory.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class GetPetsServiceTest {

    @Mock
    private PetRepository petRepository;

    private GetPetsService getPetsService;

    @BeforeEach
    public void init() {
        getPetsService = new GetPetsService(petRepository);
    }

    @Test
    public void getAllPets_whenAllIsOk_shouldReturnPets() {

        when(petRepository.findAll(any(Pageable.class))).thenReturn(createPagePet());

        final Pets pets = getPetsService.getAllPets(null, null, null);

        assertThat(pets).isNotNull();

        verify(petRepository).findAll(any(Pageable.class));
    }

    @Test
    public void getAllPets_whenSizeAllIsOk_shouldReturnPets() {

        List<Pet> petList = createListPet();
        petList.get(0).setVisits(createListVisit());

        when(petRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(petList));

        final Pets pets = getPetsService.getAllPets(null, 15, null);

        assertThat(pets).isNotNull();

        verify(petRepository).findAll(any(Pageable.class));
    }

    @Test
    public void getAllPets_whenPageAllIsOk_shouldReturnPets() {

        when(petRepository.findAll(any(Pageable.class))).thenReturn(createPagePet());

        final Pets pets = getPetsService.getAllPets(null, null, 1);

        assertThat(pets).isNotNull();

        verify(petRepository).findAll(any(Pageable.class));
    }

    @Test
    public void getAllPets_whenSortAscAllIsOk_shouldReturnPets() {

        when(petRepository.findAll(any(Pageable.class))).thenReturn(createPagePet());

        final Pets pets = getPetsService.getAllPets("description,asc", null, null);

        assertThat(pets).isNotNull();

        verify(petRepository).findAll(any(Pageable.class));
    }

    @Test
    public void getAllPets_whenSortDescAllIsOk_shouldReturnPets() {

        when(petRepository.findAll(any(Pageable.class))).thenReturn(createPagePet());

        final Pets pets = getPetsService.getAllPets("description,desc", null, null);

        assertThat(pets).isNotNull();

        verify(petRepository).findAll(any(Pageable.class));
    }

    @Test
    public void getAllPets_whenSortTitleAscAllIsOk_shouldReturnPets() {

        when(petRepository.findAll(any(Pageable.class))).thenReturn(createPagePet());

        final Pets pets = getPetsService.getAllPets("title,asc", null, null);

        assertThat(pets).isNotNull();

        verify(petRepository).findAll(any(Pageable.class));
    }
    @Test
    public void getAllPets_whenSortTitleDescAllIsOk_shouldReturnPets() {

        when(petRepository.findAll(any(Pageable.class))).thenReturn(createPagePet());

        final Pets pets = getPetsService.getAllPets("title,desc", null, null);

        assertThat(pets).isNotNull();

        verify(petRepository).findAll(any(Pageable.class));
    }

    @Test
    public void getPetFromOwner_whenAllIsOk_shouldReturnPets() {

        when(petRepository.findByOwnerName(anyString(), anyString(), anyString(), isNull())).thenReturn(createListPet());

        final Pets pets = getPetsService.getPetFromOwner("", null, "", "");

        assertThat(pets).isNotNull();

        verify(petRepository).findByOwnerName(anyString(), anyString(), anyString(), isNull());
    }
}
