package pl.loma.tests;

import org.testng.annotations.Test;
import pl.loma.core.Endpoint;
import pl.loma.core.TestCase;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PeopleContractTest extends TestCase {

    @Test
    public void peopleEndpointSchemaTest() {
        // @formatter:off
        given(spec)
            .get(Endpoint.PEOPLE.getResource())
        .then()
            .assertThat()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("schemas/people-schema.json"));
        // @formatter:on
    }

}
