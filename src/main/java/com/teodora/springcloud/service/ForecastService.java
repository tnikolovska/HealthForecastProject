package com.teodora.springcloud.service;

import java.util.Date;
import java.util.List;

import com.teodora.springcloud.model.Category;
import com.teodora.springcloud.model.Forecast;

public interface ForecastService {
	Forecast create(String name, Date date, int value, String categoryName, Category category);
	Forecast getForecast(Long id);
	void updateForecast(Long id, String name, Date date, int value, String categoryName, Category category);
	void deleteForecast(Forecast forecast);
	List<Forecast> getForecasts();
}
