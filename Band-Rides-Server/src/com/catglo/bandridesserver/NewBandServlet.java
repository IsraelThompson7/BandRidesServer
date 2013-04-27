package com.catglo.bandridesserver;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewBandServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		resp.setContentType("text/json");

        PrintWriter out = resp.getWriter();
        	
        try {
        	long bandID = Long.parseLong(req.getParameter("bandID"));
        	if (bandID==0) throw new NumberFormatException();
        	Band band = Band.BandWithId(bandID);
        	band.update(req);
        } 
        catch (NumberFormatException numberFormatException)
        {
        	try {
            	
    	        Band band = new Band(req);
    	        band.makePersistant();
    	        out.print(band.toJson());
            } catch (Exception e){ 
            	 out.print(e.getLocalizedMessage());
            }
        }
        
        
	}
}
