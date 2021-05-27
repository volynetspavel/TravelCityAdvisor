package com.volynets.telegram.service;

import com.volynets.telegram.dao.CityAdvisorDao;
import com.volynets.telegram.dto.CityAdvisorDto;
import com.volynets.telegram.exception.ResourceAlreadyExistException;
import com.volynets.telegram.exception.ResourceNotFoundException;
import com.volynets.telegram.mapper.Mapper;
import com.volynets.telegram.model.CityAdvisor;
import com.volynets.telegram.service.impl.CityAdvisorServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Class for testing methods from service layer for cityAdvisor.
 */
@ExtendWith(MockitoExtension.class)
class CityAdvisorServiceTest {

    @InjectMocks
    private CityAdvisorServiceImpl cityAdvisorService;
    @Mock
    private CityAdvisorDao cityAdvisorDao;
    @Mock
    private Mapper cityAdvisorMapper;

    @DisplayName("Testing method findById() on positive result")
    @Test
    void findCityByNameSuccessTest() throws ResourceNotFoundException {
        int id = 3;
        String name = "Mexico";
        String advice = "Pack a copy of your passport and credit cards.";

        Optional<CityAdvisor> expected = createCityAdvisor(id, name, advice);
        CityAdvisorDto expectedDto = createCityAdvisorDto(id, name, advice);

        when(cityAdvisorDao.findCityAdvisorByName(name)).thenReturn(expected);
        when(cityAdvisorMapper.toDto(expected.get())).thenReturn(expectedDto);

        CityAdvisorDto actualDto = cityAdvisorService.findCityByName(name);

        assertEquals(expectedDto, actualDto);
    }

    @DisplayName("Testing method findById() on exception")
    @Test
    void findCityByNameThrowsExceptionTest() {
        String name = "Mexico";

        when(cityAdvisorDao.findCityAdvisorByName(name)).thenReturn(null);

        assertThrows(ResourceNotFoundException.class,
                () -> cityAdvisorService.findCityByName(name));
    }

    @DisplayName("Testing method delete() by id of cityAdvisor on positive result")
    @Test
    void deleteByIdSuccessTest() throws ResourceNotFoundException {
        int id = 3;
        String name = "Mexico";
        String advice = "Pack a copy of your passport and credit cards.";

        Optional<CityAdvisor> cityAdvisor = createCityAdvisor(id, name, advice);

        when(cityAdvisorDao.findById(id)).thenReturn(cityAdvisor);
        cityAdvisorService.delete(id);
        verify(cityAdvisorDao, times(1)).delete(cityAdvisor.get());
    }

    @DisplayName("Testing method delete() by id of cityAdvisor on negative result")
    @Test
    void deleteByIdThrowsExceptionTest() {
        int id = 5;
        String name = "Mexico";

        when(cityAdvisorDao.findById(id)).thenReturn(null);

        assertThrows(ResourceNotFoundException.class,
                () -> cityAdvisorService.findCityByName(name));
    }

    @DisplayName("Testing method insert() on positive result")
    @Test
    void insertSuccessTest() {
        int id = 0;
        String name = "Mexico";
        String advice = "Pack a copy of your passport and credit cards.";

        Optional<CityAdvisor> newCityAdvisor = createCityAdvisor(id, name, advice);
        CityAdvisorDto newCityAdvisorDto = createCityAdvisorDto(id, name, advice);

        when(cityAdvisorDao.findCityAdvisorByName(name)).thenReturn(newCityAdvisor);

        when(cityAdvisorMapper.toEntity(newCityAdvisorDto)).thenReturn(newCityAdvisor.get());
        when(cityAdvisorDao.save(newCityAdvisor.get())).thenReturn(newCityAdvisor.get());
        when(cityAdvisorMapper.toDto(newCityAdvisor.get())).thenReturn(newCityAdvisorDto);

        CityAdvisorDto cityAdvisorDtoAfterInsert = cityAdvisorService.insert(newCityAdvisorDto);
        assertEquals(newCityAdvisorDto, cityAdvisorDtoAfterInsert);
    }

    @DisplayName("Testing method insert() on negative result")
    @Test
    void insertThrowsExceptionTest() {
        int id = 3;
        String name = "Mexico";
        String advice = "Pack a copy of your passport and credit cards.";

        Optional<CityAdvisor> newCityAdvisor = createCityAdvisor(id, name, advice);
        CityAdvisorDto newCityAdvisorDto = createCityAdvisorDto(id, name, advice);

        when(cityAdvisorDao.findCityAdvisorByName(name)).thenReturn(newCityAdvisor);

        assertThrows(ResourceAlreadyExistException.class,
                () -> cityAdvisorService.insert(newCityAdvisorDto));
    }

    @DisplayName("Testing method update() on positive result")
    @Test
    void updateSuccessTest() throws ResourceNotFoundException {
        int id = 3;
        String advice = "Pack a copy of your passport and credit cards.";
        CityAdvisorDto newCityAdvisorDto = createCityAdvisorDto(id, null, advice);

        String oldName = "Mexico";
        String oldAdvice = "Choose your destination carefully.";

        Optional<CityAdvisor> cityAdvisorFromDataBase = createCityAdvisor(id, oldName, oldAdvice);
        when(cityAdvisorDao.findById(id)).thenReturn(cityAdvisorFromDataBase);

        CityAdvisorDto cityAdvisorDtoAfterUpdate = createCityAdvisorDto(id, oldName, advice);

        when(cityAdvisorMapper.toDto(cityAdvisorFromDataBase.get())).thenReturn(cityAdvisorDtoAfterUpdate);

        CityAdvisorDto cityAdvisorDto = cityAdvisorService.update(newCityAdvisorDto);
        assertEquals(cityAdvisorDto.getAdvice(), advice);
    }

    @DisplayName("Testing method update() on negative result with ResourceNotFoundException")
    @Test
    void updateThrowsResourceNotFoundExceptionTest() {
        int id = 20;
        CityAdvisorDto newCityAdvisorDto = new CityAdvisorDto();

        when(cityAdvisorDao.findById(id)).thenReturn(null);

        assertThrows(ResourceNotFoundException.class,
                () -> cityAdvisorService.update(newCityAdvisorDto));
    }

    private Optional<CityAdvisor> createCityAdvisor(int id, String name, String advice) {
        CityAdvisor cityAdvisor = new CityAdvisor();
        cityAdvisor.setId(id);
        cityAdvisor.setName(name);
        cityAdvisor.setAdvice(advice);

        return Optional.of(cityAdvisor);
    }

    private CityAdvisorDto createCityAdvisorDto(int id, String name, String advice) {
        CityAdvisorDto cityAdvisorDto = new CityAdvisorDto();
        cityAdvisorDto.setId(id);
        cityAdvisorDto.setName(name);
        cityAdvisorDto.setAdvice(advice);

        return cityAdvisorDto;
    }
}