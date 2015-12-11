package com.ll.JPJson.lib.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by liuli on 15-12-7.
 */
public class JsonArray extends JsonElement {

    public void setElements(List<JsonElement> elements) {
        this.elements = elements;
    }

    public List<JsonElement> getElements() {
        if (elements == null) {
            elements = new ArrayList<JsonElement>();
        }
        return elements;
    }

    private List<JsonElement> elements;

    public int size() {
        return getElements().size();
    }
}
