package com.ticket.Ticket.Ticket;


import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<String> createTicket(@RequestParam Long eventId,@RequestBody Ticket ticket) {

        boolean created = ticketService.createTicket(eventId, ticket);
        if (created) {
            return new ResponseEntity<>("Ticket created Successfully", HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets(@RequestParam Long eventId){
        return ResponseEntity.ok(ticketService.getAllTickets(eventId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id){
        Ticket ticket=ticketService.getTicketById(id);
        if(ticket!=null){
            return new ResponseEntity<>(ticket,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(ticket,HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@PathVariable Long id,@RequestBody Ticket ticket){
        boolean updated= ticketService.updateById(id,ticket);
        if(updated){
            return new ResponseEntity<>("Ticket Updated Successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Ticket Not Found",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        boolean deleted= ticketService.deleteById(id);
        if(deleted){
            return new ResponseEntity<>("Ticket Deleted Successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Ticket Not Found",HttpStatus.NOT_FOUND);
        }
    }
}
