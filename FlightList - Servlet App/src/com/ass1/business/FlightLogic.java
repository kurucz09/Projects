package com.ass1.business;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.cfg.Configuration;

import com.ass1.da.CityDAO;
import com.ass1.da.CityEntity;
import com.ass1.da.FlightDAO;
import com.ass1.da.FlightEntity;

public class FlightLogic {
private FlightDAO flightDAO;
private CityDAO cityDAO;
	
	public FlightLogic(){
		flightDAO = new FlightDAO(new Configuration().configure().buildSessionFactory());
		cityDAO = new CityDAO(new Configuration().configure().buildSessionFactory());
	}
	
	public FlightEntity addFlight(FlightEntity flight){
		return flightDAO.addFlight(flight);
	}

	public List<FlightEntity> getAllFlights() {
		return flightDAO.getAllFlights();
	}

	public FlightEntity updateFlight(FlightEntity flight) {
		return flightDAO.updateFlight(flight);
	}

	public void removeFlight(String flightNumber) {
		flightDAO.deleteFlight(flightNumber);
	}
	
	public Date getLocalTimeArrival(FlightEntity flight){
		try {
			CityEntity city = cityDAO.findCity(flight.getArrivalCity());
			String databaseDate = flight.getArrivalDatetime();
			String pattern = "yyyy-MM-dd'T'HH:mm";
			DateFormat dateFormat = new SimpleDateFormat(pattern);
			Date date;
			date = dateFormat.parse(databaseDate);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			long time = c.getTimeInMillis();
			String key = "AIzaSyC67_WHSg9KKO1su09olyn-oSQdWyOZ81E";
			
			String targetURL = "https://maps.googleapis.com/maps/api/timezone/json?"
					+ "location="+city.getLat()+","+city.getLon()+"&timestamp="
							+ time/10000 +"&key="+key;
			String urlParameters = "";
		
			String[] response = executePost(targetURL, urlParameters);
			int hOffset = Integer.valueOf(response[0]);
			int DST = Integer.valueOf(response[1]);
			time=time - 7200*1000 + hOffset*1000;
			time-=DST*1000;
			System.out.println("After "+ time);
			Date dateFinal = new Date(time);
			return dateFinal;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		
		return null;
		
	}
	
	public Date getLocalTimeDeparture(FlightEntity flight){

		try {
			CityEntity city = cityDAO.findCity(flight.getDepartureCity());
			String databaseDate = flight.getDepartureDatetime();
			String pattern = "yyyy-MM-dd'T'HH:mm";
			DateFormat dateFormat = new SimpleDateFormat(pattern);
			Date date;
			date = dateFormat.parse(databaseDate);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			long time = c.getTimeInMillis();
			String key = "AIzaSyC67_WHSg9KKO1su09olyn-oSQdWyOZ81E";
			
			String targetURL = "https://maps.googleapis.com/maps/api/timezone/json?"
					+ "location="+city.getLat()+","+city.getLon()+"&timestamp="
							+ time/10000 +"&key="+key;
			String urlParameters = "";
			
			String[] response = executePost(targetURL, urlParameters);
			int hOffset = Integer.valueOf(response[0]);
			int DST = Integer.valueOf(response[1]);
			time=time - 7200*1000 + hOffset*1000;
			time-=DST*1000;
			Date dateFinal = new Date(time);
			return dateFinal;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		
		return null;
	}
	
	public static String[] executePost(String targetURL, String urlParameters) {
		  HttpURLConnection connection = null;
		  try {
		    //Create connection
		    URL url = new URL(targetURL);
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("GET");
		    connection.setRequestProperty("Content-Type", 
		        "application/x-www-form-urlencoded");

		    connection.setRequestProperty("Content-Length", 
		        Integer.toString(urlParameters.getBytes().length));
		    connection.setRequestProperty("Content-Language", "en-US");  

		    connection.setUseCaches(false);
		    connection.setDoOutput(true);

		    //Send request
		    DataOutputStream wr = new DataOutputStream (
		        connection.getOutputStream());
		    wr.writeBytes(urlParameters);
		    wr.close();

		    //Get Response  
		    InputStream is = connection.getInputStream();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		    StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
		    String line;
		    String rawOffset = "";
		    String dstOffset = "";
		    while ((line = rd.readLine()) != null) {
		    	if(line.contains("rawOffset")){
		    		rawOffset = line.substring(line.lastIndexOf(' ') +1);
		    		rawOffset = rawOffset.substring(0, rawOffset.length()-1);
		    	}
		    	if(line.contains("dstOffset")){
		    		dstOffset = line.substring(line.lastIndexOf(' ') +1);
		    		dstOffset = dstOffset.substring(0, dstOffset.length()-1);
		    	}
		      response.append(line);
		      response.append('\r');
		    }
		    rd.close();
		    String[] bothStrings = {rawOffset, dstOffset};
		    return bothStrings;
		  } catch (Exception e) {
		    e.printStackTrace();
		    return null;
		  } finally {
		    if (connection != null) {
		      connection.disconnect();
		    }
		  }
		}
}
