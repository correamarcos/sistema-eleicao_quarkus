package infrastructure.resources;

import api.ElectionApi;
import api.dto.out.Election;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("/api/voting")
@Produces(MediaType.APPLICATION_JSON)
public class VotingResources {
    private ElectionApi api;

    public VotingResources(ElectionApi api) {
        this.api = api;
    }

    @GET
    public List<Election> elections(){
        return api.findAll();
    }

    @POST
    @Path("/election/{electionId}/candidates/{candidateId}")
    @ResponseStatus(RestResponse.StatusCode.ACCEPTED)
    @Transactional
    public void vote(@PathParam("electionId") String electionId, @PathParam("candidateId") String candidateId){
        api.vote(electionId, candidateId);
    }
}
