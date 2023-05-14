package datekeeper.datekeeperbackend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ApiController {
    @GetMapping("/hello")
    public String HelloWorld() {
        return "Hello-World ";
    }

    @GetMapping("/world")
    public String world() {
        return "World Hello!";
    }
}

