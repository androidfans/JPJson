package com.ll.JPJson.lib.json;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by liuli on 15-12-7.
 */
public class JsonObject extends JsonElement implements Iterable<Map.Entry<String,JsonElement>> {
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

    @Override
    public Iterator<Map.Entry<String,JsonElement>> iterator() {
        return members.entrySet().iterator();
    }
}
