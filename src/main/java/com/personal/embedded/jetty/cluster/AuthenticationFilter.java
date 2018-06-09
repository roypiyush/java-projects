package com.personal.embedded.jetty.cluster;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.personal.db.jdbc.JdbcMain;

public class AuthenticationFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		System.out.println("Filter initialized");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		@SuppressWarnings("unused")
		HttpServletResponse res = (HttpServletResponse) response;
		try {
		
			if(req.getCookies().length == 0) {
				LOGGER.info("This is new session");
			}
			else {
				
				String sessionId = req.getCookies().length == 0 ? "" : req.getCookies()[0].getValue().split("\\.")[0];
				Connection connection = JdbcMain.getConnection("jdbc:mysql://127.0.0.1:3306/sessions", "root", "root");
				String query = String.format("select id from JettySessionIds where id = '%s'", sessionId);
				PreparedStatement statement = connection.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery();
				String storedSessionId = null;
				while(resultSet.next()) {
					storedSessionId = resultSet.getString(1);
				}
				
				if(storedSessionId == null) {
					LOGGER.info("This is expired session");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LOGGER.info("Perform filtering activities");
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
		System.out.println("Destroying Authentication Filter");
	}

}
