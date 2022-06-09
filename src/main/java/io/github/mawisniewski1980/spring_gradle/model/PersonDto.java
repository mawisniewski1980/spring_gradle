package io.github.mawisniewski1980.spring_gradle.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
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
