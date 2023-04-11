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

    // TODO add new get get mapping for getting all events on a given date
    // should have params day, month, year

    // TODO add new post mapping for creating a new event
    // should have params day, month, year, title, description

    // TODO add new post mapping for updating an existing event
    // should have params day, month, year, eventID, title, description
}

