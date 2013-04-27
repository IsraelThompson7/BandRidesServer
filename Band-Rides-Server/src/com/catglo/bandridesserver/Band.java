package com.catglo.bandridesserver;


import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.servlet.http.HttpServletRequest;


import com.google.appengine.api.datastore.KeyFactory;
import com.google.gson.Gson;

@PersistenceCapable	
public class Band  
{
	@Persistent public String name;
	@Persistent public String description;
	@Persistent public String imageUrl;
	@Persistent public String soundcloud;
	@Persistent public String YouTube;
	@Persistent public String imageURL;
	
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@PrimaryKey public Long bandID;
	
	public Band(){
		name = "";
		description = "";
		imageUrl = "";
		soundcloud = "";
		YouTube = "";
		imageURL = "";
	}
	
	public static Band BandWithId(Long id){
		PersistenceManager pm = PMF.get().getPersistenceManager();
        com.google.appengine.api.datastore.Key k = KeyFactory.createKey(Band.class.getSimpleName(), id);
        Band band = pm.getObjectById(Band.class, k);
        return band;
	}
	
	public Band(HttpServletRequest request){
		name = request.getParameter("name");
		description = request.getParameter("description");
		imageUrl = request.getParameter("imageUrl");
		soundcloud = request.getParameter("soundcloud");
		YouTube = request.getParameter("YouTube");
		imageURL = request.getParameter("imageURL");
	}
	
	public String toJson() {
    	Gson gson = new Gson();
    	String json = gson.toJson(this);
    	return json;
    }
	
	public void makePersistant(){
	    PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(this);
        } finally {
            pm.close();

        }   
	}
}
