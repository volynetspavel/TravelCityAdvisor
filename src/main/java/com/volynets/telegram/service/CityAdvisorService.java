package com.volynets.telegram.service;


import com.volynets.telegram.dto.CityAdvisorDto;
import com.volynets.telegram.exception.ResourceNotFoundException;

import java.util.List;

/**
 * Service for performing operations with CityAdvisor-entity.
 */
public interface CityAdvisorService {

    CityAdvisorDto insert(CityAdvisorDto newCityAdvisorDto);

    CityAdvisorDto update(CityAdvisorDto updatedCityAdvisorDto) throws ResourceNotFoundException;

    void delete(Integer id) throws ResourceNotFoundException;

    List<CityAdvisorDto> findAll();

    CityAdvisorDto findCityByName(String name) throws ResourceNotFoundException;
}
