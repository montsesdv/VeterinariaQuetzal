package com.mx.gnp.exam.pet.service;

import com.mx.gnp.exam.pet.api.model.PetRequest;
import com.mx.gnp.exam.pet.exception.SaveNotFoundException;
import com.mx.gnp.exam.pet.repositiry.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static com.mx.gnp.exam.pet.factory.ObjectFactory.createPet;
import static com.mx.gnp.exam.pet.factory.ObjectFactory.createPetRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class CreatePetServiceTest {

    @Mock
    private PetRepository petRepository;

    private CreatePetService createPetService;

    @BeforeEach
    public void init() {
        createPetService = new CreatePetService(petRepository);
    }

    @Test
    public void execute_whenAllIsOk_shouldReturnPetRequestObject() {

        when(petRepository.save(any())).thenReturn(createPet());

        final PetRequest petRequest = createPetService.execute(createPetRequest());

        assertThat(petRequest).isNotNull();

        verify(petRepository).save(any());
    }

    @Test
    public void execute_whenPetRepositoryFailed_shouldReturnException() {

        doThrow(new RuntimeException("Error while save information"))
                .when(petRepository).save(any());

        assertThatThrownBy(() -> createPetService.execute(createPetRequest()))
                .isExactlyInstanceOf(SaveNotFoundException.class);

        verify(petRepository).save(any());
    }

    @Test
    public void execute_whenPetRequestIsInvalid_shouldReturnException() {

        final PetRequest petRequest = createPetRequest();
        petRequest.setName(null);

        assertThatThrownBy(() -> createPetService.execute(petRequest))
                .isExactlyInstanceOf(SaveNotFoundException.class);
    }

    @Test
    public void execute_whenOwnerRequestIsInvalid_shouldReturnException() {

        final PetRequest petRequest = createPetRequest();
        petRequest.getOwner().setName(null);

        assertThatThrownBy(() -> createPetService.execute(petRequest))
                .isExactlyInstanceOf(SaveNotFoundException.class);
    }
}
