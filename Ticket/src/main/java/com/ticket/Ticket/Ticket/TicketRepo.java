package com.ticket.Ticket.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepo extends JpaRepository<Ticket,Long> {

    List<Ticket> findByEventId(Long eventId);
}
