package com.teodora.springcloud.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.juli.logging.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teodora.springcloud.dao.CategoryDao;
import com.teodora.springcloud.dao.ForecastDao;
import com.teodora.springcloud.dao.HealthConditionDao;
import com.teodora.springcloud.model.Category;
import com.teodora.springcloud.model.Forecast;
import com.teodora.springcloud.model.HealthCondition;
import com.teodora.springcloud.model.Symptom;
import com.teodora.springcloud.repos.CategoryRepo;
import com.teodora.springcloud.repos.ForecastRepo;
import com.teodora.springcloud.repos.HealthConditionRepo;
import com.teodora.springcloud.utils.NetworkUtils;
import java.util.Iterator;



@Controller
//@RestController
//@RequestMapping("/forecastapi")
public class ForecastRestController {
	
	@Autowired
	ForecastRepo repo;	
	
	@Autowired
	ForecastDao dao;
	
	@Autowired
	HealthConditionRepo healthrepo;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	HealthConditionDao healthConditionDao;

	private ArrayList<Forecast> forecastArrayList = new ArrayList<>();
	
	@RequestMapping(value = "/forecasts", method = RequestMethod.POST)
	public Forecast create(@RequestBody Forecast forecast) {
		return repo.save(forecast);
	}
	
	@RequestMapping(value="/forecasts/{id}", method = RequestMethod.GET)
	public Forecast getForecast(@PathVariable("id") Long id) {
		return repo.findById(id).orElse(null);
		
	}
	@RequestMapping(value="/updateforecasts", method = RequestMethod.PUT)
	public Forecast updateForecast(@RequestBody Forecast forecast) {
		return repo.save(forecast);
	}
	@RequestMapping(value="/deleteforecast/{id}", method = RequestMethod.DELETE)
	public void deleteForecast(@PathVariable("id") Long Id) {
		repo.deleteById(Id);
		
	}
	
	@RequestMapping(value="/forecasts/list", method = RequestMethod.GET)
	public List<Forecast> getForecasts() {
		return repo.findAll();
		
	}
	
	

	@RequestMapping(value="result",method = RequestMethod.GET)
	public String getForecastResult(@RequestParam Long id, Model model) throws URISyntaxException, IOException, JSONException, ParseException {
		//HealthCondition healthCondition = dao.getHealthCondition(id);
		HealthCondition healthCondition = healthrepo.getReferenceById(id);
		//System.out.println(healthCondition.getName());
		if(healthCondition!=null) {
			//HealthCondition healthCondition = repo.getReferenceById(id);
			String forecastSearchResults = NetworkUtils.getResponseFromHttpUrl(healthCondition);
			if (forecastSearchResults!=null && !forecastSearchResults.equals("")) {
				forecastArrayList = parseJSON(forecastSearchResults);
				System.out.printf(forecastArrayList.toString());
				model.addAttribute("forecastArrayList",forecastArrayList);
				model.addAttribute("name",healthCondition.getName());
				model.addAttribute("description",healthCondition.getDescription());
			}
			
		}
		
		return "forecast-result";
	}
	
	private ArrayList<Forecast> parseJSON (String forecastSearchResults) throws JSONException, ParseException{
		if(forecastArrayList != null) {
			forecastArrayList.clear();
		}
		if(forecastSearchResults != null) {
			forecastSearchResults="{DailyIndexValues:"+forecastSearchResults+"}";
			 JSONObject rootObject = new JSONObject(forecastSearchResults);
             JSONArray results = rootObject.getJSONArray("DailyIndexValues");
             for(int i=0;i<results.length();i++) {
            	 Forecast forecast = new Forecast();
            	 JSONObject resultsObj = results.getJSONObject(i);
            	 
            	 Date date = new SimpleDateFormat("yyyy-MM-dd").parse(resultsObj.getString("LocalDateTime"));
            	 forecast.setDate(date);
            	 
            	 String name = resultsObj.getString("Name");
            	 forecast.setName(name);
            	 
            	 BigDecimal value = resultsObj.getBigDecimal("Value");
                 forecast.setValue(value);
                 
                 int categoryValue = resultsObj.getInt("CategoryValue");
                 forecast.setCategoryValue(categoryValue);
                 
                 String categoryName = resultsObj.getString("Category");
                 forecast.setCategoryName(categoryName);
                 
                 Category category = categoryRepo.findByName(categoryName);
                 forecast.setCategory(category);
                 
                 String text = resultsObj.getString("Text");
                 forecast.setText(text);
                 
                 forecastArrayList.add(forecast);
                 
             }
            
		}
		return forecastArrayList;
	}
	
	
	
	
	
}
