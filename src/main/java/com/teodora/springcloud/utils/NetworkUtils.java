package com.teodora.springcloud.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

import com.teodora.springcloud.model.HealthCondition;

public class NetworkUtils {
	//private final static String WEATHERDB_BASE_URL_MIGRAINE = "http://dataservice.accuweather.com/indices/v1/daily/5day/{227397}/{27}";
	
	private final static String WEATHERDB_BASE_URL_MIGRAINE = "https://dataservice.accuweather.com/indices/v1/daily/5day/227397/27?apikey=DjvaebcGIABJ9MQvlLu3dxn98XmzI1dJ&language=en-us&details=true";
	
	//private final static String WEATHERDB_BASE_URL_ARTHRITIS = "http://dataservice.accuweather.com/indices/v1/daily/5day/{227397}/{21}";

	private final static String WEATHERDB_BASE_URL_ARTHRITIS = "https://dataservice.accuweather.com/indices/v1/daily/5day/227397/21?apikey=DjvaebcGIABJ9MQvlLu3dxn98XmzI1dJ&language=en-us&details=true";
	
	//private final static String WEATHERDB_BASE_URL_SINUS = "http://dataservice.accuweather.com/indices/v1/daily/5day/{227397}/{30}";
	
	private final static String WEATHERDB_BASE_URL_SINUS = "https://dataservice.accuweather.com/indices/v1/daily/5day/227397/30?apikey=DjvaebcGIABJ9MQvlLu3dxn98XmzI1dJ&language=en-us&details=true";
	
	private final static String API_KEY = "DjvaebcGIABJ9MQvlLu3dxn98XmzI1dJ";
	
	private final static String LANGUAGE = "lang=en-us";
	
	private final static String DETAILS = "true";
	
	private static URI buildUri = null;
	
	public static URI buildUrl(String uri, String appendQuery) throws URISyntaxException {
		 URI oldUri = new URI(uri);
		 String newQuery = oldUri.getQuery();
		 if(newQuery==null) {
			 newQuery=appendQuery;
			 
		 }
		 else {
			 newQuery+="&"+appendQuery;
			 
		 }
		 return new URI(oldUri.getScheme(),oldUri.getAuthority(),oldUri.getPath(),newQuery,oldUri.getFragment());
		
		
	}
	public static String getResponseFromHttpUrl(HealthCondition healthCondition) throws URISyntaxException, IOException{
		if(healthCondition.getName().equals("Migraine Headache")) {
			//URI buildUri = buildUrl(WEATHERDB_BASE_URL_MIGRAINE, LANGUAGE);
			 buildUri = new URI(WEATHERDB_BASE_URL_MIGRAINE);
		}
		else if(healthCondition.getName().equals("Sinus Headache")) {
			//URI buildUri = buildUrl(WEATHERDB_BASE_URL_SINUS, LANGUAGE);
			 buildUri = new URI(WEATHERDB_BASE_URL_SINUS);
		}
		else if(healthCondition.getName().equals("Arthritis Pain")) {
			//URI buildUri = buildUrl(WEATHERDB_BASE_URL_ARTHRITIS, LANGUAGE);
			 buildUri = new URI(WEATHERDB_BASE_URL_ARTHRITIS);
		}
		URL url;
		url=buildUri.toURL();
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		int responseCode = urlConnection.getResponseCode();
		if(responseCode==HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			//System.out.println(response.toString());
			return response.toString();
		}
		else {
			return null;
		}
		 
	}
}
