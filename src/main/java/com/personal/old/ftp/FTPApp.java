package com.personal.old.ftp;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FTPApp {

	public static void main(String[] args) {
		
		String server = "ftp.mozilla.org";
		
		FTPClient ftp = new FTPClient();
	    FTPClientConfig config = new FTPClientConfig();

	    ftp.configure(config );
	    boolean error = false;
	    try {
	      int reply;
	      
		ftp.connect(server);
	      System.out.println("Connected to " + server + ".");
	      System.out.print(ftp.getReplyString());

	      // After connection attempt, you should check the reply code to verify
	      // success.
	      reply = ftp.getReplyCode();

	      if(!FTPReply.isPositiveCompletion(reply)) {
	        ftp.disconnect();
	        System.err.println("FTP server refused connection.");
	        System.exit(1);
	      }
	      
	      FTPFile[] files = ftp.listFiles("pub/mozilla.org/firefox/releases/0.8/");
	      
	      for (int i = 0; i < files.length; i++) {
			
	    	  System.out.println(files[i].getName());
		}
	      
	      // transfer files
//	      ftp.logout();
		} catch (IOException e) {
	      error = true;
	      e.printStackTrace();
	    } finally {
	      if(ftp.isConnected()) {
	        try {
	          ftp.disconnect();
	        } catch(IOException ioe) {
	          // do nothing
	        }
	      }
	      System.exit(error ? 1 : 0);
	    }

	}

}
