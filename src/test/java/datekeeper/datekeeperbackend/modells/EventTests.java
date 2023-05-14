package datekeeper.datekeeperbackend.modells;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventTests {
    private final String title = "A new event";
    private final String description = "A cool description";
    private final String dateString = "2023-04-21";

    // private static final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
    //        .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-d")).toFormatter();
    // private final LocalDate date = LocalDate.parse(dateString, formatter);

    @Test
    @DisplayName("Test if newly created event has correct values")
    public void eventValueTest() {
        Event event = new Event(dateString, title, description);

        assertEquals(title, event.getTitle());
        assertEquals(description, event.getDescription());
        assertEquals(dateString, event.getEventDate());
    }

    @Test
    @DisplayName("Test if event created without values can set values correctly")
    public void eventSetTest() {
        Event event = new Event();

        event.setEventDate(dateString);
        event.setTitle(title);
        event.setDescription(description);

        assertEquals(title, event.getTitle());
        assertEquals(description, event.getDescription());
        assertEquals(dateString, event.getEventDate());
    }

    @Test
    @DisplayName("Test toString method of Event class")
    public void eventToString() {
        Event event = new Event(dateString, title, description);
        event.setId(Long.parseLong("0"));
        String expected = String.format("Event:\n\tID: %d\n\ttitle: %s\n\tdescription: %s\n\tdate: %s", 0, title, description, dateString);

        assertEquals(expected, event.toString());
    }
}
