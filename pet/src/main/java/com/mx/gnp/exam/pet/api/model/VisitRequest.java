package com.mx.gnp.exam.pet.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VisitRequest {

    private Integer id;

    @NotNull(message = "Pet cannot be null")
    private PetRequest pet;

    @NotNull(message = "Date Visit cannot be null")
    private Date date;

    @NotNull(message = "Symptom Visit cannot be null")
    @NotBlank(message = "Symptom Visit cannot be empty")
    private String symptom;

    @NotNull(message = "Diagnosis Visit cannot be null")
    @NotBlank(message = "Diagnosis Visit cannot be empty")
    private String diagnosis;
}
