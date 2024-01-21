package com.allocated.smartmair.util;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Nick Hobbelink
 */
public class JsonUtil {
    public static final Gson GSON = new GsonBuilder().create();
    
    public static <T> T parse(String json, Class<T> cls) throws IOException{
        try{
            return GSON.fromJson(json, cls);
        } catch(Throwable e){
            throw new IOException("Unable to parse JSON " + e.getMessage(), e);
        }
    }
    
    public static String toString(Object object){
        return GSON.toJson(object);
    }
}
