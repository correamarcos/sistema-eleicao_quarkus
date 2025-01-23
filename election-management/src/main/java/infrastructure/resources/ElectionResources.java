package infrastructure.resources;

import api.ElectionApi;
import api.dto.out.Election;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("/api/elections")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ElectionResources {
    private final ElectionApi api;

    public ElectionResources(ElectionApi api) {
        this.api = api;
    }

    @POST
    @ResponseStatus(RestResponse.StatusCode.CREATED)
    @Transactional
    public void submit(){
        api.submit();
    }

    @GET
    public List<Election> list(){
        return api.findAll();
    }
}
