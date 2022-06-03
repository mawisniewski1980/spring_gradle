package io.github.mawisniewski1980.spring_gradle.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Person {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;
    private String lastname;
    private LocalDate birthday;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "address_id")
    private Address address;
    private String email;

    private String phone;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "card_id")
    private Card card;

    private String iban;

}
