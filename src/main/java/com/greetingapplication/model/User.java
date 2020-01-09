package com.greetingapplication.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Component
@Entity
public class User {
    @Id
    private long id;
    private String firstName;
    private String lastName;

    public User() {
    }

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Greeting> greeting;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return firstName + " " +
                lastName;
    }

    public void addMutlipleGreeting(Greeting... greetings) {
        this.greeting = Stream.of(greetings).collect(Collectors.toSet());
        this.greeting.forEach(x -> x.setUser(this));
    }
}
