package io.github.mawisniewski1980.spring_gradle.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Card {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String creditCardNumber;
    private String creditCardExpiry;
    private String cardType;

}
