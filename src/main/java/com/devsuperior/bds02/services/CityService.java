package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.services.exceptions.DatabaseException;
import com.devsuperior.bds02.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    static final String CITY_NOT_FOUND = "City not found";
    static final String DATA_INTEGRITY_VIOLATION_EXCEPTION = "Data Integrity Violation";

    public List<CityDTO> findAll () {
        List<City> list = repository.findAll(Sort.by("name"));
        return list.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public CityDTO create (CityDTO cityDto) {
        City city = new City();
        city.setName(cityDto.getName());
        city = repository.save(city);
        return new CityDTO(city);
    }

    public void delete (Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(CITY_NOT_FOUND);
        } catch (DataIntegrityViolationException d) {
            throw new DatabaseException(DATA_INTEGRITY_VIOLATION_EXCEPTION);
        }
    }
}
