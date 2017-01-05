package john.resources;

import io.dropwizard.jersey.params.LongParam;
import john.api.Person;
import john.core.EveryonesFavourites;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/people/{personId}")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    private final Map<Long,Person> peopleRepo;
    private final EveryonesFavourites everyonesFavourites;

    public PersonResource(Map<Long, Person> peopleRepo, EveryonesFavourites everyonesFavourites) {
        this.peopleRepo = peopleRepo;
        this.everyonesFavourites = everyonesFavourites;
    }

    @GET
    public Person read(@PathParam("personId") LongParam id) {
        return peopleRepo.get(id.get());
    }

    @DELETE
    public Response delete(@PathParam("personId") LongParam id, @QueryParam("favourite") String favourite) {
        peopleRepo.remove(id.get());
        everyonesFavourites.setFood(favourite);
        return Response.ok("deleted").build();
    }
}
