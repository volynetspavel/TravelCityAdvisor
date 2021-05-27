package com.volynets.telegram.mapper;

import com.volynets.telegram.dto.CityAdvisorDto;
import com.volynets.telegram.model.CityAdvisor;

import java.util.List;

/**
 * Interface for transferring between DTO-class and entity class.
 */
public interface Mapper {

    CityAdvisor toEntity(CityAdvisorDto dto);

    CityAdvisorDto toDto(CityAdvisor entity);

    List<CityAdvisorDto> toDtoList(List<CityAdvisor> entities);

}
