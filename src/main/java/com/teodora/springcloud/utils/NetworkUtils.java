package com.teodora.springcloud.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

import com.teodora.springcloud.model.HealthCondition;

public class NetworkUtils {
	private final static String WEATHERDB_BASE_URL_MIGRAINE = "http://dataservice.accuweather.com/indices/v1/daily/5day/{227397}/{27}";
	
	private final static String WEATHERDB_BASE_URL_ARTHRITIS = "http://dataservice.accuweather.com/indices/v1/daily/5day/{227397}/{21}";

	private final static String WEATHERDB_BASE_URL_SINUS = "http://dataservice.accuweather.com/indices/v1/daily/5day/{227397}/{30}";
	
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
			URI buildUri = buildUrl(WEATHERDB_BASE_URL_MIGRAINE, LANGUAGE);
			
		}
		else if(healthCondition.getName().equals("Sinus Headache")) {
			URI buildUri = buildUrl(WEATHERDB_BASE_URL_SINUS, LANGUAGE);
			
		}
		else if(healthCondition.getName().equals("Arthritis Pain")) {
			URI buildUri = buildUrl(WEATHERDB_BASE_URL_ARTHRITIS, LANGUAGE);
			
		}
		URL url = null;
		url=buildUri.toURL();
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		InputStream in=null;
		try {
			in = urlConnection.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			urlConnection.disconnect();
		}
		Scanner scanner = new Scanner (in);
		scanner.useDelimiter("\\A");
		boolean hasInput = scanner.hasNext();
		if(hasInput) {
			return scanner.next();
			
		}
		else {
			return null;
		}
		
		 
	}
}
