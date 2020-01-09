package com.greetingapplication.service;

import com.greetingapplication.model.Greeting;
import com.greetingapplication.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IGreetingService {
    Greeting addGreeting(User user);
    Greeting getGreetingById(long id);

    List<Greeting> getAllGreeting();

    Greeting editByName(long id, String fName,String lName);

    List<Greeting> deleteGreetingById(long id);
}
