package com.ll.JPJson.lib;

import com.ll.JPJson.lib.json.*;
import com.ll.JPJson.lib.parsec.JPJsonAtomicValueOperator;

import com.ll.JParsec.lib.Parser;
import com.ll.JParsec.lib.State;
import com.ll.JParsec.lib.TextState;
import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liuli on 15-12-13.
 */
public class JPJson {
    public <T> T fromJson(String json, Class<T> tClass) {
        State state = new TextState(json);
        Parser<JsonObject> parser = JPJsonAtomicValueOperator.JPJsonValueOperator();
        JsonElement re = parser.parse(state);
        return attachValue(re, tClass);
    }

    private <T> T attachValue(JsonElement re, Class<T> tClass){
        if (re instanceof JsonNull) {
            return null;
        }
        if (re instanceof JsonPrimitive) {
            T data = ((JsonPrimitive) re).getValue(tClass);
            return data;
        }
        if (re instanceof JsonObject) {
            T reObj = construct(tClass);
            Field[] fields = tClass.getFields();
            JsonObject jsonObject = (JsonObject) re;
            for (Map.Entry<String,JsonElement> entry: jsonObject) {
                String key = entry.getKey();
                JsonElement value = entry.getValue();
                for (Field field : fields) {
                    if (field.getName().equals(key)) {
                        field.setAccessible(true);
                        Class fieldType = field.getType();
                        if (fieldType.isAssignableFrom(List.class)) {
                            ParameterizedType pt = null;
                            try {
                                pt = (ParameterizedType) field.getGenericType();
                            } catch (ClassCastException e) {
                                throw new RuntimeException("you must offer a genericType on the bean's list type for json array to parse");
                            }
                            Class cla = (Class) pt.getActualTypeArguments()[0];
                            fieldType = cla;
                        }
                        try {
                            field.set(reObj, attachValue(value, fieldType));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return (T)reObj;
        }
        if (re instanceof JsonArray) {
            JsonArray jsonArray = (JsonArray)re;
            List reList = new ArrayList<>();
            for (JsonElement element: jsonArray) {
                Object value = attachValue(element, tClass);
                reList.add(value);
            }
            return (T)reList;
        }
        return null;
    }

    private <T> T construct(Class<T> tClass){
        T obj = null;
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            final Unsafe un = (Unsafe) f.get(null);
            obj = (T) un.allocateInstance(tClass);
        } catch (Exception e) {
            throw new RuntimeException("construct object failed");
        }
        return obj;
    }
}
