package com.teodora.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teodora.springcloud.model.Forecast;

public interface ForecastRepo extends JpaRepository<Forecast, Long> {

}
