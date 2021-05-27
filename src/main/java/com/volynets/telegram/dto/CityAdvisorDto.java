package com.volynets.telegram.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

/**
 * Dto of CityAdvisor entity.
 */
@Data
@NoArgsConstructor
public class CityAdvisorDto {

    @Null
    private Integer id;
    @Pattern(regexp = "[A-Za-z \\-]+")
    private String name;
    @Pattern(regexp = "[\\w \\p{Punct}]+")
    private String advice;

}
