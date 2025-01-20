package com.ticket.Ticket.Ticket.Impl;

import com.ticket.Ticket.Ticket.Ticket;
import com.ticket.Ticket.Ticket.TicketRepo;
import com.ticket.Ticket.Ticket.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepo ticketRepo;

    public TicketServiceImpl(TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
    }


    @Override
    public boolean createTicket(Long eventId,Ticket ticket) {
        if(eventId!=null && ticket !=null ){
            ticket.setEventId(eventId);
            ticketRepo.save(ticket);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public List<Ticket> getAllTickets(Long eventId) {
        List<Ticket> ticketsList=ticketRepo.findByEventId(eventId);
        return ticketsList;
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepo.findById(id).orElse(null);
    }

    @Override
    public boolean updateById(Long id, Ticket ticket) {
        Optional<Ticket> toUpdate=ticketRepo.findById(id);
        if(toUpdate.isPresent()){
            Ticket updateticket=toUpdate.get();
            updateticket.setEventId(ticket.getEventId());
            updateticket.setCost(ticket.getCost());
            updateticket.setTicketType(ticket.getTicketType());
            updateticket.setDescription(ticket.getDescription());
            ticketRepo.save(updateticket);
            return true;

        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        if(ticketRepo.existsById(id)){
            ticketRepo.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }


}
