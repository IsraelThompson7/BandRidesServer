package com.catglo.bandridesserver;


import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.servlet.http.HttpServletRequest;


import com.google.appengine.api.datastore.KeyFactory;
import com.google.gson.Gson;

@PersistenceCapable(detachable="true")
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
	
	public void update(HttpServletRequest request)
	{
		String temp = request.getParameter("name");
		if (temp != null) name = temp;
		
		temp = request.getParameter("description");
		if (temp != null) description = temp;
				
		temp = request.getParameter("imageUrl");
		if (temp != null) imageUrl = temp;
		
		temp = request.getParameter("soundcloud");
		if (temp != null) soundcloud = temp;
		
		temp = request.getParameter("YouTube");
		if (temp!=null) YouTube = temp;
		
		temp = request.getParameter("imageURL");
		if (temp!=null) imageURL=temp;
		
	}
	
	public Band(HttpServletRequest request){
		update(request);
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
