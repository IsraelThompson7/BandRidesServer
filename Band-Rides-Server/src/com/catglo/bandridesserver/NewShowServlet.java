package com.catglo.bandridesserver;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/json");

        PrintWriter out = resp.getWriter();
        	
        try {
	        Show show = new Show(req);
	        show.makePersistant();
	        out.print(show.toJson());
        } catch (Exception e){ 
        	 out.print(e.getLocalizedMessage());
        }
	}
}
