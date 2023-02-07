package com.teodora.springcloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teodora.springcloud.model.Forecast;
import com.teodora.springcloud.repos.ForecastRepo;



@RestController
@RequestMapping("/forecastapi")
public class ForecastRestController {
	
	@Autowired
	ForecastRepo repo;	
	
	@RequestMapping(value = "/forecasts", method = RequestMethod.POST)
	public Forecast create(@RequestBody Forecast forecast) {
		return repo.save(forecast);
	}
	
	@RequestMapping(value="/forecasts/{id}", method = RequestMethod.GET)
	public Forecast getForecast(@PathVariable("id") Long id) {
		return repo.findById(id).orElse(null);
		
	}
	@RequestMapping(value="/updateforecasts", method = RequestMethod.PUT)
	public Forecast updateSymptom(@RequestBody Forecast forecast) {
		return repo.save(forecast);
	}
	@RequestMapping(value="/deleteforecast/{id}", method = RequestMethod.DELETE)
	public void deleteCategory(@PathVariable("id") Long Id) {
		repo.deleteById(Id);
		
	}
}
