package com.volynets.telegram.mapper.impl;


import com.volynets.telegram.dto.CityAdvisorDto;
import com.volynets.telegram.mapper.Mapper;
import com.volynets.telegram.model.CityAdvisor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for CityAdvisor entity.
 */
@Component
public class CityAdvisorMapperImpl implements Mapper {

    @Override
    public CityAdvisor toEntity(CityAdvisorDto dto) {
        if (dto == null) {
            return null;
        }
        CityAdvisor cityAdvisor = new CityAdvisor();
        cityAdvisor.setName(dto.getName());
        cityAdvisor.setAdvice(dto.getAdvice());

        return cityAdvisor;
    }

    @Override
    public CityAdvisorDto toDto(CityAdvisor entity) {
        if (entity == null) {
            return null;
        }
        CityAdvisorDto cityAdvisorDto = new CityAdvisorDto();
        cityAdvisorDto.setId(entity.getId());
        cityAdvisorDto.setName(entity.getName());
        cityAdvisorDto.setAdvice(entity.getAdvice());

        return cityAdvisorDto;
    }

    @Override
    public List<CityAdvisorDto> toDtoList(List<CityAdvisor> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
