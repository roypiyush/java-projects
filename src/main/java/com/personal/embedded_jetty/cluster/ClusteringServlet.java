package com.personal.embedded_jetty.cluster;

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
		
		Cookie[] cookies = req.getCookies();
		
		String sessionIdFromBrowser = (cookies.length > 0 ? cookies[0].getValue() : "");
		System.out.println("Hey, I'm processing your request " + sessionIdFromBrowser);
		HttpSession session = req.getSession(true);
		
		System.out.printf("Session1 %s and Session2 : %s", sessionIdFromBrowser.split("\\.")[0], session.getId());
		
		session.setAttribute("expiryTime", session.getId());
		
		res.getWriter().println("Your sessionId : " + session.getId());
		System.out.println("Done processing your request");
	}
}
