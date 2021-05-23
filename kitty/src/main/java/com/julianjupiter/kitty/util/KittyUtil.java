package com.julianjupiter.kitty.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Julian Jupiter
 */
public class KittyUtil {
    private KittyUtil() {
    }

    public static class Server {
        private Server() {
        }

        public static String hostAddress() {
            try {
                return InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
                LoggerFactory.getLogger(Server.class)
                        .log(System.Logger.Level.WARNING, "Unknown host, using default " + Constants.Server.DEFAULT_HOST_ADDRESS);
            }

            return Constants.Server.DEFAULT_HOST_ADDRESS;
        }
    }
}
