package ex;

import org.glassfish.grizzly.Buffer;
import org.glassfish.grizzly.Connection;
import org.glassfish.grizzly.filterchain.BaseFilter;
import org.glassfish.grizzly.filterchain.FilterChainBuilder;
import org.glassfish.grizzly.filterchain.FilterChainContext;
import org.glassfish.grizzly.filterchain.NextAction;
import org.glassfish.grizzly.filterchain.TransportFilter;
import org.glassfish.grizzly.http.HttpClientFilter;
import org.glassfish.grizzly.http.HttpContent;
import org.glassfish.grizzly.http.HttpRequestPacket;
import org.glassfish.grizzly.http.util.Header;
import org.glassfish.grizzly.http.util.HeaderValue;
import org.glassfish.grizzly.impl.FutureImpl;
import org.glassfish.grizzly.impl.SafeFutureImpl;
import org.glassfish.grizzly.memory.Buffers;
import org.glassfish.grizzly.memory.MemoryManager;
import org.glassfish.grizzly.nio.transport.TCPNIOTransport;
import org.glassfish.grizzly.nio.transport.TCPNIOTransportBuilder;

import java.io.IOException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class WebClient {
    private static final Logger LOGGER = Logger.getLogger(WebClient.class.getName());
    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    public void run() throws IOException {
        final FutureImpl<String> completeFuture = SafeFutureImpl.create();

        // Build HTTP client filter chain
        FilterChainBuilder clientFilterChainBuilder = FilterChainBuilder.stateless();
        // Add transport filter
        clientFilterChainBuilder.add(new TransportFilter());

        // Add HttpClientFilter, which transforms Buffer <-> HttpContent
        clientFilterChainBuilder.add(new HttpClientFilter());
        // Add ClientFilter
        clientFilterChainBuilder.add(new ClientFilter(completeFuture));

        // Initialize Transport
        final TCPNIOTransport transport = TCPNIOTransportBuilder.newInstance().build();
        // Set filterchain as a Transport Processor
        transport.setProcessor(clientFilterChainBuilder.build());

        try {
            // start the transport
            transport.start();

            Connection connection = null;

            // Connecting to a remote Web server
            Future<Connection> connectFuture = transport.connect(HOST, PORT);
            try {
                // Wait until the client connect operation will be completed
                // Once connection has been established, the POST will
                // be sent to the server.
                connection = connectFuture.get(10, TimeUnit.SECONDS);

                // Wait no longer than 30 seconds for the response from the
                // server to be complete.
                String result = completeFuture.get(30, TimeUnit.SECONDS);

                // Display the echoed content
                System.out.println("\nEchoed POST Data: " + result + '\n');
            } catch (Exception e) {
                if (connection == null) {
                    LOGGER.log(Level.WARNING, "Connection failed.  Server is not listening.");
                } else {
                    LOGGER.log(Level.WARNING, "Unexpected error communicating with the server.");
                }
            } finally {
                // Close the client connection
                if (connection != null) {
                    connection.closeSilently();
                }
            }
        } finally {
            // stop the transport
            transport.shutdownNow();
        }
    }

    // ------------------------------------------------------ Nested Classes
    private static final class ClientFilter extends BaseFilter {
        private static final HeaderValue HOST_HEADER_VALUE = HeaderValue.newHeaderValue(HOST + ':' + PORT).prepare();

        private static final String[] CONTENT = {
                "contentA-",
                "contentB-",
                "contentC-",
                "contentD"
        };

        private final FutureImpl<String> future;

        private final StringBuilder sb = new StringBuilder();

        // ---------------------------------------------------- Constructors
        private ClientFilter(FutureImpl<String> future) {
            this.future = future;
        }


        // ----------------------------------------- Methods from BaseFilter
        @SuppressWarnings({"unchecked"})
        @Override
        public NextAction handleConnect(FilterChainContext ctx) throws IOException {
            System.out.println("\nClient connected!\n");

            HttpRequestPacket request = createRequest();
            System.out.println("Writing request:\n");
            System.out.println(request.toString());
            ctx.write(request); // write the request

            // for each of the content parts in CONTENT, wrap in a Buffer,
            // create the HttpContent to wrap the buffer and write the
            // content.
            MemoryManager mm = ctx.getConnection().getTransport().getMemoryManager();
            for (int i = 0, len = CONTENT.length; i < len; i++) {
                HttpContent.Builder contentBuilder = request.httpContentBuilder();
                Buffer b = Buffers.wrap(mm, CONTENT[i]);
                contentBuilder.content(b);
                HttpContent content = contentBuilder.build();
                System.out.printf("(Client writing: %s)\n", b.toStringContent());
                ctx.write(content);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // since the request created by createRequest() is chunked,
            // we need to write the trailer to signify the end of the
            // POST data
            ctx.write(request.httpTrailerBuilder().build());

            System.out.println("\n");

            return ctx.getStopAction(); // discontinue filter chain execution
        }

        @Override
        public NextAction handleRead(FilterChainContext ctx) throws IOException {

            HttpContent c = ctx.getMessage();
            Buffer b = c.getContent();
            if (b.hasRemaining()) {
                sb.append(b.toStringContent());
            }

            // Last content from the server, set the future result so
            // the client can display the result and gracefully exit.
            if (c.isLast()) {
                future.result(sb.toString());
            }

            return ctx.getStopAction(); // discontinue filter chain execution

        }

        // ------------------------------------------------- Private Methods
        private HttpRequestPacket createRequest() {

            HttpRequestPacket.Builder builder = HttpRequestPacket.builder();
            builder.method("POST");
            builder.protocol("HTTP/1.1");
            builder.uri("/api");
            builder.chunked(true);
            HttpRequestPacket packet = builder.build();
            packet.addHeader(Header.Host, HOST_HEADER_VALUE);
            return packet;

        }
    }

}