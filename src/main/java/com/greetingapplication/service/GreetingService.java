package com.greetingapplication.service;

import com.greetingapplication.model.Greeting;
import com.greetingapplication.model.User;
import com.greetingapplication.repository.GreetingRepository;
import com.greetingapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GreetingService implements IGreetingService {
    private static final String template = "Hello,%s!";
    private static final AtomicLong counter = new AtomicLong();

    @Autowired
    private GreetingRepository greetingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Greeting addGreeting(User user) {
        String message = String.format(template, (user.toString().isEmpty()) ? "Hello World" : user.toString());
        Greeting greeting = new Greeting(counter.incrementAndGet(), message);
        user.setId(counter.get());
        user.addMutlipleGreeting(greeting);
        userRepository.save(user);
        return greetingRepository.save(greeting);
    }

    @Override
    public Greeting getGreetingById(long id) {
        return greetingRepository.findById(id).get();
    }

    @Override
    public List<Greeting> getAllGreeting() {
        List<Greeting> greetingList = greetingRepository.findAll();
        return greetingList;
    }

    @Override
    public Greeting editByName(long id, String fName, String lName) {
        User user = new User();
        Optional<Greeting> greeting = greetingRepository.findById(id);
        if (greeting.isPresent()) {
            user.setFirstName(fName);
            user.setLastName(lName);
            greeting.get().setName(String.format(template, (user.toString().isEmpty())
                    ? "Hello World" : user.toString()));
            greetingRepository.save(greeting.get());
        }
        return null;
    }

    @Override
    public List<Greeting> deleteGreetingById(long id) {
        greetingRepository.deleteById(id);
        return greetingRepository.findAll();
    }
}
