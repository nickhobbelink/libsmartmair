package com.allocated.smartmair.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @author Nick Hobbelink
 */
public class UrlConnectionHttpClient implements HttpClient {
    private static final Charset CHARSET = Charset.forName("UTF-8");

    @Override
    public String postJson(String urlString, String data) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");

        try(OutputStream output = conn.getOutputStream()){
            output.write(data.getBytes(CHARSET));
        }
        
        try(InputStream input = conn.getInputStream()){
            return new String(readFully(input), CHARSET);
        }
    }
    
    private byte[] readFully(InputStream input) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toByteArray();
    }

}
