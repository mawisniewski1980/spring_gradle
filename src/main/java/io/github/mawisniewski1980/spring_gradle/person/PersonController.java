package io.github.mawisniewski1980.spring_gradle.person;

import io.github.mawisniewski1980.spring_gradle.model.Person;
import io.github.mawisniewski1980.spring_gradle.model.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping(path = "/persons")
    public ResponseEntity<List<Person>> getPersons() {
        return personService.getPersons();
    }

    @GetMapping(path = "/persons/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        return personService.getPerson(id);
    }

    @PostMapping(path = "/persons")
    public ResponseEntity<Person> createPerson(@RequestBody PersonDto personDto) {
        return personService.createPerson(personDto);
    }

    @PutMapping(path = "/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody PersonDto personDto) {
        return personService.updatePerson(id, personDto);
    }

    @DeleteMapping(path = "/persons/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }
}
