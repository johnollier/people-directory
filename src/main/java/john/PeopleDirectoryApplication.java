package john;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import john.api.Person;
import john.core.EveryonesFavourites;
import john.health.SimpleHealthCheck;
import john.resources.PeopleResource;
import john.resources.PersonResource;

import java.util.HashMap;
import java.util.Map;

public class PeopleDirectoryApplication extends Application<PeopleDirectoryConfiguration> {

    public static void main(final String[] args) throws Exception {
        new PeopleDirectoryApplication().run(args);
    }

    @Override
    public String getName() {
        return "people-directory";
    }

    @Override
    public void initialize(final Bootstrap<PeopleDirectoryConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final PeopleDirectoryConfiguration configuration,
                    final Environment environment) {

        final SimpleHealthCheck healthCheck = new SimpleHealthCheck();
        environment.healthChecks().register("simple", healthCheck);

        EveryonesFavourites everyonesFavourites = new EveryonesFavourites(configuration.getFood());

        Map<Long,Person> peopleRepo = new HashMap<>();

        final PersonResource personResource = new PersonResource(peopleRepo, everyonesFavourites);
        final PeopleResource peopleResource = new PeopleResource(peopleRepo, everyonesFavourites);
        environment.jersey().register(personResource);
        environment.jersey().register(peopleResource);
    }

}
