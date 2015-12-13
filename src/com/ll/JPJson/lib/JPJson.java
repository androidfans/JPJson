package com.ll.JPJson.lib;

import com.ll.JPJson.lib.json.*;
import com.ll.JPJson.lib.parsec.JPJsonAtomicValueOperator;

import com.ll.JParsec.lib.Parser;
import com.ll.JParsec.lib.State;
import com.ll.JParsec.lib.TextState;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liuli on 15-12-13.
 */
public class JPJson {
    public <T> T fromJson(String json, Class<T> tClass) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        State state = new TextState(json);
        Parser<JsonObject> parser = JPJsonAtomicValueOperator.JPJsonValueOperator();
        JsonElement re = parser.parse(state);
        return attachValue(re, tClass);
    }

    private <T> T attachValue(JsonElement re, Class<T> tClass) throws IllegalAccessException {
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
                        field.set(reObj, attachValue(value, field.getType()));
                    }
                }
            }
            return (T)reObj;
        }
        if (re instanceof JsonArray) {
            JsonArray jsonArray = (JsonArray)re;
            List reList = new ArrayList<>();
            for (JsonElement element: jsonArray) {
                Object value = attachValue(element, Object.class);
                reList.add(value);
            }
            return (T)reList;
        }
        return null;
    }

    private <T> T construct(Class<T> tClass){
        Constructor[] ctors = tClass.getDeclaredConstructors();
        for (Constructor ctor : ctors) {
            Class[] paramTypes = ctor.getParameterTypes();
            if (paramTypes.length == 0) {
                T obj = null;
                try {
                    ctor.setAccessible(true);
                    obj = (T) ctor.newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("parse failed");
                }

                return obj;
            }
        }
        return null;
    }
}
