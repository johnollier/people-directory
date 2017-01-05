package john;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;

import javax.validation.constraints.*;

public class PeopleDirectoryConfiguration extends Configuration {
    @NotEmpty
    private String food;

    @JsonProperty
    public String getFood() {
        return food;
    }

    @JsonProperty
    public void setFood(String food) {
        this.food = food;
    }
}
