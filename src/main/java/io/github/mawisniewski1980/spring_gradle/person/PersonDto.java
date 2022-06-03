package io.github.mawisniewski1980.spring_gradle.person;

import io.github.mawisniewski1980.spring_gradle.model.Address;
import io.github.mawisniewski1980.spring_gradle.model.Card;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PersonDto {

    private String name;
    private String lastname;
    private LocalDate birthday;
    private Address address;
    private String email;
    private String phone;
    private Card card;
    private String iban;

}
