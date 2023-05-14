package datekeeper.datekeeperbackend.modells;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * Event
 * Represents a single event in a calendar.
 * Has id, title, description and date.
 */
@Entity
public class Event {
    @Id
    @GeneratedValue
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String title = "";
    private String description = "";
    private LocalDate eventDate;

    private static final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-d")).toFormatter();

    public Event() {
    }

    public Event(String date, String title, String description) {
        this.title = title;
        this.description = description;
        // parse date from  string, see https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html#parse-java.lang.CharSequence-
        // this.eventDate = LocalDate.parse(String.format("%d-%d-%d", year, month, day), formatter);
        this.eventDate = LocalDate.parse(date, formatter);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventDate() {
        return eventDate.toString();
    }

    public void setEventDate(String date) {
        this.eventDate = LocalDate.parse(date, formatter);
    }

    public String toString() {
        return String.format("Event:\n\tID: %d\n\ttitle: %s\n\tdescription: %s\n\tdate: %s", this.id, this.title, this.description, this.eventDate.toString());
    }
}
