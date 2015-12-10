package com.ll.JPJson.lib.json;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by liuli on 15-12-7.
 */
public class JsonArray extends JsonElement implements Iterable {
    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer action) {
    }

    @Override
    public Spliterator spliterator() {
        return null;
    }
}
