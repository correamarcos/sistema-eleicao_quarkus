package domain;

import api.dto.in.Candidate;
import infrastructure.resources.CandidateResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import jakarta.ws.rs.core.MediaType;
import org.instancio.Instancio;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusIntegrationTest
@TestHTTPEndpoint(CandidateResource.class)
public class CandidateResourceIT {
    @Test
    void create(){
        var in = Instancio.create(Candidate.class);

        given().contentType(MediaType.APPLICATION_JSON).body(in)
                .when().post()
                .then().statusCode(RestResponse.StatusCode.CREATED);
    }

    @Test
    void update(){
        String id = UUID.randomUUID().toString();
        var in = Instancio.of(Candidate.class).set(field("id"), id).create();

        var updateBody = Instancio.of(Candidate.class)
                .set(field("id"), Optional.empty())
                .create();

        var response = given().contentType(MediaType.APPLICATION_JSON).body(in)
                .when().put("/" + id)
                .then().statusCode(RestResponse.StatusCode.OK)
                .extract().as(api.dto.out.Candidate.class);

        var response1 = given().contentType(MediaType.APPLICATION_JSON).body(updateBody)
                .when().put("/" + id)
                .then().statusCode(RestResponse.StatusCode.OK)
                .extract().as(api.dto.out.Candidate.class);

        assertEquals(response.id(), id);
        assertEquals(response1.id(), id);
    }
}
