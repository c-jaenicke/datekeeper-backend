package datekeeper.datekeeperbackend.controllers;

import datekeeper.datekeeperbackend.modells.Event;
import datekeeper.datekeeperbackend.services.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    EventService service;

    Logger logger = LoggerFactory.getLogger(RestController.class);

    @GetMapping("/ping")
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public String ping() {
        logger.info("----- GET Request: ping");
        return "pong";
    }

    @GetMapping(value = "/events", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Event> getAllEvents() {
        logger.info("----- GET Request: events");
        return service.getAll();
    }

    @GetMapping(value = "/event/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Event getEvent(@PathVariable String id) {
        Long eventId;
        try {
            eventId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw e;
        }
        logger.info("----- GET Request: event/id with id: " + id);
        return service.get(eventId);
    }

    // TODO rename this mapping to new after "new" mapping has been removed
    @PostMapping("/json-new")
    @ResponseStatus(HttpStatus.CREATED)
    public Event createEvent(@RequestBody Event event) {
        logger.info("----- POST Request: json-new, body:" + event);
        return service.save(event);
    }

    // TODO remove this mapping
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Event createEvent(@RequestParam(name = "date", required = true) String date,
                             @RequestParam(name = "title", required = true) String title,
                             @RequestParam(name = "description", required = true) String description) {
        Event event = new Event(date, title, description);
        logger.info("----- POST Request: new, body:" + event);
        return service.save(event);
    }

    @DeleteMapping(value = "/event/{id}/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK, reason = "Event successfully deleted")
    public void delete(@PathVariable String id) {
        Long eventId = Long.parseLong(id);
        logger.info("----- DELETE Request: deleting event with it " + id);
        service.delete(eventId);
    }

    // TODO rework this method, does it make sense to set id for given event? or can event already contain the id?
    @PutMapping("/event/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id, @RequestBody Event event) {
        event.setId(id);
        logger.info("----- PUT Request: updated event with id " + id.toString() + ", with event " + event);
        service.save(event);
    }
}
