package com.catglo.bandridesserver;



import java.util.Random;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;


@PersistenceCapable	
public class User extends DataBaseModel {
	@Persistent public String name;
	@Persistent public String cell;
	@Persistent public String email;
	@Persistent public String city;
	@Persistent public String address;
	@Persistent public boolean isMale;
	@Persistent public double Lat;
	@Persistent public double Lng;
	@Persistent public String generatedKey;
	
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@PrimaryKey Long id;
	
	public User(HttpServletRequest request){
		name = request.getParameter("name");
		cell = request.getParameter("cell");
		email = request.getParameter("email");
		city = request.getParameter("city");
		address = request.getParameter("address");
		String temp = request.getParameter("isMale");
		if (temp==null){
			isMale = false;
		} else {
			isMale = true;
		}
		try {
			Lat = Float.parseFloat(request.getParameter("Lat"));
		} catch (Exception e){}
		try {
			Lng = Float.parseFloat(request.getParameter("Lng"));
		} catch (Exception e){}
		generatedKey = generateString(15);
	}
	
	
	public static String generateString(int length)
	{
		char[] chars = "abcdefghijklmnopqrstuvwxyz123456789!@#$%^&*(".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();
	}
	
    public static User constructFromJson(String json)
    {
    	Gson gson = new Gson();
    	return gson.fromJson(json, User.class);
    }
     
}
