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
    private final List<String> cities = List.of("Rybnik", "Katowice", "Gliwice", "Gdansk", "Pila", "Gdynia", "Pszow", "Raciborz");
    private final List<String> visaCards = List.of("4260973341595986","4414779511560717","4038379188710376","4262718387348743");
    private final List<String> masterCards = List.of("5211526763750005","5307750525882634","5136889847749951","5490281237631330");
    private final List<String> ibans = List.of("PL94870229753244867073832012","PL42109024028735774336755527","PL39109024029119261438748935","PL61109024024141572275677956");

    @PostConstruct
    public void init() {
        Person person = new Person(
                EMPTY_ID,
                names.get(rand.nextInt(names.size())),
                lastnames.get(rand.nextInt(lastnames.size())),
                LocalDate.now().minusYears(25),
                new Address(
                        EMPTY_ID,
                        streets.get(rand.nextInt(streets.size())) + "  " + rand.nextInt(100),
                        "22-100",
                        cities.get(rand.nextInt(cities.size())),
                        "pomorskie"
                ),
                "fake@email.com",
                "888444666",
                new Card(
                        EMPTY_ID,
                        "VISA",
                        "2704",
                        visaCards.get(rand.nextInt(visaCards.size()))
                ),
                ibans.get(rand.nextInt(ibans.size()))
        );
        personRepository.save(person);
        log.info("PostConstruct: {}", person);
    }

    @Scheduled(fixedDelay = 15000)
    public void scheduled() {
        Person person = new Person(
                EMPTY_ID,
                names.get(rand.nextInt(names.size())),
                lastnames.get(rand.nextInt(lastnames.size())),
                LocalDate.now().minusYears(25),
                new Address(
                        EMPTY_ID,
                        streets.get(rand.nextInt(streets.size())) + "  " + rand.nextInt(100),
                        "40-100",
                        cities.get(rand.nextInt(cities.size())),
                        "slaskie"
                ),
                "empty@gmail.pl",
                "333222111",
                new Card(
                        EMPTY_ID,
                        "MASTERCARD",
                        "2501",
                        masterCards.get(rand.nextInt(masterCards.size()))
                ),
                ibans.get(rand.nextInt(ibans.size()))
        );
        personRepository.save(person);
        log.info("Scheduled: {}", person);
    }

}
