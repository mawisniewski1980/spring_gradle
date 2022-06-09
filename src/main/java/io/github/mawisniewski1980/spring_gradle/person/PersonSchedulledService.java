package io.github.mawisniewski1980.spring_gradle.person;

import com.github.javafaker.Faker;
import io.github.mawisniewski1980.spring_gradle.model.Address;
import io.github.mawisniewski1980.spring_gradle.model.Card;
import io.github.mawisniewski1980.spring_gradle.model.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static io.github.mawisniewski1980.spring_gradle.person.PersonService.EMPTY_ID;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableScheduling
public class PersonSchedulledService {

    @Autowired
    Faker faker;

    private final PersonRepository personRepository;
    private final String[] cardTypes = {"VISA", "MASTERCARD", "AMERICAN EXPRESS"};

    @PostConstruct
    public void init() {
        personRepository.save(
                new Person(
                EMPTY_ID,
                faker.name().firstName(),
                faker.name().lastName(),
                LocalDate.now().minusYears(25),
                new Address(
                        EMPTY_ID,
                        faker.address().streetName() + " " + faker.address().buildingNumber(),
                        faker.address().zipCode(),
                        faker.address().city(),
                        faker.address().state()
                ),
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone().replace("-", "").replace(" ", ""),
                new Card(
                        EMPTY_ID,
                        faker.finance().creditCard().replace("-", ""),
                        String.valueOf(faker.number().numberBetween(1000, 9999)),
                        cardTypes[new Random().nextInt(cardTypes.length-1)]
                ),
                faker.finance().iban("PL")
                )
        );
        log.info("PostConstruct person added");
    }

    @Scheduled(fixedDelay = 15000)
    public void scheduled() {
        personRepository.save(
                new Person(
                        EMPTY_ID,
                        faker.name().firstName(),
                        faker.name().lastName(),
                        LocalDate.now().minusYears(39),
                        new Address(
                                EMPTY_ID,
                                faker.address().streetName() + " " + faker.address().buildingNumber(),
                                faker.address().zipCode(),
                                faker.address().city(),
                                faker.address().state()
                        ),
                        faker.internet().emailAddress(),
                        faker.phoneNumber().cellPhone().replace("-", "").replace(" ", ""),
                        new Card(
                                EMPTY_ID,
                                faker.finance().creditCard().replace("-", ""),
                                String.valueOf(faker.number().numberBetween(1000, 9999)),
                                cardTypes[new Random().nextInt(cardTypes.length-1)]
                        ),
                        faker.finance().iban("PL")
                )
        );
        log.info("Scheduled person added");
    }


}
