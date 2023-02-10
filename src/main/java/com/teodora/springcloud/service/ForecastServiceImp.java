package com.teodora.springcloud.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import com.teodora.springcloud.dao.ForecastDao;
import com.teodora.springcloud.model.Category;
import com.teodora.springcloud.model.Forecast;

public class ForecastServiceImp implements ForecastService {
	
	@Autowired
	ForecastDao forecastDao;

	@Override
	public Forecast create(String name, Date date, int value, String categoryName, Category category) {
		// TODO Auto-generated method stub
		Forecast forecast = new Forecast(name,date,value,categoryName,category);
		forecastDao.create(forecast);
		return forecast;
	}

	@Override
	public Forecast getForecast(Long id) {
		// TODO Auto-generated method stub
		return forecastDao.getForecast(id);
	}

	@Override
	public void updateForecast(Long id, String name, Date date, int value, String categoryName, Category category) {
		// TODO Auto-generated method stub
		Forecast updateForecast = forecastDao.getForecast(id);
		updateForecast.setName(name);
		updateForecast.setDate(date);
		updateForecast.setValue(value);
		updateForecast.setCategoryName(categoryName);
		updateForecast.setCategory(category);
		forecastDao.updateForecast(updateForecast);
		
	}

	@Override
	public void deleteForecast(Forecast forecast) {
		// TODO Auto-generated method stub
		forecastDao.deleteForecast(forecast);

	}

	@Override
	public List<Forecast> getForecasts() {
		// TODO Auto-generated method stub
		return forecastDao.getForecasts();
	}

}
