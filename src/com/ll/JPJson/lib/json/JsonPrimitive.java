package com.ll.JPJson.lib.json;

import java.util.Base64;

/**
 * Created by liuli on 15-12-7.
 */
public class JsonPrimitive extends JsonElement{
    public Object value = null;

    public JsonPrimitive(Object value) {
        this.value = value;
    }

    public Boolean getAsBoolean() {
        return (Boolean) value;
    }

    public Long getAsNumber() {
        return (Long) value;
    }

    public Double getAsDouble() {
        return (Double) value;
    }

    public String getAsString() {
        return (String) value;
    }
}
