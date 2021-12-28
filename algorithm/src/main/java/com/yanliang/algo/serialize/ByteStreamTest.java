package com.yanliang.algo.serialize;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 字节流测试
 *
 * @author yanliang
 * @date 8/18/20205:54 PM
 */
public class ByteStreamTest {
    public static void main(String[] args) {
        BloomFilter<String> bloomFilter =
                BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), 10, 0.001);
        bloomFilter.put("yanliang");

        byte[] bytes = writeTo(bloomFilter);

        BloomFilter bloomFilter1 = readFrom(bytes);

        System.out.println(bloomFilter.equals(bloomFilter1));

        System.out.println(bloomFilter1.mightContain("yanliang"));
    }

    private static byte[] writeTo(BloomFilter bloomFilter) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            bloomFilter.writeTo(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    private static BloomFilter readFrom(byte[] bytes) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        BloomFilter bloomFilter = null;
        try {
            bloomFilter =
                    BloomFilter.readFrom(
                            inputStream, Funnels.stringFunnel(Charset.defaultCharset()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bloomFilter;
    }
}
