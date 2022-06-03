package io.github.mawisniewski1980.spring_gradle.person;

import io.github.mawisniewski1980.spring_gradle.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonController {

    public static final Long EMPTY_ID = null;
    private final PersonService personService;

    @GetMapping(path = "/persons")
    public List<Person> getPersons() {
        return personService.getPersons();
    }

    @GetMapping(path = "/persons/{id}")
    public Person getPerson(@PathVariable Long id) {
        return personService.getPerson(id);
    }

    @PostMapping(path = "/persons")
    public PersonDto createPerson(@RequestBody PersonDto personDto) {
        Person person = Person.builder()
                .id(EMPTY_ID)
                .name(personDto.getName())
                .lastname(personDto.getLastname())
                .birthday(personDto.getBirthday())
                .address(personDto.getAddress())
                .email(personDto.getEmail())
                .phone(personDto.getPhone())
                .card(personDto.getCard())
                .iban(personDto.getIban())
                .build();
        personService.createPerson(person);
        return personDto;
    }

    @PutMapping(path = "/persons/{id}")
    public PersonDto updatePerson(@PathVariable Long id, @RequestBody PersonDto personDto) {
        Person person = Person.builder()
                .id(id)
                .name(personDto.getName())
                .lastname(personDto.getLastname())
                .birthday(personDto.getBirthday())
                .address(personDto.getAddress())
                .email(personDto.getEmail())
                .phone(personDto.getPhone())
                .card(personDto.getCard())
                .iban(personDto.getIban())
                .build();
        personService.updatePerson(person);
        return personDto;
    }

    @DeleteMapping(path = "/persons/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }
}
