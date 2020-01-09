package com.greetingapplication.controller;

import com.greetingapplication.model.Greeting;
import com.greetingapplication.model.User;
import com.greetingapplication.service.IGreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/greetings")
public class GreetingController {

    @Autowired
    private IGreetingService greetingService;

    @GetMapping()
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "WORLD") String name,
                             @RequestParam(value = "lname", defaultValue = "WORLD") String lName) {
        User user = new User();
        user.setFirstName(name);
        user.setLastName(lName);
        return greetingService.addGreeting(user);
    }

    @GetMapping("/getById")
    public Greeting greeting(@RequestParam(value = "id") long id) {
        return greetingService.getGreetingById(id);
    }

    @GetMapping("/getAllGreeting")
    public List<Greeting> getAllGreeting() {
        return greetingService.getAllGreeting();
    }

    @PutMapping("/putGreeting/{id}")
    public Greeting putGreeting(@PathVariable long id,
                                @RequestParam(value = "fname", defaultValue = "WORLD") String fName,
                                @RequestParam(value = "lname", defaultValue = "WORLD") String lName) {
        return greetingService.editByName(id, fName, lName);
    }

    @DeleteMapping("/deleteById/{id}")
    public List<Greeting> deleteGreeting(@PathVariable long id) {
        List<Greeting> greetings = greetingService.deleteGreetingById(id);
        return greetings;
    }
}
