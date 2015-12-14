package com.ll.JPJson.lib.json;


import java.awt.geom.Area;
import java.util.DoubleSummaryStatistics;
import java.util.Locale;

/**
 * Created by liuli on 15-12-7.
 */
public class JsonPrimitive extends JsonElement{
    public Object value = null;

    public JsonPrimitive(Object value) {
        this.value = value;
    }

    public Boolean getAsBoolean() {
        if (value instanceof Boolean) {
            return ((Boolean) value).booleanValue();
        }
        return Boolean.parseBoolean(getAsString());
    }

    public Long getAsLong() {
        return value instanceof Long ? ((Long) value).longValue() : Long.parseLong(getAsString());
    }

    public Integer getAsInteger() {
        return value instanceof Integer ? ((Integer) value).intValue() : Integer.parseInt(getAsString());
    }

    public Short getAsShort() {
        return value instanceof Double ? ((Double) value).shortValue() : Short.parseShort(getAsString());
    }

    public Byte getAsByte() {
        return value instanceof Byte ? ((Byte) value).byteValue() : Byte.parseByte(getAsString());
    }

    public Character getAsCharacter() {
        return getAsString().charAt(0);
    }

    public Double getAsDouble() {
        return value instanceof Double ? ((Double) value).doubleValue() : Double.parseDouble(getAsString());
    }

    public Float getAsFloat() {
        return value instanceof Float ? ((Float) value).floatValue() : Float.parseFloat(getAsString());
    }

    public Number getAsNumber() {
        if (value instanceof String) {
            return Integer.parseInt((String) value);
        }
        return (Number)value;
    }

    public String getAsString() {
        if (value instanceof Number) {
            return getAsNumber().toString();
        } else if (value instanceof Boolean) {
            return ((Boolean) value).toString();
        } else {
            return (String) value;
        }
    }

    public <T> T getValue(Class<T> tClass) {
        switch (tClass.getSimpleName()) {
            case "char":
            case "Character":
                return (T) getAsCharacter();
            case "int":
            case "Integer":
                return (T) getAsInteger();
            case "long":
            case "Long":
                return (T) getAsLong();
            case "byte":
            case "Byte":
                return (T) getAsByte();
            case "short":
            case "Short":
                return (T) getAsShort();
            case "double":
            case "Double":
                return (T) getAsDouble();
            case "float":
            case "Float":
                return (T) getAsFloat();
            case "boolean":
            case "Boolean":
                return (T) getAsBoolean();
            case "String":
                return (T) getAsString();
        }
        return (T) value;
    }
}
