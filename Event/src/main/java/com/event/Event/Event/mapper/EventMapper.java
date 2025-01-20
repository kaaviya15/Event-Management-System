package com.event.Event.Event.mapper;

import com.event.Event.Event.Event;
import com.event.Event.Event.dto.EventDTO;
import com.event.Event.Event.external.Payment;
import com.event.Event.Event.external.Ticket;

import java.util.List;

public class EventMapper {
    public static EventDTO mapToEventTicketDto(
            Event event,
            List<Ticket> tickets,
            List<Payment> payments){

        EventDTO eventDTO=new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setName(event.getName());
        eventDTO.setLocation(event.getLocation());
        eventDTO.setStartTime(event.getStartTime());
        eventDTO.setEndTime(event.getEndTime());
        eventDTO.setDescription(eventDTO.getDescription());
        eventDTO.setTickets(tickets);
        eventDTO.setPayments(payments);
        return eventDTO;
    }
}
