package com.allocated.smartmair.util;

import java.io.IOException;

/**
 * @author Nick Hobbelink
 *
 * Abstraction for handling HTTP requests
 */
public interface HttpClient {
    public String postJson(String url, String body) throws IOException;
}
