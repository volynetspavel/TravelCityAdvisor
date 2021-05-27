package com.volynets.telegram.dao;

import com.volynets.telegram.model.CityAdvisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Dao class for performing operations with CityAdvisor-entity.
 */
@Repository
public interface CityAdvisorDao extends JpaRepository<CityAdvisor, Integer> {

    Optional<CityAdvisor> findCityAdvisorByName(String name);
}
