/**
 * 
 */
package com.personal.embedded.grizzly;

import java.io.IOException;
import java.nio.charset.Charset;

import org.glassfish.grizzly.filterchain.FilterChainBuilder;
import org.glassfish.grizzly.filterchain.TransportFilter;
import org.glassfish.grizzly.nio.transport.TCPNIOTransport;
import org.glassfish.grizzly.nio.transport.TCPNIOTransportBuilder;
import org.glassfish.grizzly.utils.StringFilter;

/**
 * @author piyush
 *
 */
public class EchoServer {

    public static final String HOST = "localhost";
    public static final int PORT = 7777;

    public static void main(String[] args) throws IOException, InterruptedException {

        // Create TCP transport
        final TCPNIOTransport transport =
                TCPNIOTransportBuilder.newInstance().build();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                transport.shutdownNow();
            } catch (IOException e) {
                System.out.println("Couldn't shutdown gracefully");
                throw new RuntimeException(e);
            }
            System.out.println("Stopped Server...");
        }));

        // Create a FilterChain using FilterChainBuilder
        FilterChainBuilder filterChainBuilder = FilterChainBuilder.stateless();
        // for reading and writing data to the connection
        filterChainBuilder.add(new TransportFilter());

        // StringFilter is responsible for Buffer <-> String conversion
        filterChainBuilder.add(new StringFilter(Charset.forName("UTF-8")));

        // EchoFilter is responsible for echoing received messages
        filterChainBuilder.add(new EchoFilter());

        transport.setProcessor(filterChainBuilder.build());
        // binding transport to start listen on certain host and port
        transport.bind(HOST, PORT);
        // start the transport
        transport.start();
        System.out.printf("Listening on %s:%s\n", HOST, PORT);
        Thread.currentThread().join();
    }
}
