package com.viniciusog.registrationproject.registration;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    //Essa classe representa o objeto request que será feito quando uma pessoa
    //se cadastrar no sistema

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
}
