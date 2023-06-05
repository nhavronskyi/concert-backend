package com.concert.controllers;

import com.concert.entities.Event;
import com.concert.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {
    private EventService eventService;

    @GetMapping
    public List<Event> readAllEvents() {
        return eventService.readAll();
    }

    @GetMapping("/pagination")
    public Page<Event> readPaginatedEvents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "9") int size) {
        return eventService.readAll(page, size);
    }

    @GetMapping("/location")
    public List<Event> getEventsByLocation(@RequestParam String location) {
        return eventService.getEventsByLocation(location);
    }

    @GetMapping("/pagination/location")
    public Page<Event> readPaginatedByLocation(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "9") int size, @RequestParam String location) {
        return eventService.readAllByLocation(page, size, location);
    }

    @GetMapping("/{id}")
    public Event readEvent(@PathVariable Long id) {
        return eventService.read(id);
    }

    @PostMapping
    public void createEvent(@RequestBody Event event) {
        eventService.create(event);
    }

    @PostMapping(value = "/image/{title}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadImage(@PathVariable String title, @RequestPart MultipartFile file) {
        eventService.uploadEventImage(title, file);
    }

    @GetMapping(value = "/image/{title}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable String title) {
        return eventService.getEventImage(title);
    }

    @PutMapping("/{id}")
    public void updateEvent(@PathVariable Long id, @RequestBody Event event) {
        eventService.update(id, event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.delete(id);
    }
}
