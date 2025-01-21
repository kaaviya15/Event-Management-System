package com.event.Event.Event.Client;


import com.event.Event.Event.external.Ticket;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="ticket")
public interface TicketClient {

    @GetMapping("/ticket")
    List<Ticket> getTicket(@RequestParam("eventId") Long eventId);

}
