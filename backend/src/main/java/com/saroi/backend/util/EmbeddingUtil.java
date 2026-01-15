package com.saroi.backend.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

public class EmbeddingUtil {

    public static byte[] toBytes(List<Float> v) {
        ByteBuffer b = ByteBuffer.allocate(v.size() * 4)
                .order(ByteOrder.LITTLE_ENDIAN);
        v.forEach(b::putFloat);
        return b.array();
    }

    public static float[] fromBytes(byte[] bytes) {
        ByteBuffer b = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);
        float[] out = new float[bytes.length / 4];
        for (int i = 0; i < out.length; i++) out[i] = b.getFloat();
        return out;
    }
}
    