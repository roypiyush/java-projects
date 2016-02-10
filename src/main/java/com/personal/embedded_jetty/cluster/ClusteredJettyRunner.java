/**
 * 
 */
package com.personal.embedded_jetty.cluster;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.JDBCSessionIdManager;
import org.eclipse.jetty.server.session.JDBCSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 * @author piyush
 *
 */
public class ClusteredJettyRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final Server server = new Server(9090);
		
		int scavengeInterval = 30;
		
		JDBCSessionIdManager sessionIdManager = new JDBCSessionIdManager(server);
		sessionIdManager.setWorkerName("fred");
		sessionIdManager.setDriverInfo("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/sessions?user=root&password=root");
		sessionIdManager.setScavengeInterval(scavengeInterval);
		server.setSessionIdManager(sessionIdManager);

 		JDBCSessionManager sessionManager = new JDBCSessionManager();
 		sessionManager.setSaveInterval(30);
 		sessionManager.setSessionIdManager(server.getSessionIdManager());
 		sessionManager.setMaxInactiveInterval(scavengeInterval);
 		
 		SessionHandler sessionHandler = new SessionHandler(sessionManager);
 		
 		ServletContextHandler context = new ServletContextHandler();
 		context.setContextPath("/jetty");
 		context.addServlet(ClusteringServlet.class, "/servlet");
 		server.setHandler(context);
 		context.setSessionHandler(sessionHandler);
 		try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
