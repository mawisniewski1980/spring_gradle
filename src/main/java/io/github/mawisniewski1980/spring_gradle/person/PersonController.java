package io.github.mawisniewski1980.spring_gradle.person;

import io.github.mawisniewski1980.spring_gradle.model.AddressDto;
import io.github.mawisniewski1980.spring_gradle.model.CardDto;
import io.github.mawisniewski1980.spring_gradle.model.Person;
import io.github.mawisniewski1980.spring_gradle.model.PersonDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping(path = "/persons")
    public ResponseEntity<List<Person>> getPersons() {
        log.info("PersonController, endpoint: {}, timestamp: {}", "/persons", LocalDateTime.now());
        return ResponseEntity.ok(personService.getPersons());
    }

    @GetMapping(path = "/web/persons")
    public String getAllPersons(Model model) {
        log.info("PersonController, endpoint: {}, timestamp: {}", "/web/persons", LocalDateTime.now());
        model.addAttribute("persons", personService.getPersonsDto());
        return "persons";
    }

    @GetMapping(path = "/persons/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        log.info("PersonController, endpoint: {}, timestamp: {}", "/persons/{id}", LocalDateTime.now());
        return ResponseEntity.ok(personService.getPerson(id));
    }

    @PostMapping(path = "/persons")
    public ResponseEntity<Person> createPerson(@RequestBody PersonDto personDto) {
        log.info("PersonController, endpoint: {}, timestamp: {}", "/persons", LocalDateTime.now());
        return ResponseEntity.ok(personService.createPerson(personDto));
    }

    @GetMapping(path = {"/web/form", "/web/FORM"})
    public String form(Model model) {
        log.info("PersonController, endpoint: {}, timestamp: {}", "/web/form", LocalDateTime.now());
        //model.addAttribute("persons", personService.getPersonsDto());
        return "form";
    }

    @RequestMapping(value="/web/form", method = RequestMethod.POST)
    public String addPerson(Model model,
                            String name,
                            String lastname,
                            String birthday,

                            String street,
                            String zipCode,
                            String city,
                            String state,

                            String email,
                            String phone,

                            String creditCardNumber,
                            String creditCardExpiry,
                            String cardType,

                            String iban) {
        log.info("PersonController, endpoint: {}, timestamp: {}", "/web/form", LocalDateTime.now());
        personService.createPerson(PersonDto.builder()
                        .name(name)
                        .lastname(lastname)
                        .birthday(LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .address(AddressDto.builder()
                                .street(street)
                                .zipCode(zipCode)
                                .city(city)
                                .state(state)
                                .build())
                        .email(email)
                        .phone(phone)
                        .card(CardDto.builder()
                                .creditCardNumber(creditCardNumber)
                                .creditCardExpiry(creditCardExpiry)
                                .cardType(cardType)
                                .build())
                        .iban(iban)
                .build());
        model.addAttribute("complete", true);
        return "form";
    }

    @PutMapping(path = "/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody PersonDto personDto) {
        log.info("PersonController, endpoint: {}, timestamp: {}", "/persons/{id}", LocalDateTime.now());
        return ResponseEntity.ok(personService.updatePerson(id, personDto));
    }

    @DeleteMapping(path = "/persons/{id}")
    public void deletePerson(@PathVariable Long id) {
        log.info("PersonController, endpoint: {}, timestamp: {}", "/persons/{id}", LocalDateTime.now());
        personService.deletePerson(id);
    }
}
