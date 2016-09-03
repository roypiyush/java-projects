package com.personal.embedded.jetty.cluster;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ClusteringServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Override
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);
		Cookie[] cookies = req.getCookies();
		String sessionIdFromBrowser = (cookies.length > 0 ? cookies[0].getValue() : "");
		
		if(!sessionIdFromBrowser.equalsIgnoreCase(session.getId())) {
			System.out.println("This is new or expired session");
		}
		
		System.out.printf("Old Session %s and New Session : %s\n", sessionIdFromBrowser.split("\\.")[0], session.getId());
		
		session.setAttribute("expiryTime", session.getId());
		
		res.getWriter().println("Your sessionId : " + session.getId());
		System.out.println("Done processing your request");
	}
}
