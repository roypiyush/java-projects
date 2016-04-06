/**
 * 
 */
package com.personal.ws.rest;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.session.JDBCSessionIdManager;
import org.eclipse.jetty.server.session.JDBCSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

/**
 * @author piyush
 *
 */
public class ServiceStartup {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		QueuedThreadPool threadPool = new QueuedThreadPool(8, 8);
		final Server server = new Server(threadPool);
		ServletContextHandler contextHandler = new ServletContextHandler(
				ServletContextHandler.SESSIONS);
		contextHandler.setContextPath("/");

		ServletHolder holder = contextHandler.addServlet(
				org.glassfish.jersey.servlet.ServletContainer.class, "/*");
		holder.setInitOrder(0);

		holder.setInitParameter("jersey.config.server.provider.classnames",
				RestWebService.class.getCanonicalName());

		ServerConnector connector = new ServerConnector(server);
		Connector[] connectors = {connector}; 
		connector.setPort(Integer.parseInt(System.getProperty("port")));
		server.setConnectors(connectors);
		
		server.setHandler(contextHandler);

		JDBCSessionIdManager idMgr = new JDBCSessionIdManager(server);
		idMgr.setWorkerName("fred");
		idMgr.setDriverInfo("com.mysql.jdbc.Driver",
				"jdbc:mysql://127.0.0.1:3306/sessions?user=root&password=root");
		idMgr.setScavengeInterval(120);

		server.setSessionIdManager(idMgr);
		JDBCSessionManager jdbcMgr = new JDBCSessionManager();
		jdbcMgr.setSaveInterval(120);
		jdbcMgr.setSessionIdManager(server.getSessionIdManager());
		SessionHandler sessionHandler = new SessionHandler(jdbcMgr);
		contextHandler.getSessionHandler().setSessionManager(jdbcMgr);
		contextHandler.setSessionHandler(sessionHandler);
		
		try {
			server.start();
			server.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

}
