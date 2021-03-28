package com.mx.gnp.exam.pet.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.gnp.exam.pet.PetApplication;
import com.mx.gnp.exam.pet.api.model.PetRequest;
import com.mx.gnp.exam.pet.factory.ObjectFactory;
import com.mx.gnp.exam.pet.repositiry.OwnerRepository;
import com.mx.gnp.exam.pet.repositiry.PetRepository;
import com.mx.gnp.exam.pet.repositiry.VisitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.mx.gnp.exam.pet.factory.ObjectFactory.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {PetApplication.class})
@WebMvcTest
public class IntegrationTest {

    private final static String GET_PET_URI = "/pet";
    private final static String GET_PETS_URI = "/pets";
    private final static String POST_PET_URI = "/createPet";
    private final static String POST_VISIT_URI = "/createVisit";
    private final static String PUT_VISIT_URI = "/modifyOwner";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PetRepository petRepository;

    @MockBean
    private OwnerRepository ownerRepository;

    @MockBean
    private VisitRepository visitRepository;

    @BeforeEach
    public void init() {

        when(petRepository.save(any())).thenReturn(ObjectFactory.createPet());
        when(visitRepository.save(any())).thenReturn(ObjectFactory.createVisit());
        when(ownerRepository.existsById(anyInt())).thenReturn(true);
        when(ownerRepository.save(any())).thenReturn(ObjectFactory.createOwner());
        when(petRepository.findAll(any(Pageable.class))).thenReturn(createPagePet());
        when(petRepository.findByOwnerName(anyString(), anyString(), anyString(), anyString())).thenReturn(createListPet());
    }

    @Test
    void createPet() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(POST_PET_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createPetRequest())))
                .andExpect(status().isOk());
    }

    @Test
    void createVisit() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(POST_VISIT_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createVisitRequest())))
                .andExpect(status().isOk());
    }

    @Test
    void modifyOwner() throws Exception {

        final PetRequest petRequest = createPetRequest();
        petRequest.setId(1);
        petRequest.getOwner().setId(1);

        mockMvc.perform(MockMvcRequestBuilders.put(PUT_VISIT_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(petRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void getPets() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(GET_PETS_URI)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getPet() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(GET_PET_URI)
                .contentType(MediaType.APPLICATION_JSON)
        .param("name", "m")
        .param("middleName", "m")
        .param("lastName", "m")
        .param("motherLastName", "m"))
                .andExpect(status().isOk());
    }

}
