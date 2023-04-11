package datekeeper.datekeeperbackend.services;

import datekeeper.datekeeperbackend.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarService {

    @Autowired
    EventRepository eventRepository;

    // TODO implement save, does this contain new and update? or should new be separate

    // TODO implement get

    // TODO implement get list

    // TODO add delete
}
