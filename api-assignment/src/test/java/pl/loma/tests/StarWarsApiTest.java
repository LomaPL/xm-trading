package pl.loma.tests;

import io.qameta.allure.Owner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;
import pl.loma.core.Endpoint;
import pl.loma.core.TestCase;
import pl.loma.entities.Film;
import pl.loma.entities.PeopleSearch;
import pl.loma.entities.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Owner("Kamil Lominski")
public class StarWarsApiTest extends TestCase {

    @Test
    public void useCaseOneTest() {
        // 1️⃣
        // @formatter:off
        var vader = given(spec)
            .param("search", "vader")
        .when()
            .get(Endpoint.PEOPLE.getResource())
        .then()
            .assertThat()
            .statusCode(200)
            .extract()
            .as(PeopleSearch.class)
            .results()
            .getFirst();
        // @formatter:on

        // 2️⃣
        var films = new ArrayList<Film>();
        var filmUrls = vader.films();
        for (var filmUrl : filmUrls) {
            log.debug("Retrieving film: {}", filmUrl);
            // @formatter:off
            var film = given(spec)
                .expect()
                .statusCode(200)
            .when()
                .get(filmUrl)
            .then()
                .extract()
                .as(Film.class);
            // @formatter:on
            films.add(film);
        }
        var filmWithMostPlanets = films.stream()
                .max(Comparator.comparingInt(film -> film.planets().size()))
                .orElse(null);
        var filmWithMostPlanetsTitle = Objects.requireNonNull(filmWithMostPlanets).title();
        assertThat(filmWithMostPlanetsTitle).isEqualTo("Revenge of the Sith");

        // 3️⃣
        boolean wasVadersStarshipInFilm = false;
        for (var starship : filmWithMostPlanets.starships()) {
            wasVadersStarshipInFilm = vader.starships().contains(starship);
        }
        if (wasVadersStarshipInFilm) {
            log.info("Vader's starship was in the film: {}", filmWithMostPlanetsTitle);
        } else {
            log.info("Vader's starship was not in the film: {}", filmWithMostPlanetsTitle);
        }
        assertThat(wasVadersStarshipInFilm).isFalse();

        // 4️⃣
        var peopleResponse = fetchPeople(BASE_URL + Endpoint.PEOPLE.getResource());
        var people = new ArrayList<>(peopleResponse.results());

        var nextPageUrl = peopleResponse.next();
        while (nextPageUrl != null) {
            var nextPage = fetchPeople(nextPageUrl);
            nextPageUrl = nextPage.next();
            people.addAll(nextPage.results());
        }
        var oldestPerson = findOldestPerson(people).name();
        log.info("The oldest character in a Star Wars film is: {}", oldestPerson);
        assertThat(people).hasSize(82);
        assertThat(oldestPerson).isEqualTo("Yoda");
    }

    private PeopleSearch fetchPeople(String url) {
        // @formatter:off
        log.debug("Request url: {}", url);
        return given(spec)
            .get(url)
        .then()
            .assertThat()
            .statusCode(200)
            .extract()
            .as(PeopleSearch.class);
        // @formatter:on
    }

    private Person findOldestPerson(List<Person> people) {
        Predicate<Person> afterBattlePredicate = person -> !person.birth_year().contains("ABY");
        Predicate<Person> unknown = person -> !person.birth_year().equalsIgnoreCase("unknown");
        return people.stream()
                .filter(afterBattlePredicate.and(unknown))
                .max(Comparator.comparingDouble(person -> {
                    String year = StringUtils.substringBefore(person.birth_year(), "BBY");
                    return Double.parseDouble(year);
                }))
                .orElse(null);
    }

}
