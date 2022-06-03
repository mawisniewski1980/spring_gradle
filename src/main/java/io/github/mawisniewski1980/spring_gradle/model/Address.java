package io.github.mawisniewski1980.spring_gradle.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Address {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String street;
    private String zipCode;
    private String city;
    private String state;

}
