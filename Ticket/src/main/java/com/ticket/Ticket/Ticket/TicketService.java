package com.ticket.Ticket.Ticket;

import java.util.List;

public interface TicketService {

    boolean createTicket(Long eventId,Ticket ticket);
    List<Ticket> getAllTickets(Long eventId);
    Ticket getTicketById(Long id);
    boolean updateById(Long id,Ticket ticket);
    boolean deleteById(Long id);
}
