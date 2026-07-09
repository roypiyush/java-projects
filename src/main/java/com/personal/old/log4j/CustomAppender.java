package com.personal.old.log4j;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Created by piyushr on 12/19/17.
 */
public class CustomAppender extends AppenderSkeleton {


    protected boolean fileAppend = true;
    protected String fileName = null;
    protected boolean bufferedIO = false;
    protected int bufferSize = 8*1024;

    public void setFile(String file) {
        String val = file.trim();
        fileName = val;
    }

    public synchronized void setFile(String fileName, boolean append, boolean bufferedIO, int bufferSize, LoggingEvent event)
            throws IOException {

        LogLog.debug("setFile called: "+fileName+", "+append);

        // It does not make sense to have immediate flush and bufferedIO.

        FileOutputStream ostream = null;
        try {
            //
            //   attempt to create file
            //
            ostream = new FileOutputStream(fileName, append);
        } catch(FileNotFoundException ex) {
            //
            //   if parent directory does not exist then
            //      attempt to create it and try to create file
            //      see bug 9150
            //
            String parentName = new File(fileName).getParent();
            if (parentName != null) {
                File parentDir = new File(parentName);
                if(!parentDir.exists() && parentDir.mkdirs()) {
                    ostream = new FileOutputStream(fileName, append);
                } else {
                    throw ex;
                }
            } else {
                throw ex;
            }
        }
        Writer fw = createWriter(ostream);
        if(bufferedIO) {
            fw = new BufferedWriter(fw, bufferSize);
        }

        fw.write(layout.format(event));
        fw.flush();
        fw.close();
        this.fileName = fileName;
        this.fileAppend = append;
        this.bufferedIO = bufferedIO;
        this.bufferSize = bufferSize;
        LogLog.debug("setFile ended");
    }

    @Override
    public void append(LoggingEvent event) {
        event.getMessage();
        try {
            setFile(fileName, true, false, 1024*8, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return true;
    }

    protected OutputStreamWriter createWriter(OutputStream os) {
        OutputStreamWriter retval = null;

        String enc = "UTF-8";
        if(enc != null) {
            try {
                retval = new OutputStreamWriter(os, enc);
            } catch(IOException e) {
                if (e instanceof InterruptedIOException) {
                    Thread.currentThread().interrupt();
                }
                LogLog.warn("Error initializing output writer.");
                LogLog.warn("Unsupported encoding?");
            }
        }
        if(retval == null) {
            retval = new OutputStreamWriter(os);
        }
        return retval;
    }
}
