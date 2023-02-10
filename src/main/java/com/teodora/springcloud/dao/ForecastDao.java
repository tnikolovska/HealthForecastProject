package com.teodora.springcloud.dao;





import java.util.List;


import com.teodora.springcloud.model.Forecast;

public interface ForecastDao {
	long create(Forecast forecast);
	Forecast getForecast(Long id);
	void updateForecast(Forecast forecast);
	void deleteForecast(Forecast forecast);
	List<Forecast> getForecasts();
}
