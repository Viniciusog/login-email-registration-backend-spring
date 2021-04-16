package com.viniciusog.registrationproject.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;


@Service
public class EmailValidator implements Predicate<String> {

    //Vamos validar se o e-mail está correto ou não
    @Override
    public boolean test(String s) {
        //TODO: Regex para validar e-mail
        return true;
    }
}
