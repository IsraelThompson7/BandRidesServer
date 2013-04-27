package com.catglo.bandridesserver;


import java.util.Calendar;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.servlet.http.HttpServletRequest;


import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable	
public class Show  {
	@Persistent public Calendar date;
	@Persistent public String address;
	@Persistent public String city;
	@Persistent public String state;
	@Persistent public double longitude;
	@Persistent public double latitude;
	@Persistent public String locationName;
	@Persistent public String imageURL;
		
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@PrimaryKey public Long showID;
	
	public Show(){
		date = Calendar.getInstance();
		address = "";
		city = "";
		state = "";
		locationName = "";
		imageURL = "";
		longitude = 0;
		latitude = 0;
	}
	
	public static Band BandWithId(Long id){
		PersistenceManager pm = PMF.get().getPersistenceManager();
        com.google.appengine.api.datastore.Key k = KeyFactory.createKey(Band.class.getSimpleName(), id);
        Band band = pm.getObjectById(Band.class, k);
        return band;
	}
	
	public Show(HttpServletRequest request){
		//date 			  = request.getParameter("date");
		address 		= request.getParameter("address");
		city 			= request.getParameter("city");
		state 			= request.getParameter("state");
		locationName 	= request.getParameter("locationName");
		imageURL 		= request.getParameter("imageURL");
		try {
			longitude = Double.parseDouble(request.getParameter("longitude"));
			latitude  = Double.parseDouble(request.getParameter("latitude"));
		} catch (NumberFormatException e){
			longitude = 0;
			latitude = 0;
		}
	}
}
