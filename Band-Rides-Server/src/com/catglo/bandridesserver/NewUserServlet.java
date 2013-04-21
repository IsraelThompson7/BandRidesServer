package com.catglo.bandridesserver;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class NewUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/json");

        PrintWriter out = resp.getWriter();
        	
        try {
	        User user = new User(req);
	        user.makePersistant();
	        out.print(user.toJson());
        } catch (Exception e){ 
        	 out.print(e.getLocalizedMessage());
        }
	}
}
