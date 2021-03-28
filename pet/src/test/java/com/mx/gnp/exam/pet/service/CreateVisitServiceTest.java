package com.mx.gnp.exam.pet.service;

import com.mx.gnp.exam.pet.api.model.PetRequest;
import com.mx.gnp.exam.pet.api.model.VisitRequest;
import com.mx.gnp.exam.pet.exception.SaveNotFoundException;
import com.mx.gnp.exam.pet.repositiry.VisitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.mx.gnp.exam.pet.factory.ObjectFactory.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class CreateVisitServiceTest {

    @Mock
    private VisitRepository visitRepository;

    private CreateVisitService createVisitService;

    @BeforeEach
    public void init() {
        createVisitService = new CreateVisitService(visitRepository);
    }

    @Test
    public void execute_whenAllIsOk_shouldReturnPetRequestObject() {

        when(visitRepository.save(any())).thenReturn(createVisit());

        final VisitRequest visitRequest = createVisitService.execute(createVisitRequest());

        assertThat(visitRequest).isNotNull();

        verify(visitRepository).save(any());
    }

    @Test
    public void execute_whenPetRepositoryFailed_shouldReturnException() {

        doThrow(new RuntimeException("Error while save information"))
                .when(visitRepository).save(any());

        assertThatThrownBy(() -> createVisitService.execute(createVisitRequest()))
                .isExactlyInstanceOf(SaveNotFoundException.class);

        verify(visitRepository).save(any());
    }

    @Test
    public void execute_whenVisitRequestIsInvalid_shouldReturnException() {

        final VisitRequest petRequest = createVisitRequest();
        petRequest.setSymptom(null);

        assertThatThrownBy(() -> createVisitService.execute(petRequest))
                .isExactlyInstanceOf(SaveNotFoundException.class);
    }

    @Test
    public void execute_whenPetRequestIsInvalid_shouldReturnException() {

        final VisitRequest petRequest = createVisitRequest();
        petRequest.getPet().setName(null);

        assertThatThrownBy(() -> createVisitService.execute(petRequest))
                .isExactlyInstanceOf(SaveNotFoundException.class);
    }

    @Test
    public void execute_whenOwnerRequestIsInvalid_shouldReturnException() {

        final VisitRequest petRequest = createVisitRequest();
        petRequest.getPet().getOwner().setName(null);

        assertThatThrownBy(() -> createVisitService.execute(petRequest))
                .isExactlyInstanceOf(SaveNotFoundException.class);
    }
}
