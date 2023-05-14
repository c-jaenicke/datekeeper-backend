package datekeeper.datekeeperbackend.repositories;

import datekeeper.datekeeperbackend.modells.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * EventRepository
 * Extends CrudRepository<Event, Long>
 * Used in EventService
 */
@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
}