package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.Body;
import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;

/**
 * @author Julian Jupiter
 */
final record KittyBody(ByteBuf content) implements Body {
    KittyBody(ByteBuf content) {
        this.content = content;
    }

    @Override
    public boolean isReadable() {
        return content.isReadable();
    }

    @Override
    public boolean isWritable() {
        return content.isWritable();
    }

    @Override
    public int size() {
        return content.readableBytes();
    }

    @Override
    public String toString() {
        return content.toString(CharsetUtil.UTF_8);
    }
}
