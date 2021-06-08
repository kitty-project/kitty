module com.julianjupiter.kitty {
    exports com.julianjupiter.kitty;
    exports com.julianjupiter.kitty.util;
    requires com.julianjupiter.kitty.http.handler;
    requires io.netty.transport;
    requires io.netty.codec.http;
    requires io.netty.buffer;
    requires io.netty.common;
    requires io.netty.codec;
    requires io.netty.handler;
}