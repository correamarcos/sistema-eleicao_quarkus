package infrastructure.resources;

import api.CandidateApi;
import api.dto.in.Candidate;
import io.quarkus.test.InjectMock;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.instancio.Instancio;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
@TestHTTPEndpoint(CandidateResource.class)
class CandidateResourceTest {
    @InjectMock
    CandidateApi candidateApi;

    @Test
    void create(){
        Candidate in = Instancio.create(Candidate.class);

        given().contentType(MediaType.APPLICATION_JSON).body(in)
                .when().post()
                .then().statusCode(RestResponse.StatusCode.CREATED);

        verify(candidateApi).create(in);
    }

    @Test
    void list(){
        List<api.dto.out.Candidate> out = Instancio.stream(api.dto.out.Candidate.class).limit(5).toList();

        when(candidateApi.list()).thenReturn(out);

        var response = given()
                .when().get()
                .then().statusCode(RestResponse.StatusCode.OK).extract().as(api.dto.out.Candidate[].class);

        verify(candidateApi).list();

        assertEquals(out, Arrays.stream(response).toList());
    }

//    @Test
//    void update() {
//        CandidateRequest in = Instancio.create(CandidateRequest.class);
//        CandidateResponse out = given().contentType(MediaType.APPLICATION_JSON).body(in)
//                .when().put("/" + in.id())
//                .then().statusCode(RestResponse.StatusCode.OK)
//                .extract().as(CandidateResponse.class);
//
//        assertEquals(String.valueOf(in.id()), out.id());
//
//        CandidateRequest inUpdated = Instancio.of(CandidateRequest.class)
//                .set(field("id"), null)
//                .create();
//
//        CandidateResponse updatedCandidate = given().contentType(MediaType.APPLICATION_JSON).body(inUpdated)
//                .when().put("/" + in.id())
//                .then().statusCode(RestResponse.StatusCode.OK)
//                .extract().as(CandidateResponse.class);
//
//        assertNotNull(updatedCandidate,);
//        assertEquals(String.valueOf(in.id()), updatedCandidate.id());
//        assertEquals(inUpdated.givenName(), updatedCandidate.givenName());
//        assertEquals(inUpdated.familyName(), updatedCandidate.familyName());
//    }

}