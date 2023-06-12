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

@CrossOrigin(origins = "*")
@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    EventService service;

    Logger logger = LoggerFactory.getLogger(RestController.class);

    /**
     * ATTENTION ping returns code 418 IMATEAPOT
     *
     * @return
     */
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

    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public List<Event> createEvent(@RequestBody Event event) {
        logger.info("----- POST Request: json-new, body:" + event);
        service.save(event);
        return service.getAll();
    }

    @PostMapping(value = "/new-simple", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Event createEvent(@RequestParam(name = "date", required = true) String date,
                             @RequestParam(name = "title", required = true) String title,
                             @RequestParam(name = "description", required = true) String description) {
        Event event = new Event(date, title, description);
        logger.info("----- POST Request: new, body:" + event);
        return service.save(event);
    }

    @DeleteMapping(value = "/event/{id}/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Event> delete(@PathVariable String id) {
        Long eventId = Long.parseLong(id);
        logger.info("----- DELETE Request: deleting event with it " + id);
        service.delete(eventId);
        return service.getAll();
    }

    @PutMapping("/event/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id, @RequestBody Event event) {
        event.setId(id);
        logger.info("----- PUT Request: updated event with id " + id.toString() + ", with event " + event);
        service.save(event);
    }
}
