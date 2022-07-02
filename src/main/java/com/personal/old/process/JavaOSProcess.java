package com.personal.old.process;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JavaOSProcess {

    public static final String SSH_CMD = "/usr/bin/ssh";
    public static final String VERBOSE = "-vvv";
    public static final String HOST_CHECK = "-oStrictHostKeyChecking=no";

    public static void main(String[] args) throws IOException, InterruptedException     {

        final String USER_HOST = String.format("%s@%s", args[1], args[0]);
        final ProcessBuilder processBuilder = new ProcessBuilder(SSH_CMD, VERBOSE, HOST_CHECK, USER_HOST)
                .inheritIO();
        processBuilder.environment().put("TERM", "/dev/tty");
        final Process process = processBuilder
                .start();

        final BufferedOutputStream outputStream = new BufferedOutputStream(process.getOutputStream());

        readData(process.getInputStream());

        writeCommand(outputStream, String.format("%s", args[2]));
        readData(process.getInputStream());

        writeCommand(outputStream, "ping -c 2 localhost");
        readData(process.getInputStream());

        writeCommand(outputStream, "exit");
        readData(process.getInputStream());

        process.destroy();
        System.exit(process.waitFor());
    }

    private static void writeCommand(final BufferedOutputStream outputStream, final String command) throws IOException {
        outputStream.write(command.getBytes());
        outputStream.write("\n".getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }

    private static void readData(final BufferedReader reader) throws IOException, InterruptedException {

        int counter = 0;
        while (true) {
            if (reader.ready()) {
                counter = 0;
                final String line = reader.readLine();
                if (line == null) {
                    if (counter++ > 2) {
                        break;
                    } else {
                        Thread.sleep(1000);
                    }
                }
                System.out.println(line);
            } else {
                if (counter++ > 2) {
                    break;
                } else {
                    Thread.sleep(1000);
                }
            }
        }
    }

    private static void readData(final InputStream inputStream) throws IOException, InterruptedException {
        byte[] data;
        int counter = 0;
        while (true) {
            if (inputStream.available() > 0) {
                final int read = inputStream.read(data = new byte[8192]);
                if (read <= 0) {
                    if (counter++ > 4) {
                        break;
                    } else {
                        Thread.sleep(1000);
                    }
                    return;
                }
                if (read > 0) {
                    counter = 0;
                    System.out.print(new String(data, 0, read));
                }
            } else {
                if (counter++ > 2) {
                    break;
                } else {
                    Thread.sleep(1000);
                }
            }
        }
    }
}
