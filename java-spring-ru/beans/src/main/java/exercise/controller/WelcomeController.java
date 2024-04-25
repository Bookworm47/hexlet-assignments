package exercise.controller;

import exercise.daytime.Daytime;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

// BEGIN
@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    Daytime daytime;

    public WelcomeController(Daytime daytime) {
        this.daytime = daytime;
    }

    @GetMapping
    public String hello() {
        return String.format("It is %s now! Welcome to Spring!%n", daytime.getName());
    }
}
// END
