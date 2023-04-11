package datekeeper.datekeeperbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class controller {
    @GetMapping("/hello")
    public String HelloWorld() {
        return "Hello-World ";
    }

    @GetMapping("/world")
    public String world() {
        return "World Hello!";
    }
}

