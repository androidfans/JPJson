package com.ll.JPJson.lib.json;

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
}
