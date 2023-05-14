package datekeeper.datekeeperbackend.services;

import datekeeper.datekeeperbackend.modells.Event;
import datekeeper.datekeeperbackend.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * EventService
 * Methods for accessing database
 */
@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;

    /**
     * Save a new event and update an existing event
     * If a new event is inserted or an existing on updated, depends on if the id exists already.
     *
     * @param event
     * @return Event
     */
    public Event save(Event event) {
        try {
            return eventRepository.save(event);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Event to be inserted was null.");
        }
    }

    /**
     * Get event with given id
     *
     * @param id
     * @return Event
     */
    public Event get(Long id) {
        return eventRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    // TODO approach: get all events from db and check if date is equal to given date
    // This should be filtered by day
    // Possibly make a new method to filter for time frame

    /**
     * Get all events for a given date
     *
     * @param date
     * @return List<Event>
     */
    public List<Event> getEvents(Date date) {
        return null;
    }

    /**
     * Get all events in the database
     *
     * @return List<Event>
     */
    public List<Event> getAll() {
        Iterable<Event> iter = eventRepository.findAll();
        List<Event> events = new ArrayList<Event>();
        iter.forEach(events::add);
        return events;
    }

    /**
     * Delete the event with the given id
     *
     * @param id
     */
    public void delete(Long id) {
        try {
            eventRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Given id was null.");
        }
    }
}
