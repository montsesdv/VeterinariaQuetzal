package com.mx.gnp.exam.pet.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PetRequest {

    private Integer id;

    @NotNull(message = "Name Pet cannot be null")
    @NotBlank(message = "Name Pet cannot be empty")
    private String name;

    @NotNull(message = "Breed Pet cannot be null")
    @NotBlank(message = "Breed Pet cannot be empty")
    private String breed;

    @NotNull(message = "Color Pet cannot be null")
    @NotBlank(message = "Color Pet cannot be empty")
    private String color;

    @NotNull(message = "Size Pet cannot be null")
    @NotBlank(message = "Size Pet cannot be empty")
    private String size;

    @NotNull(message = "Age Pet cannot be null")
    private Integer age;

    @NotNull(message = "Gender Pet cannot be null")
    @NotBlank(message = "Gender Pet cannot be empty")
    private String gender;

    @NotNull(message = "Owner Pet cannot be null")
    private OwnerRequest owner;

    private List<VisitRequest> visits;

    private Page page;
}
