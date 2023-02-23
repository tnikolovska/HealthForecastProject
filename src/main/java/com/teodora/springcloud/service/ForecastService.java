package com.teodora.springcloud.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.teodora.springcloud.model.Category;
import com.teodora.springcloud.model.Forecast;

public interface ForecastService {
	Forecast create(String name, Date date, BigDecimal value, String categoryName, int categoryValue ,Category category,String text);
	Forecast getForecast(Long id);
	void updateForecast(Long id, String name, Date date, BigDecimal value, String categoryName, int categoryValue,Category category,String text);
	void deleteForecast(Forecast forecast);
	List<Forecast> getForecasts();
}
