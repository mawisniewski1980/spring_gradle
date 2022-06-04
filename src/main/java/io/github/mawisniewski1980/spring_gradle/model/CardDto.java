package io.github.mawisniewski1980.spring_gradle.model;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
public class CardDto {

    private String creditCardNumber;
    private String creditCardExpiry;
    private String cardType;

}
