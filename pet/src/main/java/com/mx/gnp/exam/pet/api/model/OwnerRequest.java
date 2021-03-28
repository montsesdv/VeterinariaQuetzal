package com.mx.gnp.exam.pet.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OwnerRequest {

    private Integer id;

    @NotNull(message = "Name Owner cannot be null")
    @NotBlank(message = "Name Owner cannot be empty")
    private String name;

    private String middleName;

    @NotNull(message = "LastName Owner cannot be null")
    @NotBlank(message = "LastName Owner cannot be empty")
    private String lastName;

    private String motherLastName;

    @NotNull(message = "Address Owner cannot be null")
    @NotBlank(message = "Address Owner cannot be empty")
    private String address;

    @NotNull(message = "PhoneNumber Owner cannot be null")
    @NotBlank(message = "PhoneNumber Owner cannot be empty")
    private String phoneNumber;
}
