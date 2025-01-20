package com.event.Event.Event;


import com.event.Event.Event.dto.EventDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<String> createEvent(@RequestBody Event event){
        eventService.createEvent(event);
        return new ResponseEntity<>("Event Created Successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents(){
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id){
        EventDTO event=eventService.getEventById(id);
        if(event!=null){
            return new ResponseEntity<>(event,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(event,HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@PathVariable Long id,@RequestBody Event event){
        boolean isUpdated=eventService.updateById(id,event);
        if(isUpdated){
            return new ResponseEntity<>("Event Updated Successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Event Not Found",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        boolean deleted= eventService.deleteById(id);
        if(deleted){
            return new ResponseEntity<>("Event deleted Successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Event Not Found",HttpStatus.NOT_FOUND);
        }
    }

}
