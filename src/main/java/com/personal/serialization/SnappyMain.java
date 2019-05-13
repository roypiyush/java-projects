package com.personal.serialization;

import org.apache.commons.codec.binary.Base64;
import org.xerial.snappy.Snappy;

import java.nio.charset.StandardCharsets;

public class SnappyMain {
    public static void main(String[] args) throws Exception {
        String input = "Hello snappy-java! Snappy-java is a JNI-based wrapper of Snappy, a fast compresser/decompresser.";
        byte[] compressed = Snappy.compress(input.getBytes(StandardCharsets.UTF_8));

        System.out.println(String.format("%s", new String(compressed)));
        System.out.println();
        System.out.println(String.format("Compressed  [ %s ] ", Base64.encodeBase64URLSafeString(compressed)));
        byte[] uncompressed = Snappy.uncompress(compressed);
        String result = new String(uncompressed, StandardCharsets.UTF_8);

    }
}
