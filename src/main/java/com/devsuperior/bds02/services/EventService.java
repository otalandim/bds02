package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    static final String EVENT_NOT_FOUND = "Event not found";

    @Transactional
    public EventDTO update (Long id, EventDTO eventDto) {
        try {
            Event event = repository.getOne(id);
            event.setName(eventDto.getName());
            event.setDate(eventDto.getDate());
            event.setUrl(eventDto.getUrl());
            event.setCity(new City(eventDto.getCityId(), null));
            event = repository.save(event);
            return new EventDTO(event);
        } catch (EntityNotFoundException e) {
         throw new NotFoundException(EVENT_NOT_FOUND);
        }
    }
}
