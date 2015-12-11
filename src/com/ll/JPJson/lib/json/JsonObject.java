package com.ll.JPJson.lib.json;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by liuli on 15-12-7.
 */
public class JsonObject extends JsonElement {
    private HashMap<String, JsonElement> members = new HashMap<>();

    public void add(String key, JsonElement value) {
        if (value == null) {
            value = JsonNull.instance();
        }
        members.put(key, value);
    }

    public JsonElement get(String key) {
        return members.get(key);
    }
}
