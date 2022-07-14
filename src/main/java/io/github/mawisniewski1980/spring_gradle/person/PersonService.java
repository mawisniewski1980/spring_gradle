package io.github.mawisniewski1980.spring_gradle.person;

import io.github.mawisniewski1980.spring_gradle.model.Person;
import io.github.mawisniewski1980.spring_gradle.model.PersonDto;
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
                            .address(person.getAddress())
                            .email(person.getEmail())
                            .phone(person.getPhone())
                            .card(person.getCard())
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
                personDto.getAddress(),
                personDto.getEmail(),
                personDto.getPhone(),
                personDto.getCard(),
                personDto.getIban()
        ));
    }

    public Person updatePerson(Long id, PersonDto personDto) {
        return personRepository.save(new Person(
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

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}
