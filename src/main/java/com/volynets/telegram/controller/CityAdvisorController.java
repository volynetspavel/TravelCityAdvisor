package com.volynets.telegram.controller;

import com.volynets.telegram.dto.CityAdvisorDto;
import com.volynets.telegram.exception.ResourceNotFoundException;
import com.volynets.telegram.service.CityAdvisorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * Controller for handling requests.
 */
@RestController
@RequestMapping("/advises")
public class CityAdvisorController {

    private final CityAdvisorService cityAdvisorService;

    public CityAdvisorController(CityAdvisorService cityAdvisorService) {
        this.cityAdvisorService = cityAdvisorService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CityAdvisorDto insert(@Valid @RequestBody CityAdvisorDto cityAdvisor) {
        return cityAdvisorService.insert(cityAdvisor);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public CityAdvisorDto update(@PathVariable("id") @Min(value = 1) int id,
                                 @Valid @RequestBody CityAdvisorDto cityAdvisor) throws ResourceNotFoundException {
        cityAdvisor.setId(id);
        return cityAdvisorService.update(cityAdvisor);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") @Min(value = 1) int id) throws ResourceNotFoundException {
        cityAdvisorService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<CityAdvisorDto> findAll() {
        return cityAdvisorService.findAll();
    }
}
