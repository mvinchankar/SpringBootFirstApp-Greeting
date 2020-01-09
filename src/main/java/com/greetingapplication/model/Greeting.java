package com.greetingapplication.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Greeting {

    public void setName(String name) {
        this.name = name;
    }

    public Greeting() {
    }

    @ManyToOne
    @JoinColumn
    private User user;

    @Id
    private long id;
    private String name;

    public Greeting(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

