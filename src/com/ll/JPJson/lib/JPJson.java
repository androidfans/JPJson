package com.ll.JPJson.lib;

import com.ll.JPJson.lib.json.JsonElement;
import com.ll.JPJson.lib.json.JsonObject;
import com.ll.JPJson.lib.json.JsonPrimitive;
import com.ll.JPJson.lib.parsec.JPJsonAtomicValueOperator;

import com.ll.JParsec.lib.Parser;
import com.ll.JParsec.lib.State;
import com.ll.JParsec.lib.TextState;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by liuli on 15-12-13.
 */
public class JPJson {
    public <T> T fromJson(String json, Class<T> tClass) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        State state = new TextState(json);
        Parser<JsonObject> parser = JPJsonAtomicValueOperator.JPJsonValueOperator();
        JsonElement re = parser.parse(state);
        //TODO:因为各种类型都不一样,所以这里可以使用工厂模式
        if (re instanceof JsonPrimitive) {
            T data = ((JsonPrimitive) re).getValue(tClass);
            return data;
        }
        //TODO:OBJ或者数组类型
        T reObj = null;
        return reObj;
    }

    private <T> T construct(Class<T> tClass) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor[] ctors = tClass.getConstructors();
        for (Constructor ctor : ctors) {
            Class[] paramTypes = ctor.getParameterTypes();
            if (paramTypes.length == 0) {
                T obj = (T) ctor.newInstance();
                return obj;
            }
        }
        return null;
    }
}
