package io.github.mawisniewski1980.spring_gradle.person;

import io.github.mawisniewski1980.spring_gradle.model.Address;
import io.github.mawisniewski1980.spring_gradle.model.Card;
import io.github.mawisniewski1980.spring_gradle.model.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static io.github.mawisniewski1980.spring_gradle.person.PersonController.EMPTY_ID;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableScheduling
public class PersonSchedulledService {

    private final PersonRepository personRepository;
    private final Random rand = new Random();
    private final List<String> names = List.of("MAX", "DAX", "KYZIO", "DYZIO", "ZYZIO", "ARNOLD");
    private final List<String> lastnames = List.of("KWIATKOWSKI", "LIS", "DUZY", "BARANIN", "SLONCE", "WYSOKI");
    private final List<String> streets = List.of("Krolicza", "Sloneczna", "Koscielna", "Czekoladowa", "Czarna", "Uliczna");
    private final List<String> cities = List.of("Rybnik", "Katowice", "Gliwice", "Gdansk", "Pila", "Gdynia");
    private final List<String> visaCards = List.of("4260973341595986","4414779511560717","4038379188710376","4262718387348743");
    private final List<String> masterCards = List.of("5211526763750005","5307750525882634","5136889847749951","5490281237631330");
    private final List<String> ibans = List.of("PL94870229753244867073832012","PL42109024028735774336755527","PL39109024029119261438748935","PL61109024024141572275677956");

    @PostConstruct
    public void init() {
        Person person = Person.builder()
                .id(EMPTY_ID)
                .name(names.get(rand.nextInt(names.size())))
                .lastname(lastnames.get(rand.nextInt(lastnames.size())))
                .birthday(LocalDate.now().minusYears(25))
                .address(Address.builder()
                        .id(EMPTY_ID)
                        .street(streets.get(rand.nextInt(streets.size())) + "  " + rand.nextInt(100))
                        .zipCode("22-100")
                        .city(cities.get(rand.nextInt(cities.size())))
                        .state("pomorskie")
                        .build())
                .email("fake@email.com")
                .phone("888444666")
                .card(Card.builder()
                        .cardType("VISA")
                        .creditCardExpiry("2704")
                        .creditCardNumber(visaCards.get(rand.nextInt(visaCards.size())))
                        .build())
                .iban(ibans.get(rand.nextInt(ibans.size())))
                .build();
        personRepository.save(person);
        log.info("PostConstruct: {}", person);
    }

    @Scheduled(fixedDelay = 15000)
    public void scheduled() {
        Person person = Person.builder()
                .id(EMPTY_ID)
                .name(names.get(rand.nextInt(names.size())))
                .lastname(lastnames.get(rand.nextInt(lastnames.size())))
                .birthday(LocalDate.now().minusYears(rand.nextInt(100)))
                .address(Address.builder()
                        .id(EMPTY_ID)
                        .street(streets.get(rand.nextInt(streets.size())) + "  " + rand.nextInt(100))
                        .zipCode("44-200")
                        .city(cities.get(rand.nextInt(cities.size())))
                        .state("slaskie")
                        .build())
                .email("fake2@email.com")
                .phone("333222111")
                .card(Card.builder()
                        .cardType("MASTERCARD")
                        .creditCardExpiry("2501")
                        .creditCardNumber(masterCards.get(rand.nextInt(masterCards.size())))
                        .build())
                .iban(ibans.get(rand.nextInt(ibans.size())))
                .build();
        personRepository.save(person);
        log.info("Scheduled: {}", person);
    }

}
