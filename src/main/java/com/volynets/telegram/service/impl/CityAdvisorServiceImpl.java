package com.volynets.telegram.service.impl;


import com.volynets.telegram.dao.CityAdvisorDao;
import com.volynets.telegram.dto.CityAdvisorDto;
import com.volynets.telegram.exception.ResourceAlreadyExistException;
import com.volynets.telegram.exception.ResourceNotFoundException;
import com.volynets.telegram.mapper.Mapper;
import com.volynets.telegram.model.CityAdvisor;
import com.volynets.telegram.service.CityAdvisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This class is an implementation of CityAdvisorService.
 */
@Service
public class CityAdvisorServiceImpl implements CityAdvisorService {

    private final CityAdvisorDao cityAdvisorDao;
    private final Mapper cityAdvisorMapper;

    @Autowired
    public CityAdvisorServiceImpl(CityAdvisorDao cityAdvisorDao, Mapper cityAdvisormapper) {
        this.cityAdvisorDao = cityAdvisorDao;
        this.cityAdvisorMapper = cityAdvisormapper;
    }

    @Transactional
    @Override
    public CityAdvisorDto insert(CityAdvisorDto newCityAdvisorDto) {
        String name = newCityAdvisorDto.getName();

        if (!isResourceAlreadyExist(name)) {
            throw new ResourceAlreadyExistException("City with name " + name + " has already existed.");
        }

        CityAdvisor cityAdvisor = cityAdvisorDao.save(cityAdvisorMapper.toEntity(newCityAdvisorDto));
        return cityAdvisorMapper.toDto(cityAdvisor);
    }

    @Transactional
    @Override
    public CityAdvisorDto update(CityAdvisorDto updatedCityAdvisorDto) throws ResourceNotFoundException {
        Integer id = updatedCityAdvisorDto.getId();
        String name = updatedCityAdvisorDto.getName();
        String advice = updatedCityAdvisorDto.getAdvice();

        CityAdvisor cityAdvisor = checkCityAdvisorOnNullById(id);

        if (name != null) {
            cityAdvisor.setName(name);
        }
        if (advice != null) {
            cityAdvisor.setAdvice(advice);
        }
        return cityAdvisorMapper.toDto(cityAdvisor);
    }

    @Transactional
    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        CityAdvisor cityAdvisor = checkCityAdvisorOnNullById(id);
        cityAdvisorDao.delete(cityAdvisor);
    }

    private CityAdvisor checkCityAdvisorOnNullById(Integer id) throws ResourceNotFoundException {
        return cityAdvisorDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Requested resource not found."));
    }

    private boolean isResourceAlreadyExist(String name) {
        return cityAdvisorDao.findCityAdvisorByName(name).isPresent();
    }

    @Override
    public List<CityAdvisorDto> findAll() {
        return cityAdvisorMapper.toDtoList(cityAdvisorDao.findAll());
    }

    @Override
    public CityAdvisorDto findCityByName(String name) throws ResourceNotFoundException {
        CityAdvisor cityAdvisor = cityAdvisorDao.findCityAdvisorByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Requested resource not found."));
        return cityAdvisorMapper.toDto(cityAdvisor);
    }
}
