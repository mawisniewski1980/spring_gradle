package io.github.mawisniewski1980.spring_gradle.person;

import io.github.mawisniewski1980.spring_gradle.model.Person;
import io.github.mawisniewski1980.spring_gradle.model.PersonDto;
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
    public Person createPerson(@RequestBody PersonDto personDto) {
        return personService.createPerson(new Person(
                EMPTY_ID,
                personDto.getName(),
                personDto.getLastname(),
                personDto.getBirthday(),
                personDto.getAddress(),
                personDto.getEmail(),
                personDto.getPhone(),
                personDto.getCard(),
                personDto.getIban()
        ));
    }

    @PutMapping(path = "/persons/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody PersonDto personDto) {
        return personService.updatePerson(new Person(
                id,
                personDto.getName(),
                personDto.getLastname(),
                personDto.getBirthday(),
                personDto.getAddress(),
                personDto.getEmail(),
                personDto.getPhone(),
                personDto.getCard(),
                personDto.getIban()
        ));
    }

    @DeleteMapping(path = "/persons/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }
}
