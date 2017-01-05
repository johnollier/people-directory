package john.resources;

import john.api.Person;
import john.core.EveryonesFavourites;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
public class PeopleResource {

    private final Map<Long,Person> peopleRepo;
    private final AtomicLong nextId = new AtomicLong(1l);
    private final EveryonesFavourites everyonesFavourites;

    public PeopleResource(Map<Long, Person> peopleRepo, EveryonesFavourites everyonesFavourites) {
        this.peopleRepo = peopleRepo;
        this.everyonesFavourites = everyonesFavourites;
    }

    @POST
    public Person createPerson(Person person) {
        long id = nextId.getAndIncrement();
        Person p = new Person(id, person.getName(), everyonesFavourites.getFood());
        peopleRepo.put(id, p);
        return p;
    }


}
