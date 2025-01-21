package com.event.Event.Event.Impl;

import com.event.Event.Event.Client.PaymentClient;
import com.event.Event.Event.Client.TicketClient;
import com.event.Event.Event.Event;
import com.event.Event.Event.EventRepo;
import com.event.Event.Event.EventService;
import com.event.Event.Event.dto.EventDTO;
import com.event.Event.Event.external.Payment;
import com.event.Event.Event.external.Ticket;
import com.event.Event.Event.mapper.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EventServiceImpl implements EventService {

    private EventRepo eventRepo;


    @Autowired
    RestTemplate restTemplate;
    private TicketClient ticketClient;
    private PaymentClient paymentClient;
    public EventServiceImpl(EventRepo eventRepo,TicketClient ticketClient,PaymentClient paymentClient) {
        this.eventRepo = eventRepo;
        this.ticketClient=ticketClient;
        this.paymentClient=paymentClient;
    }


    @Override
    public void createEvent(Event event) {
        eventRepo.save(event);
    }

    @Override
    public List<EventDTO> getAllEvents() {
        List<Event> eventList = eventRepo.findAll();
        List<EventDTO> eventDTOS=new ArrayList<>();
        return eventList.stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private EventDTO convertToDto(Event event) {
        Long eventId = event.getTicketId(); // Assuming ticketId is equivalent to eventId
        if (eventId == null || eventId <= 0) {
            throw new IllegalArgumentException("Invalid eventId for event: " + event.getId());
        }
        List<Ticket> tickets = ticketClient.getTicket(eventId); // Updated to expect a list
        List<Payment> payments = paymentClient.getPayment(event.getPaymentId());
        EventDTO eventDTO = EventMapper.mapToEventTicketDto(event, tickets, payments);
        return eventDTO;
    }


    @Override
    public EventDTO getEventById(Long id) {
        Event event=eventRepo.findById(id).orElse(null);
        return convertToDto(event);
    }

    @Override
    public boolean updateById(Long id, Event event) {
        Optional<Event> toUpdate = eventRepo.findById(id);
        if (toUpdate.isPresent()) {
            Event updateEvent = toUpdate.get();
            updateEvent.setName(event.getName());
            updateEvent.setDescription(event.getDescription());
            updateEvent.setStartTime(event.getStartTime());
            updateEvent.setEndTime(event.getEndTime());
            updateEvent.setLocation(event.getLocation());
            updateEvent.setTicketId(event.getTicketId());
            updateEvent.setPaymentId(event.getPaymentId());
            eventRepo.save(updateEvent);
            return true;

        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        if (eventRepo.existsById(id)) {
            eventRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
