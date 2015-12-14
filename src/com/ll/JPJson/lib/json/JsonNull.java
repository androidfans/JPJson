package com.ll.JPJson.lib.json;

/**
 * Created by liuli on 15-12-7.
 */
public class JsonNull extends JsonElement{
    private static final JsonNull INSTANCE = new JsonNull();

    private JsonNull(){};

    public static JsonNull instance() {
        return INSTANCE;
    }
}
