package com.teodora.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teodora.springcloud.model.Forecast;

@Repository
public interface ForecastRepo extends JpaRepository<Forecast, Long> {

}
