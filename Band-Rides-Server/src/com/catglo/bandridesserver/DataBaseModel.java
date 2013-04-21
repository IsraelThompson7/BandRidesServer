package com.catglo.bandridesserver;


import javax.jdo.PersistenceManager;
import javax.jdo.annotations.PersistenceCapable;

import com.google.gson.Gson;

@PersistenceCapable	
abstract public class DataBaseModel {
	
	
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
