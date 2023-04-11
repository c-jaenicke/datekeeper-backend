package datekeeper.datekeeperbackend.controllers;

import datekeeper.datekeeperbackend.services.CalendarServerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTController {
    @Autowired
    CalendarServerice calendarServerice;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    // TODO add new get get mapping for getting all events on a given date
    // should have params day, month, year

    // TODO add new post mapping for creating a new event
    // should have params day, month, year, title, description

    // TODO add new post mapping for updating an existing event
    // should have params day, month, year, eventID, title, description
}
