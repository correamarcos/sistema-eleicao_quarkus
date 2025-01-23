package infrastructure.resources;

import api.CandidateApi;
import api.dto.in.Candidate;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("/api/candidates")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CandidateResource {
    private final CandidateApi candidateApi;

    public CandidateResource(CandidateApi candidateApi) {
        this.candidateApi = candidateApi;
    }

    @GET
    public List<api.dto.out.Candidate> list(){
        return candidateApi.list();
    }

    @POST
    @ResponseStatus(RestResponse.StatusCode.CREATED)
    @Transactional
    public void create(Candidate request){
        candidateApi.create(request);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") String id, Candidate request){
        api.dto.out.Candidate updatedCandidate = candidateApi.update(id, request);
        if (updatedCandidate == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updatedCandidate).build();
    }
}
