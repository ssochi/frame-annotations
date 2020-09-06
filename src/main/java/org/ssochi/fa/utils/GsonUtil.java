package org.ssochi.fa.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GsonUtil {
    public static ThreadLocal<Gson> gsons = new ThreadLocal<>();
    public static ThreadLocal<JsonParser> parsers = new ThreadLocal<>();

    public static Gson getGson(){
        Gson gson = gsons.get();
        if (gson == null){
            gson = new Gson();
            gsons.set(gson);
        }
        return gson;
    }

    public static JsonParser getParse(){
        JsonParser parser = parsers.get();
        if (parser == null){
            parser  = new JsonParser();
            parsers.set(parser);
        }
        return parser;
    }

    public static JsonElement from(Object obj){
        if (obj == null){
            return new JsonObject();
        }
        return getGson().toJsonTree(obj);
    }
    public static JsonElement from(Object obj,Object defaultObj){
        if (obj == null){
            obj = defaultObj;
        }
        return getGson().toJsonTree(obj);
    }
}
