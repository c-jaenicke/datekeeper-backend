package datekeeper.datekeeperbackend.repositories;

import datekeeper.datekeeperbackend.modells.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * EventRepository
 * Extends CrudRepository<Event, Long>
 * Used in EventService
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}