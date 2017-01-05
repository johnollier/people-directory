package john.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
    private long id;
    private String name;
    private String favouriteFood;

    public Person() {
    }

    public Person(long id, String name, String favouriteFood) {
        this.id = id;
        this.name = name;
        this.favouriteFood = favouriteFood;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public String getFavouriteFood() {
        return favouriteFood;
    }
}
