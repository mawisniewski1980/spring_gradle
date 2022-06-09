package io.github.mawisniewski1980.spring_gradle.person;

import io.github.mawisniewski1980.spring_gradle.model.Person;
import io.github.mawisniewski1980.spring_gradle.model.PersonDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService {

    public static final Long EMPTY_ID = null;
    private final PersonRepository personRepository;

    public ResponseEntity<List<Person>> getPersons() {
        return new ResponseEntity<>(personRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Person> getPerson(Long id) {
        return new ResponseEntity<>(personRepository.findById(id).orElseThrow(), HttpStatus.OK);
    }

    public ResponseEntity<Person> createPerson(PersonDto personDto) {
        return new ResponseEntity<>(personRepository.save(new Person(
                EMPTY_ID,
                personDto.getName(),
                personDto.getLastname(),
                personDto.getBirthday(),
                personDto.getAddress(),
                personDto.getEmail(),
                personDto.getPhone(),
                personDto.getCard(),
                personDto.getIban()
        )),HttpStatus.OK);
    }

    public ResponseEntity<Person> updatePerson(Long id, PersonDto personDto) {
        return new ResponseEntity<>(personRepository.save(new Person(
                id,
                personDto.getName(),
                personDto.getLastname(),
                personDto.getBirthday(),
                personDto.getAddress(),
                personDto.getEmail(),
                personDto.getPhone(),
                personDto.getCard(),
                personDto.getIban()
        )), HttpStatus.OK);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}
