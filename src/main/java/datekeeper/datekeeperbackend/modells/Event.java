package datekeeper.datekeeperbackend.modells;

import java.time.LocalDate;

public class Event {
    // Simple structure representing an event
    String title = "";
    String description = "";
    LocalDate eventDate;

    public Event(int day, int month, int year, String title, String description) {
        this.title = title;
        this.description = description;
        // parse date from  string, see https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html#parse-java.lang.CharSequence-
        this.eventDate = LocalDate.parse(String.format("%s-%s-%s", year, month, day));
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

    public void setEventDate(int day, int month, int year) {
        this.eventDate = LocalDate.parse(String.format("%s-%s-%s", year, month, day));
    }
}
