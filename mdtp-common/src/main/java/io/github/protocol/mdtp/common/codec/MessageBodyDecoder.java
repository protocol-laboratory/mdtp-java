package io.github.protocol.mdtp.common.codec;

import io.github.protocol.mdtp.common.model.AbstractMessageBody;
import io.netty.buffer.ByteBuf;

public interface MessageBodyDecoder {
    AbstractMessageBody handle(ByteBuf in);
}
