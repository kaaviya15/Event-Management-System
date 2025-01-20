package com.event.Event.Event;

import com.event.Event.Event.dto.EventDTO;

import java.util.List;

public interface EventService {
    void createEvent(Event event);
    List<EventDTO> getAllEvents();
    EventDTO getEventById(Long id);
    boolean updateById(Long id,Event event);
    boolean deleteById(Long id);
}
