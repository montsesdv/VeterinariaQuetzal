package com.mx.gnp.exam.pet.service;

import com.mx.gnp.exam.pet.api.model.PetRequest;
import com.mx.gnp.exam.pet.exception.OwnerNotFoundException;
import com.mx.gnp.exam.pet.repositiry.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.mx.gnp.exam.pet.factory.ObjectFactory.createOwner;
import static com.mx.gnp.exam.pet.factory.ObjectFactory.createPetRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UpdateOwnerServiceTest {

    @Mock
    private OwnerRepository ownerRepository;

    private UpdateOwnerService updateOwnerService;

    @BeforeEach
    public void init() {
        updateOwnerService = new UpdateOwnerService(ownerRepository);
    }

    @Test
    public void execute_whenAllIsOk_shouldReturnPetRequestObject() {

        when(ownerRepository.existsById(any())).thenReturn(true);
        when(ownerRepository.save(any())).thenReturn(createOwner());

        final PetRequest petRequest = createPetRequest();
        petRequest.setId(1);
        petRequest.getOwner().setId(1);

        final PetRequest petResponse = updateOwnerService.execute(petRequest);

        assertThat(petResponse).isNotNull();

        verify(ownerRepository).existsById(any());
        verify(ownerRepository).save(any());
    }

    @Test
    public void execute_whenNotExist_shouldException() {

        when(ownerRepository.existsById(any())).thenReturn(false);

        assertThatThrownBy(() -> updateOwnerService.execute(createPetRequest()))
                .isExactlyInstanceOf(OwnerNotFoundException.class);


        verify(ownerRepository).existsById(any());
    }
}
