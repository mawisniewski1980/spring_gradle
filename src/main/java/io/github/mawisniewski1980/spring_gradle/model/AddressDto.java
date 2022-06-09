package io.github.mawisniewski1980.spring_gradle.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressDto {

    private String street;
    private String zipCode;
    private String city;
    private String state;

}
