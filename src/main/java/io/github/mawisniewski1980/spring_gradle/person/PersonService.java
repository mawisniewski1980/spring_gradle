package io.github.mawisniewski1980.spring_gradle.person;

import io.github.mawisniewski1980.spring_gradle.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService {

    public static final Long EMPTY_ID = null;
    private final PersonRepository personRepository;

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public List<PersonDto> getPersonsDto() {
        return personRepository
                .findAll()
                .stream()
                .map(person ->
                    PersonDto
                            .builder()
                            .name(person.getName())
                            .lastname(person.getLastname())
                            .birthday(person.getBirthday())
                            .address(AddressDto
                                    .builder()
                                    .street(person.getAddress().getStreet())
                                    .zipCode(person.getAddress().getZipCode())
                                    .city(person.getAddress().getCity())
                                    .state(person.getAddress().getState())
                                    .build())
                            .email(person.getEmail())
                            .phone(person.getPhone())
                            .card(CardDto.builder()
                                    .creditCardExpiry(person.getCard().getCreditCardExpiry())
                                    .creditCardNumber(person.getCard().getCreditCardNumber())
                                    .cardType(person.getCard().getCardType())
                                    .build())
                            .iban(person.getIban())
                            .build()
                )
                .collect(Collectors.toList());
    }

    public Person getPerson(Long id) {
        return personRepository.findById(id).orElseThrow();
    }

    public Person createPerson(PersonDto personDto) {
        return personRepository.save(new Person(
                EMPTY_ID,
                personDto.getName(),
                personDto.getLastname(),
                personDto.getBirthday(),
                new Address(
                        EMPTY_ID,
                        personDto.getAddress().getStreet(),
                        personDto.getAddress().getZipCode(),
                        personDto.getAddress().getCity(),
                        personDto.getAddress().getState()
                ),
                personDto.getEmail(),
                personDto.getPhone(),
                new Card(EMPTY_ID,
                        personDto.getCard().getCreditCardNumber(),
                        personDto.getCard().getCreditCardExpiry(),
                        personDto.getCard().getCardType()),
                personDto.getIban()
        ));
    }

    public Person updatePerson(Long id, PersonDto personDto) {
        return personRepository.save(new Person(
                id,
                personDto.getName(),
                personDto.getLastname(),
                personDto.getBirthday(),
                new Address(
                        id,
                        personDto.getAddress().getStreet(),
                        personDto.getAddress().getZipCode(),
                        personDto.getAddress().getCity(),
                        personDto.getAddress().getState()
                ),
                personDto.getEmail(),
                personDto.getPhone(),
                new Card(id,
                        personDto.getCard().getCreditCardNumber(),
                        personDto.getCard().getCreditCardExpiry(),
                        personDto.getCard().getCardType()),
                personDto.getIban()
        ));
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}
